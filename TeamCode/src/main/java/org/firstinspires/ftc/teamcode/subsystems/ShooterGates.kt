package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.config.Config

class ShooterGates(hmap: HardwareMap, telemetry: Telemetry) {
    private val smallGateServo: Servo = hmap.get(Servo::class.java, Config.SMALL_GATE_SERVO)
    private val largeGateServo: Servo = hmap.get(Servo::class.java, Config.LARGE_GATE_SERVO)

    companion object {
        const val S_OPEN: Double = 0.4
        const val S_CLOSED: Double = 0.5
        const val L_OPEN: Double = 0.7
        const val L_CLOSE: Double = 0.6
    }

    init {
        smallGateServo.position = S_CLOSED
        largeGateServo.position = L_OPEN
    }

    fun openSmallGate() {
        smallGateServo.position = S_OPEN
    }

    fun openLargeGate() {
        largeGateServo.position = L_OPEN
    }

    fun closeSmallGate() {
        smallGateServo.position = S_CLOSED
    }

    fun closeLargeGate() {
        largeGateServo.position = L_CLOSE
    }

    fun closeBoth() {
        closeSmallGate()
        closeLargeGate()
    }

    fun openBoth() {
        openSmallGate()
        openLargeGate()
    }

    fun setSmallPosition(position: Double) {
        smallGateServo.position = position.coerceIn(-1.0, 1.0)
    }

    fun setLargePosition(position: Double) {
        largeGateServo.position = position.coerceIn(-1.0, 1.0)
    }
}