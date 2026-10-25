package org.firstinspires.ftc.teamcode.auton

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.auton.timebased.ShootPark
import org.firstinspires.ftc.teamcode.utilities.CONFIG
import org.firstinspires.ftc.teamcode.utilities.SubsystemManager

open class AutonMethods : OpMode() {
    var subsystemManager: SubsystemManager? = null

    override fun init() {
        subsystemManager = SubsystemManager(hardwareMap, telemetry)
    }

    override fun loop() {}


    /** blocking methods */
    /**
     * this is blocking and uses `@JvmOverloads` to make constructors with defaults
     */
//    @JvmOverloads
//    fun outtakeForTime(seconds: Double = 2.5) {
//        subsystemManager!!.outtake.outtakeRun()
//        val timer = ElapsedTime()
//        timer.reset()
//        while (timer.seconds() < seconds) {
//            /*
//               Time, Dr. Freeman? Is it really that time again? It seems as if you only just arrived. You've done a great deal in a small time span. You've done so well, in fact, that I've received some interesting offers for your services.
//
//               Ordinarily, I wouldn't contemplate them... but these *are* extraordinary times.
//
//               Rather than offer you the illusion of free choice, I will take the liberty of choosing for you... if and when your time comes round again. I do apologize for what must seem to you an arbitrary imposition, Dr. Freeman. I trust it will all make sense to you in the course of... well... I'm really not at liberty to say. In the meantime... this is where I get off.
//            */
//        }
//        subsystemManager!!.outtake.outtakeStop()
//    }

    private fun rotateHelper(turn: Double) {
        subsystemManager!!.drive.move(0.0, 0.0, turn);
        val timer = ElapsedTime()
        timer.reset()
        while (timer.seconds() < CONFIG.SALS_TURNING_CONSTANT) {
            // hi
        }
        subsystemManager!!.drive.move(0.0,0.0,0.0)
    }

    fun rotateCW(degrees: Double) {
        rotateHelper(1.0)
    }

    fun rotateCCW(degrees: Double) {
        rotateHelper(-1.0)
    }

    fun goHelper(howMuchIn: Double, x: Double, y: Double, constant: Double) {
        subsystemManager!!.drive.move(x, y,0.0)
        val timer = ElapsedTime()
        timer.reset()
        while (timer.seconds() < constant) {
            // hi
        }
        subsystemManager!!.drive.move(0.0,0.0,0.0)
    }

    fun goForward(howMuchIN: Double) {
        goHelper(howMuchIN, 0.0, 0.5, CONFIG.SALS_Y_CONSTANT)
        updateTelemetryCounter()
    }
    fun goBackward(howMuchIN: Double) {
        goHelper(howMuchIN, 0.0, 0.5, CONFIG.SALS_Y_CONSTANT)
        updateTelemetryCounter()
    }
    fun goLeft(howMuchIN: Double) {
        goHelper(howMuchIN, -0.5, 0.0, CONFIG.SALS_X_CONSTANT)
        updateTelemetryCounter()
    }
    fun goRight(howMuchIN: Double) {
        goHelper(howMuchIN, 0.5, 0.0, CONFIG.SALS_X_CONSTANT)
        updateTelemetryCounter()
    }

    fun updateTelemetryCounter() {
        counter++
        telemetry.addLine("counter: $counter")
        telemetry.update()
    }

    fun boomShakaLaka() {
        subsystemManager!!.shooters.shootBoth()

        var iterCounter = 0
        var avgSmallRPM = 0
        var avgLargeRPM = 0
        var smallIsSatisfactory = false
        var largeIsSatisfactory = false

        while (!smallIsSatisfactory && !largeIsSatisfactory) {

            counter++
        }
        subsystemManager!!.shooters.stopBoth()
    }

    fun stopShakaLaka() {


        subsystemManager!!.shooters.shootBoth()
    }


    /** non-blocking methods below */
//    @JvmOverloads
//    fun intakeStart(override: Double = 0.9) {
//        subsystemManager!!.intake.intakeRun();
//    }

//    fun intakeReverse() {
//        subsystemManager!!.intake.intakeReversed();
//    }


//    fun intakeStop() {
//        subsystemManager!!.intake.intakeStop();
//    }

//    @JvmOverloads
//    fun outtakeStart(override: Double = 0.43) {
//        subsystemManager!!.outtake.outtakeRun(override);
//    }

//    fun outtakeStop() {
//        subsystemManager!!.outtake.outtakeStop();
//    }
    companion object {
        private var counter: Double = 0.0
    }
}