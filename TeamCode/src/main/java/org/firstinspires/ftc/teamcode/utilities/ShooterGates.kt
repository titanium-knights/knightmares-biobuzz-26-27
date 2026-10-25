package org.firstinspires.ftc.teamcode.utilities

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry

class ShooterGates(hmap: HardwareMap, telemetry: Telemetry) {
    private val smallGateServo: Servo = hmap.get(Servo::class.java, CONFIG.SMALL_GATE_SERVO)
    private val largeGateServo: Servo = hmap.get(Servo::class.java, CONFIG.LARGE_GATE_SERVO)

    companion object {
        const val POSITION_OPEN: Double = 0.0 // 0 degrees
        const val POSITION_CLOSED: Double = 0.5 // 90 degrees
    }

    init {
        smallGateServo.position = POSITION_CLOSED
        largeGateServo.position = POSITION_CLOSED
    }
    // TODO: EVERYTHING
    fun openSmallGate() {
        smallGateServo.position = POSITION_OPEN
    }

    fun openLargeGate() {
        largeGateServo.position = POSITION_OPEN
    }

    fun closeSmallGate() {
        smallGateServo.position = POSITION_CLOSED
    }

    fun closeLargeGate() {
        largeGateServo.position = POSITION_CLOSED
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
        smallGateServo.position = position.coerceIn(0.0, 1.0)
    }

    fun setLargePosition(position: Double) {
        smallGateServo.position = position.coerceIn(0.0, 1.0)
    }
}