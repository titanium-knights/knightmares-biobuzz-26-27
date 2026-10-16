
package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {

    public DcMotor intakeMotor;

    public static double intakePower = 0.9;

    public intake(HardwareMap hmap) {
        intakeMotor = hmap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE
        );
    }

    public void intakeRun() {
        intakeMotor.setPower(intakePower);
    }

    public void intakeReverse() {
        intakeMotor.setPower(-intakePower);
    }

    public void intakeStop() {
        intakeMotor.setPower(0.0);
    }

    public double getPower() {
        return intakeMotor.getPower();
    }
}
