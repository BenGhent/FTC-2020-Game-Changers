package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name="Blue_Basic_Auto", group="Linear Opmode")
//@Disable
public class Blue_Basic_Auto extends LinearOpMode {

    Hardware r = new Hardware();

    Auto a = new Auto();

    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "TeleOp_Basic");
        r.initRobot(hardwareMap, telemetry);
        r.initAutonomous();

        r.side = 0; //Blue side

        a.init();

        r.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double angle;

        r.frontLeft.setTargetPosition((int) (r.angle(100, 200, 30)* r.ticksPerDeg));

        r.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        do{
            r.frontLeft.setPower(0.5);
        }while(
                r.frontLeft.getCurrentPosition() != r.frontLeft.getTargetPosition()
        );


    }
}
