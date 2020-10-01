package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="BasicAuto_Linear", group="Linear Opmode")
//@Disabled
public class RedBlue_Basic_Auto extends LinearOpMode {

    Hardware r = new Hardware();

    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "TeleOp_4");
        r.initRobot(hardwareMap, telemetry);
        r.initAutonomous();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        r.driveBackwardEncoder(.5,33);

    }
}
