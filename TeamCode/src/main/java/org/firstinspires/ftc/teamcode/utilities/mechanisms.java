
package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class mechanisms {

    public Servo flowerPower;
    public Servo vectorLock;
    public Servo pollenLock;

    // Servo positions: adjust after testing on the robot.
    public static double flowerRestPosition = 0.0;
    public static double flowerActivePosition = 1.0;

    public static double vectorUnlockedPosition = 0.0;
    public static double vectorLockedPosition = 1.0;

    public static double pollenUnlockedPosition = 0.0;
    public static double pollenLockedPosition = 1.0;

    private boolean vectorIsLocked = false;
    private boolean pollenIsLocked = false;

    public mechanisms(HardwareMap hmap) {
        flowerPower = hmap.get(Servo.class, "flowerPower");
        vectorLock = hmap.get(Servo.class, "vectorLock");
        pollenLock = hmap.get(Servo.class, "pollenLock");

        // Initial positions
        flowerPower.setPosition(flowerRestPosition);
        vectorLock.setPosition(vectorUnlockedPosition);
        pollenLock.setPosition(pollenUnlockedPosition);
    }

    // Flower Power: active while held
    public void flowerActivate() {
        flowerPower.setPosition(flowerActivePosition);
    }

    public void flowerRest() {
        flowerPower.setPosition(flowerRestPosition);
    }

    // Vector Lock: toggle between locked and unlocked
    public void toggleVectorLock() {
        vectorIsLocked = !vectorIsLocked;

        vectorLock.setPosition(
                vectorIsLocked
                        ? vectorLockedPosition
                        : vectorUnlockedPosition
        );
    }

    // Pollen Lock: toggle between locked and unlocked
    public void togglePollenLock() {
        pollenIsLocked = !pollenIsLocked;

        pollenLock.setPosition(
                pollenIsLocked
                        ? pollenLockedPosition
                        : pollenUnlockedPosition
        );
    }

    public boolean isVectorLocked() {
        return vectorIsLocked;
    }

    public boolean isPollenLocked() {
        return pollenIsLocked;
    }
}
