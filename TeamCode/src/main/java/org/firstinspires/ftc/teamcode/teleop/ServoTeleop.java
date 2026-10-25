package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.SubsystemManager;

@TeleOp(name = "Servo")
public class ServoTeleop extends OpMode {
    private SubsystemManager subsystemManager;
    private double rot = 0.0;

    @Override
    public void init() {
        subsystemManager = new SubsystemManager(hardwareMap, telemetry);

        telemetry.addLine("67");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (gamepad1.dpadUpWasPressed()) {
            rot = Math.min(rot + 0.1, 1.0);
        }
        if (gamepad1.dpadDownWasPressed()) {
            rot = Math.max(rot - 0.1, -1.0);
        }

        telemetry.addLine(String.format("rot = %f", rot));

        subsystemManager.gates.setLargePosition(rot);

        telemetry.update();
    }
}
