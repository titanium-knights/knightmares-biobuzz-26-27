package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.utilities.SubsystemManager;

@TeleOp(name = "TeleOp")
public class Teleop extends OpMode {

    boolean doModifier = false;
    double driveSpeedModifier = -1.0;
    private SubsystemManager subsystemManager;

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

        if (gamepad1.right_trigger_pressed) subsystemManager.shooters.shootBoth();
        else subsystemManager.shooters.stopBoth();

        if (gamepad1.left_trigger_pressed) {
            subsystemManager.intake.intakeRun();
        } else if (gamepad1.left_bumper) {
            subsystemManager.intake.intakeReversed();
        } else {
            subsystemManager.intake.intakeStop();
        }

        if (gamepad1.rightTriggerWasPressed()) subsystemManager.scoreMacro.start();
        if (gamepad1.rightTriggerWasReleased()) subsystemManager.scoreMacro.stop();
        subsystemManager.scoreMacro.update();


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
