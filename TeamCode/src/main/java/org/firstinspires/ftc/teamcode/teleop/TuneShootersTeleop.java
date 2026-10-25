package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.utilities.SubsystemManager;

@TeleOp(name = "TuneShootersTeleOp")
public class TuneShootersTeleop extends OpMode {
    private SubsystemManager subsystemManager;
    private int speed = 1000;

    @Override
    public void init() {
        subsystemManager = new SubsystemManager(hardwareMap, telemetry);

        telemetry.addLine("67");
        telemetry.update();
    }

    @Override
    public void loop() {

        if (gamepad1.right_trigger_pressed) {
            subsystemManager.shooters.shootSmall(0.0); // TODO: FIND VALUE USING DEBUGGER LINES BELOW
            subsystemManager.shooters.shootLarge(0.0); // TODO: FIND VALUE USING DEBUGGER LINES BELOW
        } else {
            subsystemManager.shooters.stopSmall();
            subsystemManager.shooters.stopLarge();
        }
        // DEBUGGING
        if (gamepad1.a) {
            subsystemManager.shooters.shootSmall(Math.min(speed, 18_000));
        } else {
            subsystemManager.shooters.shootSmall(0.0);
        }
        if (gamepad1.b) {
            subsystemManager.shooters.shootLarge(Math.min(speed, 18_000));
        } else {
            subsystemManager.shooters.shootLarge(0.0);
        }
        if (gamepad1.dpadUpWasPressed()) {
            speed = Math.min(speed + 100, 18_000);
        }
        if (gamepad1.dpadDownWasPressed()) {
            speed = Math.max(speed - 100, 0);
        }

        telemetry.update();
    }
}

/*
Gamepad 1:
rt-intake
lt-outtake
joysticks-movement
x - set yaw angle
y - go to yaw angle

Gamepad 2:
rt-intake
lt-outtake
ld-left gate
rd-right gate
ud-both gate
rb-right shooter
lb-left shooter


 */
