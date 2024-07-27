package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Robot: MyTeleop", group="Robot")
@Disabled
public class MyTeleop extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor thisIsAMotor = null;
    public Servo thisIsAServo = null;

    public double motorControl = 0.0;
    public double servoControl = 0.5;

    public static double SERVO_INIT_POS = 0.5;
    public static double SERVO_SPEED = 0.01;

    @Override
    public void runOpMode() {

        // Define and Initialize Motors
        thisIsAMotor = hardwareMap.get(DcMotor.class, "motorName");
        thisIsAMotor.setDirection(DcMotor.Direction.FORWARD);
        // thisIsAMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize ALL installed servos.
        thisIsAServo = hardwareMap.get(Servo.class, "servoName");
        thisIsAServo.setPosition(SERVO_INIT_POS);

        // Send telemetry message to signify robot waiting;
        telemetry.addData(">", "Robot Ready.  Press Play.");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Gamepad input is built into LinearOpMode
            motorControl = gamepad1.right_stick_y;
            thisIsAMotor.setPower(motorControl);

            servoControl += gamepad1.left_stick_y * SERVO_SPEED;
            //clamp the servo position between 0.0 and 1.0
            if(servoControl > 1.0) {
                servoControl = 1.0;
            }
            if(servoControl < 0.0){
                servoControl = 0.0;
            }
            thisIsAServo.setPosition(servoControl);

            // Send telemetry message to signify robot running;
            telemetry.addData("motorControl",  "%.2f", motorControl);
            telemetry.update();

            // Pace this loop so it run at a reasonable speed.
            sleep(50);
        }
    }
}
