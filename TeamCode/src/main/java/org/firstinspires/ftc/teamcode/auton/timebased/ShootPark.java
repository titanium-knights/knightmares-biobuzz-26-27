package org.firstinspires.ftc.teamcode.auton.timebased;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
import org.firstinspires.ftc.teamcode.utilities.shooter;
@Autonomous(name="time_specimenNearHumanNEW", group="Linear OpMode")
//@Config
public class ShootPark extends AutonMethods {
    @Override


    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized:", "Hopefully");
//      telemetry.update();

        shooter = new shooter(hardwareMap);

        ElapsedTime runtime = new ElapsedTime();
        waitForStart();
        runtime.reset();
        double counter = 0;

        //move forward 2.5 clocks
        moveForward(convert(54));
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();

        //turn 90 degrees

        //shoot function
        nectarShoot();

        //move to the left 1.5 blocks
        moveLeft(convert(30));
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();

        // move forward 3
        moveForward(convert(72));
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();
        // move to the left into the wall
        moveLeft(convert(48));
        counter++;
        telemetry.addLine("counter" + counter);
        telemetry.update();
    }

    //
}

