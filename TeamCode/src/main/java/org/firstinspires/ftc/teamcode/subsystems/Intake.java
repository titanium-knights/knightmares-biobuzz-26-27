package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.Config;

public class Intake {

    public static Telemetry telemetry;
    public static final double intakePower = 0.7;
    public final DcMotor middleIntake;
    public final DcMotor outerIntake;

    public Intake(HardwareMap hmap, Telemetry telemetry) {
        this.middleIntake = hmap.dcMotor.get(Config.MIDDLE_INTAKE);
        this.outerIntake = hmap.dcMotor.get(Config.OUTER_INTAKE);
        this.middleIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.outerIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.middleIntake.setDirection(Config.M_IN_DIRECTION);
        this.outerIntake.setDirection(Config.O_IN_DIRECTION);
        Intake.telemetry = telemetry;
    }

    public void intakeRun() {
        middleIntake.setPower(intakePower);
        outerIntake.setPower(intakePower);
        telemetry.addLine(" intake is running");
    }

    public void intakeStop() {
        middleIntake.setPower(0.0);
        outerIntake.setPower(0.0);
        telemetry.addLine(" intake is stopped");
    }

    public void intakeReversed() {
        middleIntake.setPower(-0.7);
        outerIntake.setPower(-0.7);
        telemetry.addLine(" intake is reversing");
    }
}
