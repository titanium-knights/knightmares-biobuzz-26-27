# Zed editor setup (Java + Kotlin language servers)

This folder makes **Java (jdtls)** and **Kotlin (kotlin-lsp)** work properly in
[Zed](https://zed.dev) for this FTC Android project: go-to-definition, completions, and
diagnostics across our code, the FTC SDK, and each other (Java <-> Kotlin).

**Android Studio is unaffected.** Nothing here changes any Gradle build file, plugin, or
version. Everything is either Zed-only config, a git-ignored generated file, or a Gradle
init script that is only loaded when explicitly requested or when kotlin-lsp itself starts
the build.

## Quick start (after cloning)

Prerequisites:

- Zed with the **Java** and **Kotlin** extensions installed (they provide jdtls and kotlin-lsp).
- A JDK that can run the Gradle wrapper (Gradle 9.1 needs JDK 17+).
- The Android SDK, with `local.properties` at the repo root containing `sdk.dir=...`
  (Android Studio creates this automatically; otherwise
  `echo 'sdk.dir=/path/to/Android/Sdk' > local.properties`).
- Network access for the first run, so Gradle can download dependencies.

Then:

```sh
.zed/setup.sh
```

and in Zed run `editor: restart language server` (or restart Zed). That's it.

Re-run `.zed/setup.sh` whenever:

- dependencies in `build.dependencies.gradle` change,
- you add/remove a Gradle module,
- you changed Kotlin code and want **Java** files to see the new/changed Kotlin members
  (see "Java seeing Kotlin" below).

Windows: the script is POSIX `sh`. Do the two steps by hand (below) with `%USERPROFILE%\.gradle\init.d`.

## What `setup.sh` does

0. Generates `.zed/settings.json` from `settings.json.template`, filling in this clone's absolute
   path for jdtls's `workspaceFolders`.
1. Copies `.zed/lsp-android-sync-fix.init.gradle` to `~/.gradle/init.d/lsp-android-sync-fix.gradle`
   (fixes kotlin-lsp, see below).
2. Runs `./gradlew -I .zed/jdtls-classpath.init.gradle :FtcRobotController:createFullJarDebug :TeamCode:bundleDebugClassesToCompileJar :FtcRobotController:genJdtlsProject :TeamCode:genJdtlsProject`
   (fixes jdtls, see below). This also compiles Kotlin, and builds the two module jars that
   kotlin-lsp lists as dependencies.

## Files in this folder

| File | Purpose | Committed |
| --- | --- | --- |
| `settings.json.template` | Template for the Zed project settings for jdtls (`workspaceFolders` + Gradle/Maven import disabled, see below) | yes |
| `settings.json` | Generated from the template by `setup.sh` with this clone's absolute path | no (git-ignored) |
| `setup.sh` | Runs both steps above | yes |
| `jdtls-classpath.init.gradle` | Gradle init script that generates `.project`/`.classpath` for jdtls | yes |
| `lsp-android-sync-fix.init.gradle` | Gradle init script that unbreaks kotlin-lsp's Gradle sync | yes |
| `FtcRobotController/.project`, `.classpath`, `TeamCode/.project`, `.classpath` | Generated for jdtls | no (git-ignored) |
| `~/.gradle/init.d/lsp-android-sync-fix.gradle` | Installed copy of the kotlin-lsp fix | no (your machine) |

## Background: what was broken and why

### Java (jdtls)

Symptom: `Intake.java is not on the classpath of project TeamCode, only syntax errors are reported`.

Cause: jdtls imports Gradle projects through Buildship, which learns source folders and
dependencies from Gradle's **Java** plugin. This project uses `com.android.application` and
`com.android.library`, which don't expose that information, so jdtls registered `TeamCode`
as an empty project with no source roots, no `android.jar`, and none of the FTC/Pedro libraries.

Fix: skip Buildship entirely and hand jdtls plain Eclipse project files
(`.project` + `.classpath`) that contain the information Buildship can't discover. jdtls's
Eclipse importer finds the nested files in `FtcRobotController/` and `TeamCode/` and registers
both as projects.

- **`workspaceFolders` must point at this repo** (done by `settings.json.template`). A user's
  *global* Zed settings (`~/.config/zed/settings.json`) can define jdtls `workspaceFolders` too
  (e.g. for other school or personal projects). If the project settings don't set it, jdtls
  inherits the global value, never imports this repo, and every file is
  "a non-project file". It must be an absolute path, which is why `settings.json` is generated
  per clone instead of committed. (We hit this: it was dropped once for portability and Java
  broke.)
- **Keep jdtls's Gradle and Maven import disabled** (`java.import.gradle.enabled: false`,
  `java.import.maven.enabled: false` in `settings.json`). If the Gradle import is on, Buildship
  imports the project itself, applies Gradle's `eclipse` plugin (which finds nothing in an Android
  module) and **overwrites our `.project` and `.classpath` with empty ones** (no source folder,
  no jars). Symptom: `X.java is not on the classpath of project TeamCode, only syntax errors are
  reported` even though `TeamCode` is a registered project. You can recognise it by `.classpath`
  shrinking to ~4 entries including `org.eclipse.buildship.core.gradleclasspathcontainer` and
  `.project` gaining the `gradleprojectnature`. With the import off, the jdtls log says
  `Importing Eclipse project(s)` instead of `Importing Gradle project(s)`, and our files are
  used untouched.
