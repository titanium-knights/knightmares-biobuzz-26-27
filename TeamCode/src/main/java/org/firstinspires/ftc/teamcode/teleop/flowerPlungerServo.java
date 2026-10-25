//package org.firstinspires.ftc.teamcode.teleop;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.utilities.SubsystemManager;
//
//@TeleOp(name = "Servo")
//public class flowerPlungerServo extends OpMode {
//    public final double UP_STATE = 0.0;
//    public final double MIDDLE_STATE = 0.5;
//    public final double DOWN_STATE = 1.0;
//
//    private SubsystemManager subsystemManager;
//    private double rot = 0.0;
//
//    @Override
//    public void init() {
//        subsystemManager = new SubsystemManager(hardwareMap, telemetry);
//
//        telemetry.addLine("flowerPlungerServo initialized");
//        telemetry.update();
//    }
//// A - lowest, y - up, x - mid
//    @Override
//    public void loop() {
//        if (gamepad1.yWasPressed()) {
////            rot = Math.min(rot + 0.1, 1.0);
//            rot = UP_STATE;
//        }
//        if (gamepad1.xWasPressed()) {
////            rot = Math.max(rot - 0.1, 0.0);
//
//            rot = MIDDLE_STATE;
//        }
//        if (gamepad1.aWasPressed()) {
////            rot = Math.max(rot - 0.1, 0.0);
//            rot = DOWN_STATE;
//        }
//
////        telemetry.addLine(String.format("rot = %f", rot));
////
//        subsystemManager.gates.setLargePosition(rot);
////
//        telemetry.update();
//    }
//}
