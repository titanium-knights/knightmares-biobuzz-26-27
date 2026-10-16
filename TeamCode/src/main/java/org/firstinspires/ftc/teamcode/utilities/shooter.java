
package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class shooter {

    public DcMotor nectarMotor;
    public DcMotor pollenMotor;

    public static double nectarPower = 0.9;
    public static double pollenPower = 0.9;

    public shooter(HardwareMap hmap) {
        nectarMotor = hmap.get(DcMotor.class, "nectarMotor");
        pollenMotor = hmap.get(DcMotor.class, "pollenMotor");

        nectarMotor.setDirection(DcMotor.Direction.FORWARD);
        pollenMotor.setDirection(DcMotor.Direction.FORWARD);

        nectarMotor.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE
        );
        pollenMotor.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE
        );
    }

    public void nectarShoot() {
        nectarMotor.setPower(nectarPower);
    }

    public void nectarStop() {
        nectarMotor.setPower(0.0);
    }

    public void pollenShoot() {
        pollenMotor.setPower(pollenPower);
    }

    public void pollenStop() {
        pollenMotor.setPower(0.0);
    }

    public void stopAll() {
        nectarStop();
        pollenStop();
    }
}
