package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name="Dev_Auto", group="Linear Opmode")
//@Disable
public class Dev_Auto extends LinearOpMode {//This will test the new methods for the auto programs, will not use in comp
    Hardware r = new Hardware();

    Auto a = new Auto();

    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "TeleOp_Basic");
        r.initRobot(hardwareMap, telemetry);
        r.initAutonomous();

        r.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        do{
            r.frontLeft.setPower(0.6);//Hello
        }while(opModeIsActive());



    }
}
