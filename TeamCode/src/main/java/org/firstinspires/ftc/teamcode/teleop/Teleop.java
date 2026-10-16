
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.intake;
import org.firstinspires.ftc.teamcode.utilities.shooter;
import org.firstinspires.ftc.teamcode.utilities.mechanisms;

@TeleOp(name = "New Season TeleOp")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {

        intake intake = new intake(hardwareMap);
        shooter shooter = new shooter(hardwareMap);
        mechanisms mechanisms = new mechanisms(hardwareMap);

        boolean previousVectorLB = false;
        boolean previousPollenRB = false;

        telemetry.addLine("Initialized!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // CONTROLLER 1: INTAKE
            if (gamepad1.left_bumper) {
                intake.intakeReverse();
            } else if (gamepad1.left_trigger > 0.1) {
                intake.intakeRun();
            } else {
                intake.intakeStop();
            }

            // CONTROLLER 1: FLOWER POWER
            if (gamepad1.right_bumper) {
                mechanisms.flowerActivate();
            } else {
                mechanisms.flowerRest();
            }

            // CONTROLLER 2: NECTAR SHOOTER
            if (gamepad2.left_trigger > 0.1) {
                shooter.nectarShoot();
            } else {
                shooter.nectarStop();
            }

            // CONTROLLER 2: POLLEN SHOOTER
            if (gamepad2.right_trigger > 0.1) {
                shooter.pollenShoot();
            } else {
                shooter.pollenStop();
            }

            // CONTROLLER 2: VECTOR LOCK TOGGLE
            if (gamepad2.left_bumper && !previousVectorLB) {
                mechanisms.toggleVectorLock();
            }
            previousVectorLB = gamepad2.left_bumper;

            // CONTROLLER 2: POLLEN LOCK TOGGLE
            if (gamepad2.right_bumper && !previousPollenRB) {
                mechanisms.togglePollenLock();
            }
            previousPollenRB = gamepad2.right_bumper;

            telemetry.addData("Intake Power", intake.getPower());
            telemetry.addData("Vector Locked",
                    mechanisms.isVectorLocked());
            telemetry.addData("Pollen Locked",
                    mechanisms.isPollenLocked());
            telemetry.update();
        }

        // Stop motors when OpMode ends
        intake.intakeStop();
        shooter.stopAll();
        mechanisms.flowerRest();
    }
}
