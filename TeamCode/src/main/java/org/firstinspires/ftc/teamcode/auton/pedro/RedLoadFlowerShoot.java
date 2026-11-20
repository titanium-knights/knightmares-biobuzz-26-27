package org.firstinspires.ftc.teamcode.auton.pedro;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;
@Autonomous(name = "RedLoadFlowerShoot", group = "Autonomous")
public class RedLoadFlowerShoot extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose prepareToShoot1 = poseFactory.of(56, 13, 90);
    private final Pose goToPositionIntake1 = poseFactory.of(31.9401, 7.9333, 180);
    private final Pose goIntake1 = poseFactory.of(7.8801, 7.93, 180);
    private final Pose goLineUpForSideSwitchStart = poseFactory.of(7.8801, 7.93, 180);
    private final Pose goLineUpForSideSwitch = poseFactory.of(56.3659, 35.1927, 270);
    private final Pose driveUnderBasket = poseFactory.of(60.4193, 133.3952, 270);
    private final Pose prepareToShoot2 = poseFactory.of(57.1309, 119.2083, 270);
    private final Pose displaceToGoFlowerStart = poseFactory.of(57.1309, 119.2083, 270);
    private final Pose displaceToGoFlower = poseFactory.of(48.446, 119.6315, 0);
    private final Pose goIntakeFromFlower = poseFactory.of(48, 130, 90);
    private final Pose goToPositionShoot3 = poseFactory.of(51.9447, 120.6871, 90);
    private final Pose prepareToShoot3Start = poseFactory.of(51.9447, 120.6871, 90);
    private final Pose prepareToShoot3 = poseFactory.of(60.42, 133.4, 270);
    private final Pose park = poseFactory.of(9.8656, 106.6773, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, prepareToShoot1()),
                follow(follower, goToPositionIntake1()),
                follow(follower, goIntake1()),
                follow(follower, goLineUpForSideSwitch()),
                follow(follower, driveUnderBasket()),
                follow(follower, prepareToShoot2()),
                follow(follower, displaceToGoFlower()),
                follow(follower, goIntakeFromFlower()),
                follow(follower, goToPositionShoot3()),
                follow(follower, prepareToShoot3()),
                follow(follower, park())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        waitForStart();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
    }

    public Path prepareToShoot1() {
        return Paths.line(start, prepareToShoot1).constant(prepareToShoot1);
    }

    public Path goToPositionIntake1() {
        return Paths.line(prepareToShoot1, goToPositionIntake1).constant(goToPositionIntake1);
    }

    public Path goIntake1() {
        return Paths.line(goToPositionIntake1, goIntake1).constant(goIntake1);
    }

    public Path goLineUpForSideSwitch() {
        return Paths.line(goLineUpForSideSwitchStart, goLineUpForSideSwitch).linear(goLineUpForSideSwitchStart, goLineUpForSideSwitch);
    }

    public Path driveUnderBasket() {
        return Paths.line(goLineUpForSideSwitch, driveUnderBasket).constant(driveUnderBasket);
    }

    public Path prepareToShoot2() {
        return Paths.line(driveUnderBasket, prepareToShoot2).constant(prepareToShoot2);
    }

    public Path displaceToGoFlower() {
        return Paths.line(displaceToGoFlowerStart, displaceToGoFlower).linear(displaceToGoFlowerStart, displaceToGoFlower);
    }

    public Path goIntakeFromFlower() {
        return Paths.line(displaceToGoFlower, goIntakeFromFlower).constant(goIntakeFromFlower);
    }

    public Path goToPositionShoot3() {
        return Paths.line(goIntakeFromFlower, goToPositionShoot3).constant(goToPositionShoot3);
    }

    public Path prepareToShoot3() {
        return Paths.line(prepareToShoot3Start, prepareToShoot3).linear(prepareToShoot3Start, prepareToShoot3);
    }

    public Path park() {
        return Paths.line(prepareToShoot3, park).constant(park);
    }
}
