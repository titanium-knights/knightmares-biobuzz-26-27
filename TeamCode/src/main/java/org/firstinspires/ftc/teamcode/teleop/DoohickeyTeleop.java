package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.utilities.CONFIG;

@TeleOp(name = "Doohickey")
public class DoohickeyTeleop extends OpMode {
    private DcMotorEx doohickey;

    @Override
    public void init() {
        doohickey = hardwareMap.get(DcMotorEx.class, CONFIG.DOOHICKEY);
        doohickey.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        doohickey.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger_pressed) {
            doohickey.setVelocity(2800, AngleUnit.DEGREES);
        } else if (gamepad1.right_bumper) {
            doohickey.setVelocity(1400);
        } else {
            doohickey.setVelocity(0.0, AngleUnit.DEGREES);
        }

        telemetry.addData("velo", doohickey.getVelocity());
        telemetry.update();
    }
}
