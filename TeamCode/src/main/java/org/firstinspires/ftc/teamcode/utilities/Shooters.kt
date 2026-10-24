package org.firstinspires.ftc.teamcode.utilities

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit

class Shooters(hmap: HardwareMap, private val telemetry: Telemetry) {
    var smallShooter: DcMotorEx
    var largeShooter: DcMotorEx

    init {
        this.smallShooter = hmap.get(DcMotorEx::class.java, CONFIG.SMALL_SHOOTER)
        this.smallShooter.direction = CONFIG.SMALL_SHOOTER_DIRECTION
        this.smallShooter.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        this.smallShooter.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        this.smallShooter.mode = DcMotor.RunMode.RUN_USING_ENCODER

        this.largeShooter = hmap.get(DcMotorEx::class.java, CONFIG.LARGE_SHOOTER)
        this.largeShooter.direction = CONFIG.LARGE_SHOOTER_DIRECTION
        this.largeShooter.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        this.largeShooter.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        this.largeShooter.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    companion object {
        const val DEFAULT_SMALL_VELO: Double = 2800.0
        const val DEFAULT_LARGE_VELO: Double = 2800.0
        const val ENCODER_TPR: Double = 28.0 // Ticks Per Revolution (6000 RPM 1:1 motor)

        fun rpmToTicks(rpm: Double): Double {
            return (rpm * ENCODER_TPR) / 60.0
        }
    }

    @JvmOverloads
    fun shootSmall(targetRPM: Double = DEFAULT_SMALL_VELO) {
        smallShooter.velocity = rpmToTicks(targetRPM)
        telem(telemetry, "smallShooter", smallShooter)
    }

    fun stopSmall() {
        smallShooter.power = 0.0
    }

    @JvmOverloads
    fun shootLarge(targetRPM: Double = DEFAULT_LARGE_VELO) {
        largeShooter.velocity = rpmToTicks(targetRPM)
        telem(telemetry, "largeShooter", largeShooter)
    }

    fun stopLarge() {
        largeShooter.power = 0.0
    }

    private fun telem(telemetry: Telemetry, label: String, shooter: DcMotorEx) {
        val ticks = shooter.velocity
        val rpm = (ticks * 60.0) / ENCODER_TPR

        telemetry.addLine("--- $label ---")
        telemetry.addData("$label Ticks/sec", "%.2f", ticks)
        telemetry.addData("$label RPM", "%.2f", rpm)
        telemetry.addData("$label Deg/sec", "%.2f", shooter.getVelocity(AngleUnit.DEGREES))
    }
}