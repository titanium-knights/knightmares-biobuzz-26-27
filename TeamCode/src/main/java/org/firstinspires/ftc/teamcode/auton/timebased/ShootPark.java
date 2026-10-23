package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
import org.firstinspires.ftc.teamcode.utilities.shooter;
@Autonomous(name="time_specimenNearHumanNEW", group="Linear OpMode")
//@Config
public class ShootPark extends AutonMethods {

    public static double counter = 0.00;

    @Override


    public void updateTelemetryCounter() {
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized:", "Hopefully");

        shooter = new shooter(hardwareMap);

        ElapsedTime runtime = new ElapsedTime();
        waitForStart();
        runtime.reset();

        //move forward 2.5 clocks
        moveForward(convert(54));
        updateTelemetryCounter();

        //turn 90 degrees
        turn(90);

        //shoot function
        nectarShoot();

        //move to the left 1.5 blocks
        moveLeft(convert(30));
        updateTelemetryCounter();

        // move forward 3
        moveForward(convert(72));
        updateTelemetryCounter();

        // move to the left into the wall
        moveLeft(convert(48));
        updateTelemetryCounter();
    }

    //
}