- `jdtls-classpath.init.gradle` registers a `genJdtlsProject` task on each Android module. It
  writes `.project` and `.classpath` containing:
  - the module's source dirs (`src/main/java`) and generated `R`/`BuildConfig` dirs if built,
  - a project reference from `TeamCode` to `FtcRobotController`,
  - `android.jar` for the project's `compileSdk`,
  - every resolved dependency jar (AARs are read as their unpacked `classes.jar`),
  - the compiled Kotlin classes (`build/tmp/kotlin-classes/debug`).
- The script is **not** applied automatically; it only runs because `setup.sh` passes `-I`.

#### Java seeing Kotlin

jdtls can't parse `.kt` files, so Java sees Kotlin only through compiled classes.
`genJdtlsProject` depends on `compileDebugKotlin` so those classes exist and are current when
it runs. Consequence: if you add or change a member in a `.kt` file, jdtls won't see it from
Java until you re-run `.zed/setup.sh` (or `./gradlew :TeamCode:compileDebugKotlin`) and restart
the Java language server.

### Kotlin (kotlin-lsp)

Symptom: `Unable to import a Gradle project: org.gradle.tooling.BuildActionFailureException: The supplied phased action failed with an exception.`, and then Kotlin files can't see FTC SDK
classes (no libraries get loaded when the import fails).

Cause: kotlin-lsp runs its own Gradle sync and its Android source-set resolver
(`resolveAndroidSourceSets.kt`) reads the `androidTest` variant's BuildConfig directory before
that task has run, which Gradle rejects:

```
Querying the mapped value of property(...) before task
':FtcRobotController:generateDebugAndroidTestBuildConfig' has completed is not supported
```

Fix: `lsp-android-sync-fix.init.gradle` disables the `androidTest` variants, but **only when
the build was started by kotlin-lsp**. kotlin-lsp passes its own init script named
`lsp-gradle-init*.gradle`, and our script checks for that name:

```groovy
def startedByLsp = gradle.startParameter.initScripts.any { it.name.startsWith('lsp-gradle-init') }
```

