#!/bin/sh
# One-time (and "after dependencies change") setup for Java + Kotlin language servers in Zed.
# See .zed/README.md for what this does and why.
#
# Usage, from anywhere inside the repo:
#   .zed/setup.sh              # normal
#   .zed/setup.sh --offline    # extra args are passed through to ./gradlew

set -eu

repo="$(cd "$(dirname "$0")/.." && pwd)"
cd "$repo"

if [ ! -f local.properties ]; then
    echo "warning: local.properties not found. Create it with your Android SDK path, e.g." >&2
    echo "         echo 'sdk.dir=/path/to/Android/Sdk' > local.properties" >&2
fi

# 0) Zed project settings. jdtls needs workspaceFolders to point at THIS clone (an absolute
#    path), so settings.json is generated from a template and is git-ignored.
sed "s|__REPO_DIR__|$repo|g" .zed/settings.json.template > .zed/settings.json
echo "wrote .zed/settings.json for $repo"

# 1) kotlin-lsp workaround: Gradle only auto-loads init scripts from ~/.gradle/init.d.
#    The script is a no-op unless the build was started by kotlin-lsp.
mkdir -p "$HOME/.gradle/init.d"
rm -f "$HOME/.gradle/init.d/lsp-android-sync-fix.gradle"
cp .zed/lsp-android-sync-fix.init.gradle "$HOME/.gradle/init.d/lsp-android-sync-fix.gradle"
echo "installed ~/.gradle/init.d/lsp-android-sync-fix.gradle"

# 2) jdtls: generate .project/.classpath for both Android modules. This also compiles the
#    Kotlin sources so Java can see Kotlin classes.
#    createFullJarDebug / bundleDebugClassesToCompileJar produce the module jars that
#    kotlin-lsp lists as dependencies; without them it warns "Couldn't resolve some dependencies".
./gradlew "$@" -I .zed/jdtls-classpath.init.gradle \
    :FtcRobotController:createFullJarDebug :TeamCode:bundleDebugClassesToCompileJar \
    :FtcRobotController:genJdtlsProject :TeamCode:genJdtlsProject

echo
echo "Done. In Zed, run 'editor: restart language server' (or restart Zed)."
echo "If the old errors persist, see 'Troubleshooting' in .zed/README.md."
