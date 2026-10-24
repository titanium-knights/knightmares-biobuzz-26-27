package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
import org.firstinspires.ftc.teamcode.utilities.shooter;
@Autonomous(name="Shoot Park", group="Linear OpMode")
//@Config
public class ShootPark extends AutonMethods {

    public static double counter = 0.00;

    @Override


    // Telemetry UPDATE

    public static void updateTelemetryCounter() {
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();
    }

    // Movement functions

    public static void goForward (double howMuchIN) {
        moveForward(convert(howMuchIN));
        updateTelemetryCounter();
    }
    public static void goBackward (double howMuchIN) {
        moveBackward(convert(howMuchIN));
        updateTelemetryCounter();
    }
    public static void goLeft (double howMuchIN) {
        moveLeft(convert(howMuchIN));
        updateTelemetryCounter();
    }
    public static void goRight (double howMuchIN) {
        moveRight(convert(howMuchIN));
        updateTelemetryCounter();
    }

    // Movement functions end

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized:", "Hopefully");

        shooter = new shooter(hardwareMap);

        ElapsedTime runtime = new ElapsedTime();
        waitForStart();
        runtime.reset();

        //move forward 2.5 clocks
        goForward(54);

        //turn 90 degrees
        turn(90);

        //shoot function
        nectarShoot();

        //move to the left 1.5 blocks
        goLeft(30);

        // move forward 3
        goForward(72);

        // move to the left into the wall
        goLeft(48);
    }
}

