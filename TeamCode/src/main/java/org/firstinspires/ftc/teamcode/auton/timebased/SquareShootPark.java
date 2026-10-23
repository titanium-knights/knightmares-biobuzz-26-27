package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
import org.firstinspires.ftc.teamcode.utilities.intake;
import org.firstinspires.ftc.teamcode.utilities.shooter;
@Autonomous(name="time_specimenNearHumanNEW", group="Linear OpMode")
//@Config
public class SquareShootPark extends AutonMethods {

    public static double counter = 0.00;
    @Override

    public void updateTelemetryCounter() {
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized:", "Hopefully");
//      telemetry.update();

        shooter = new shooter(hardwareMap);
        ElapsedTime runtime = new ElapsedTime();
        waitForStart();
        runtime.reset();

        // Move forward 24 inches
        moveForward(convert(24));
        updateTelemetryCounter();

        // Move right 72 inches
        moveRight(convert(72));
        updateTelemetryCounter();

        // Move forward 24 inches
        moveForward(convert(24));
        updateTelemetryCounter();

        // Rotate 90 degrees counter-clockwise
        turn(90);

        // Shoot!
        nectarShoot();

        // Move left 24 inches
        moveLeft(convert(24));
        updateTelemetryCounter();

        // Move forward 72 inches
        moveForward(convert(72));
        updateTelemetryCounter();

        // Move left 24 inches
        moveLeft(convert(24));
        updateTelemetryCounter();

        // Arrived at parking
    }
}