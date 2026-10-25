package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.macros.ScoreMacro;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterGates;
import org.firstinspires.ftc.teamcode.subsystems.Shooters;
import org.firstinspires.ftc.teamcode.subsystems.SimpleMecanumDrive;


/**
 * This class is used to encapsulate all utility classes for subsystems.
 * Any OpMode (teleop or auton) can initialize this class and access any utility class
 * The instance of this object should be passed to any TeleopState to provide access to utility classes
 * This prevents the need to pass references to several utility classes to each TeleopState
 */

public class SubsystemManager {
    public final SimpleMecanumDrive drive;
    public final Shooters shooters;
    public final Intake intake;
    public final ShooterGates gates;
    public final ScoreMacro scoreMacro;

    public SubsystemManager(HardwareMap hmap, Telemetry telemetry) {
        // add util class initializations here
        drive = new SimpleMecanumDrive(hmap, telemetry);
        shooters = new Shooters(hmap, telemetry);
        intake = new Intake(hmap, telemetry);
        gates = new ShooterGates(hmap, telemetry);

        scoreMacro = new ScoreMacro(hmap, telemetry, shooters, gates);
    }
}