For every other build (Android Studio, `./gradlew`, CI) it does nothing. Gradle only loads
init scripts automatically from `~/.gradle/init.d/`, so `setup.sh` copies it there.
(We don't use `androidTest` in this project; it only affects what kotlin-lsp sees while syncing.)

Kotlin seeing Java needs nothing extra: after the sync, kotlin-lsp reads the `.java` files in
`src/main/java` as source.

## Things we tried that did NOT work (so nobody repeats them)

- **A generated `workspace.json` for kotlin-lsp.** The *open-source* kotlin-lsp reads one
  before trying Gradle, but the newer bundled server ("Java and Kotlin by IntelliJ IDEA",
  build 263.x, which the Zed Kotlin extension downloads) only has Gradle/Maven/Bazel
  importers and ignores it. If you see `Trying to import using gradle` in the log and nothing
  about JSON, that's why.
- **Leaving jdtls's Gradle import enabled** alongside the generated files. Buildship overwrites
  them with empty ones (see the Java section above).
- **Committing a `settings.json` with a hard-coded `workspaceFolders` path** (breaks for everyone
  else), and the opposite, **omitting `workspaceFolders`** (jdtls then inherits the user's global
  Zed value and never imports this repo). Hence the generated `settings.json`.
- Changing Android Gradle Plugin / Gradle / Kotlin versions to dodge the kotlin-lsp bug.
  Not an option: this repo is a fork of the FTC SDK starter and versions must stay as-is.

## Troubleshooting

**Java: "X.java is a non-project file, only syntax errors are reported"**
- `.zed/settings.json` must exist (run `.zed/setup.sh`) and contain `workspaceFolders` set to
  *this* repo's path, plus `java.import.gradle.enabled: false`. Also check your global
  `~/.config/zed/settings.json` for a jdtls `workspaceFolders` pointing elsewhere.

**Java: "X.java is not on the classpath of project TeamCode"** (TeamCode *is* registered)
- Buildship overwrote the generated files. Check `TeamCode/.classpath`: if it has only ~4
  entries and mentions `buildship`, the Gradle import is enabled somewhere. Set
  `java.import.gradle.enabled: false` (re-run `.zed/setup.sh` to regenerate `settings.json`),
  re-run `.zed/setup.sh` to rewrite the `.project`/`.classpath` files, then quit the Java language
  server, delete `~/.cache/jdtls-<hash>` and restart it.
- Look at jdtls's project list: `ls ~/.cache/jdtls-*/.metadata/.plugins/org.eclipse.core.resources/.projects/`.
  You should see `TeamCode` and `FtcRobotController`. If you only see `jdt.ls-java-project`,
  jdtls didn't import anything.
- **Changing the import setting alone is not enough.** jdtls records a digest of the build files
  after each import (`.metadata/.plugins/org.eclipse.jdt.ls.core/.file-digests`) and skips
  re-importing while they're unchanged, so a restart logs `Importers: ...` and nothing else.
  A real import logs `Importing Gradle project(s)` and `Progressive import: reporting N new
  project(s)` (see `.metadata/.log` in the data directory). To force one: quit the Java language
  server, delete the whole data directory (`~/.cache/jdtls-<hash>`, safe, it's only a cache),
  and restart the language server.

**Java: still "not on the classpath"**
- Make sure `TeamCode/.classpath` exists (re-run `.zed/setup.sh`).
- jdtls caches the old Gradle import. Restart the language server; if that isn't enough,
  delete jdtls's workspace data (under Zed's extension data directory, e.g.
  `~/.local/share/zed/extensions/work/java/`) and restart Zed.

**Java: can't see a Kotlin class** - re-run `.zed/setup.sh` (it recompiles Kotlin), restart the
Java language server.

**Java: can't find `R` or `BuildConfig`** - run `./gradlew :TeamCode:assembleDebug` once, then
`.zed/setup.sh`. Those files only exist after a build.

**Kotlin: `Couldn't resolve some dependencies: ... full.jar, ... classes.jar`**
- Harmless warning: kotlin-lsp lists the modules' own build outputs as dependencies and they
  hadn't been built. `.zed/setup.sh` builds them (`createFullJarDebug`,
  `bundleDebugClassesToCompileJar`). After a `./gradlew clean` run `.zed/setup.sh` again.

**Kotlin: still `Unable to import a Gradle project`**
- Confirm `~/.gradle/init.d/lsp-android-sync-fix.gradle` exists.
- Look at the newest kotlin-lsp log for the real error:
  `ls -t /tmp/idea-system*/system/log/intellij-server.log | head -1`, then search it for
  `Gradle sync failed` / `Caused by`.
- kotlin-lsp caches its workspace model. Delete `~/.config/JetBrains/analyzer/workspaces/`
  and restart the language server.
- The kotlin-lsp version is chosen by the Zed Kotlin extension and is alpha-quality software.
  A different version may need a different workaround.

**Gradle error about `dependencyProject` or similar in `jdtls-classpath.init.gradle`** - the
script targets Gradle 9.1 / AGP 8.13. If the wrapper or AGP version changes, the script may
need small updates (it uses `android.bootClasspath`, the `android-classes-jar` artifact view,
and `ProjectDependency.name`).

## Undoing everything

```sh
rm -f ~/.gradle/init.d/lsp-android-sync-fix.gradle
rm -f FtcRobotController/.project FtcRobotController/.classpath TeamCode/.project TeamCode/.classpath
rm -rf .zed
```
