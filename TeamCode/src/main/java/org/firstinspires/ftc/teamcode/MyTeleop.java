package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Robot: MyTeleop", group="Robot")
public class MyTeleop extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor left_drive = null;
    public DcMotor right_drive = null;

    public Servo thisIsAServo = null;

    public double servoControl = 0.5;

    public static double SERVO_INIT_POS = 0.5;
    public static double SERVO_SPEED = 0.05;

    @Override
    public void runOpMode() {

        // Define and Initialize Motors
        left_drive = hardwareMap.get(DcMotor.class, "left_drive");
        right_drive = hardwareMap.get(DcMotor.class, "right_drive");

        left_drive.setDirection(DcMotor.Direction.FORWARD);
//         thisIsAMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
            left_drive.setPower(gamepad1.right_stick_y);
            right_drive.setPower(gamepad1.left_stick_y);

            if(gamepad1.dpad_up){
                servoControl += SERVO_SPEED;
            }
            if(gamepad1.dpad_down){
                servoControl -= SERVO_SPEED;
            }
            //clamp the servo position between 0.0 and 1.0
            if(servoControl > 1.0) {
                servoControl = 1.0;
            }
            if(servoControl < 0.0){
                servoControl = 0.0;
            }
            thisIsAServo.setPosition(servoControl);

            // Send telemetry message to signify robot running;
            telemetry.addData("servoControl",  "%.2f", servoControl);
            telemetry.update();

            // Pace this loop so it run at a reasonable speed.
            sleep(50);
        }
    }
}
