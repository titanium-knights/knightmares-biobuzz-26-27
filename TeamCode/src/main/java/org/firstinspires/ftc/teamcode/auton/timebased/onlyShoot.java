package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
@Autonomous(name="onlyShoot", group="Linear OpMode")
//@Config
public class onlyShoot extends AutonMethods {

    @Override
    public void init() {
        super.init();
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized:", "Hopefully");

        boomShakaLaka();
    }

}