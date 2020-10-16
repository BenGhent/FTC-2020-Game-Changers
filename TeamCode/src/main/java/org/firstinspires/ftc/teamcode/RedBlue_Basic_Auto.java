package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREV2mDistance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Red_Blue_Basic_Auto", group="Linear Opmode")
//@Disabled
public class RedBlue_Basic_Auto extends LinearOpMode {

    Hardware r = new Hardware();

    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "TeleOp_Basic");
        r.initRobot(hardwareMap, telemetry);
        r.initAutonomous();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //3.1 per deg

        r.frontLeft.setTargetPosition(558);

        r.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        do r.frontLeft.setPower(0.6); while(
            r.frontLeft.getCurrentPosition() < r.frontLeft.getTargetPosition()
        );

    }
}
