package org.firstinspires.ftc.teamcode.auton.pedro;

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
public class straightShootPark extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56.8156, 8, 0);
    private final Pose toShoot = poseFactory.of(56, 36, 0);
    private final Pose moveSideways = poseFactory.of(37.67, 36, 0);
    private final Pose moveForwardTowardsParkStart = poseFactory.of(37.67, 36, 0);
    private final Pose moveForwardTowardsPark = poseFactory.of(38, 105, 0);
    private final Pose parkStart = poseFactory.of(38, 105, 0);
    private final Pose park = poseFactory.of(8, 105, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, toShoot()),
                follow(follower, moveSideways()),
                follow(follower, moveForwardTowardsPark()),
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

    public Path toShoot() {
        return Paths.line(start, toShoot).linear(start, toShoot);
    }

    public Path moveSideways() {
        return Paths.line(toShoot, moveSideways).reverseTangent();
    }

    public Path moveForwardTowardsPark() {
        return Paths.line(moveForwardTowardsParkStart, moveForwardTowardsPark).linear(moveForwardTowardsParkStart, moveForwardTowardsPark);
    }

    public Path park() {
        return Paths.line(parkStart, park).linear(parkStart, park);
    }
}