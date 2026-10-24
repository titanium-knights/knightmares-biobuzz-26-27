package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.utilities.SubsystemManager;

@TeleOp(name = "TeleOp")
public class Teleop extends OpMode {

    boolean doModifier = false;
    double driveSpeedModifier = -1.0;
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
        subsystemManager.drive.move(
            gamepad1.left_stick_x * (doModifier ? driveSpeedModifier : 1.0),
            -gamepad1.left_stick_y * (doModifier ? driveSpeedModifier : 1.0), // negated bc y value is opposite on the stick
            gamepad1.right_stick_x
        );

        if (gamepad1.rightBumperWasPressed()) {
            doModifier = !doModifier;
        }

        telemetry.addLine(String.format("modifier = %B", doModifier));

        if (gamepad1.right_trigger_pressed) {
            subsystemManager.shooters.shootSmall(0.0); // TODO: FIND VALUE USING DEBUGGER LINES BELOW
            subsystemManager.shooters.shootLarge(0.0); // TODO: FIND VALUE USING DEBUGGER LINES BELOW
        } else {
          subsystemManager.shooters.stopSmall();
          subsystemManager.shooters.stopLarge();
        }

        if (gamepad1.left_trigger_pressed) {
            subsystemManager.intake.intakeRun();
        } else if (gamepad1.left_bumper) {
            subsystemManager.intake.intakeReversed();
        } else {
            subsystemManager.intake.intakeStop();
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

        telemetry.addLine(String.format("speed = %d", speed));


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
