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

@Autonomous(name = "AutoPath", group = "Autonomous")
public class LeftShootPark extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose left = poseFactory.of(20, 8, 90);
    private final Pose goShootStart = poseFactory.of(20, 8, 90);
    private final Pose goShoot = poseFactory.of(56, 30, 90);
    private final Pose deviateToGoParkStart = poseFactory.of(56, 30, 90);
    private final Pose deviateToGoPark = poseFactory.of(32, 30, 360);
    private final Pose goParkStart = poseFactory.of(32, 30, 360);
    private final Pose goPark = poseFactory.of(10.4301, 104.2101, 360);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, left()),
                follow(follower, goShoot()),
                follow(follower, deviateToGoPark()),
                follow(follower, goPark())
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

    public Path left() {
        return Paths.line(start, left).linear(start, left);
    }

    public Path goShoot() {
        return Paths.line(goShootStart, goShoot).linear(goShootStart, goShoot);
    }

    public Path deviateToGoPark() {
        return Paths.line(deviateToGoParkStart, deviateToGoPark).linear(deviateToGoParkStart, deviateToGoPark);
    }

    public Path goPark() {
        return Paths.line(goParkStart, goPark).linear(goParkStart, goPark);
    }
}
