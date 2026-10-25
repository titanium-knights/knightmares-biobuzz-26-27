package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
@Autonomous(name="Shoot Park", group="Linear OpMode")
//@Config
public class ShootPark extends AutonMethods {

    @Override
    public void init() {
        super.init();
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized:", "Hopefully");

        //move forward 2.5 clocks
        goForward(54);

        //turn 90 degrees
        rotateCW(90);

        //shoot function
        boomShakaLaka();

        //move to the left 1.5 blocks
        goLeft(30);

        // move forward 3
        goForward(72);

        // move to the left into the wall
        goLeft(48);
    }
}

