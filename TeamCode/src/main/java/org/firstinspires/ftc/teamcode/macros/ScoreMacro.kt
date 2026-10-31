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
    private var state = State.STOPPED
    private var avgSmallRPM = 0.0
    private var avgLargeRPM = 0.0
    private var smallRPMS = arrayOf(
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
    )
    private var largeRPMS = arrayOf(
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
    )

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
        telemetry.addData("s_avg", avgSmallRPM)
        telemetry.addData("l_avg", avgLargeRPM)
        telemetry.addData("state", state)
    }

    fun stop() {
        state = State.STOPPED
    }

    private fun doneSpinningUp(): Boolean {

        avgSmallRPM = smallRPMS.sum() / smallRPMS.size
        avgLargeRPM = largeRPMS.sum() / largeRPMS.size

        // dont use outdated junk data
        smallRPMS.set(0, smallRPMS.get(1))
        smallRPMS.set(1, smallRPMS.get(2))
        smallRPMS.set(2, smallRPMS.get(3))
        smallRPMS.set(3, smallRPMS.get(4))
        smallRPMS.set(4, shooters.getSmallRPM())

        largeRPMS.set(0, largeRPMS.get(1))
        largeRPMS.set(1, largeRPMS.get(2))
        largeRPMS.set(2, largeRPMS.get(3))
        largeRPMS.set(3, largeRPMS.get(4))
        largeRPMS.set(4, shooters.getLargeRPM())

        return (avgSmallRPM >= Config.SMALL_SHOOTER_RPM - 500.0
            && avgSmallRPM <= Config.SMALL_SHOOTER_RPM + 500.0
            && avgLargeRPM >= Config.LARGE_SHOOTER_RPM - 500.0
            && avgLargeRPM <= Config.LARGE_SHOOTER_RPM + 500.0
        )
    }
}