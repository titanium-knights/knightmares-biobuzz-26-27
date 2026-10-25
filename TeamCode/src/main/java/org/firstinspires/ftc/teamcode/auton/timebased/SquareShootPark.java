//package org.firstinspires.ftc.teamcode.auton.timebased;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.auton.AutonMethods;
//@Autonomous(name="Square Shoot Park", group="Linear OpMode")
////@Config
//public class SquareShootPark extends AutonMethods {
//
//    public static double counter = 0.00;
//    @Override
//
//    // Telemetry UPDATE
//
//    public static void updateTelemetryCounter() {
//        counter++;
//        telemetry.addLine("counter" + counter);
//        telemetry.update();
//    }
//
//    // Movement functions
//
//    public static void goForward (double howMuchIN) {
//        moveForward(convert(howMuchIN));
//        updateTelemetryCounter();
//    }
//    public static void goBackward (double howMuchIN) {
//        moveBackward(convert(howMuchIN));
//        updateTelemetryCounter();
//    }
//    public static void goLeft (double howMuchIN) {
//        moveLeft(convert(howMuchIN));
//        updateTelemetryCounter();
//    }
//    public static void goRight (double howMuchIN) {
//        moveRight(convert(howMuchIN));
//        updateTelemetryCounter();
//    }
//
//    // Movement functions end
//
//    public void runOpMode() throws InterruptedException {
//        telemetry.addData("Initialized:", "Hopefully");
////      telemetry.update();
//
//        shooter = new shooter(hardwareMap);
//        ElapsedTime runtime = new ElapsedTime();
//        waitForStart();
//        runtime.reset();
//
//        // Move forward 24 inches
//        goForward(24);
//
//        // Move right 72 inches
//        goRight(72);
//
//        // Move forward 24 inches
//        goForward(24);
//
//        // Rotate 90 degrees counter-clockwise
//        turn(90);
//
//        // Shoot!
//        nectarShoot();
//
//        // Move left 24 inches
//        goLeft(24);
//
//        // Move forward 72 inches
//        goForward(72);
//
//        // Move left 24 inches
//        goLeft(24);
//
//        // Arrived at parking
//    }
//}