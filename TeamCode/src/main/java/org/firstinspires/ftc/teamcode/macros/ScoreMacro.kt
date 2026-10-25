package org.firstinspires.ftc.teamcode.macros

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.config.Config
import org.firstinspires.ftc.teamcode.subsystems.ShooterGates
import org.firstinspires.ftc.teamcode.subsystems.Shooters

class ScoreMacro(
    hmap: HardwareMap,
    private val telemetry: Telemetry,
    private val shooters: Shooters,
    private val gates: ShooterGates
) {
    private var counter = 0
    private var state = State.STOPPED
    private var avgSmallRPM = 0.0
    private var avgLargeRPM = 0.0


    private enum class State {
        IDLE, STOPPED, SPIN_UP, SPINNING_UP, FEEDING,
    }

    companion object

    fun start() {
        state = State.SPIN_UP
    }

    fun update() {
//        when (state) {
//            State.IDLE -> {
//                telemetry.addLine("STOPPING SHOOTING")
//            }
//            State.SPINNING_UP -> {
//                telemetry.addLine("SPINNING UP")
//
//                if (doneSpinningUp()) {
//                    gates.openBoth()
//                }
//            }
//            State.FEEDING -> {
//                telemetry.addLine("FEEDING SHOOTERS")
//
//                state = State.STOPPED
//            }
//            State.STOPPED -> {
//                telemetry.addLine("STOPPING SHOOTING")
//                shooters.stopBoth()
//                gates.closeBoth()
//                state = State.IDLE
//            }
//        }
        if (state == State.SPIN_UP) {
            shooters.shootBoth()

            counter = 0
            avgSmallRPM = 0.0
            avgLargeRPM = 0.0

            state = State.SPINNING_UP
        }
        if (state == State.SPINNING_UP) {
            if (doneSpinningUp()) {
                state = State.FEEDING
            }
        }
        if (state == State.FEEDING) {
            gates.openBoth()
            state = State.IDLE
        }
        if (state == State.STOPPED) {
            gates.closeBoth()
            shooters.stopBoth()
        }
    }

    fun stop() {
        state = State.STOPPED
    }

    private fun doneSpinningUp(): Boolean {

        avgSmallRPM = (avgSmallRPM * counter + shooters.getSmallRPM()) / counter + 1
        avgLargeRPM = (avgLargeRPM * counter + shooters.getLargeRPM()) / counter + 1

        // dont use outdated junk data
        if (counter > 5) {
            counter = 0
            avgSmallRPM = 0.0
            avgLargeRPM = 0.0
        }

        counter++

        return (
            avgSmallRPM >= Config.SMALL_SHOOTER_RPM - 100.0
            && avgSmallRPM <= Config.SMALL_SHOOTER_RPM + 100.0
            && avgLargeRPM >= Config.LARGE_SHOOTER_RPM - 100.0
            && avgLargeRPM <= Config.LARGE_SHOOTER_RPM + 100.0
        )

    }
}