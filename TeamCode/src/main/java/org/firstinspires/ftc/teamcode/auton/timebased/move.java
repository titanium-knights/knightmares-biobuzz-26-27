package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
@Autonomous(name="move", group="Linear OpMode")
//@Config
public class move extends AutonMethods {
    boolean ligma = true;

    @Override
    public void init() {
        super.init();
        telemetry.addData("Initialized:", "Hopefully");
    }

    @Override
    public void loop() {
        if (ligma) {
            //move forward 1.0 blocks
            goHelper(0.0, 0.0, 1.0, 0.4);
            ligma = false;
        }
    }
}
