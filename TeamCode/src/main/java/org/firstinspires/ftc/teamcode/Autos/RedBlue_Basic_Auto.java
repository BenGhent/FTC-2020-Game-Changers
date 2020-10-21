package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name="Red_Blue_Basic_Auto", group="Linear Opmode")
//@Disabled
public class RedBlue_Basic_Auto extends LinearOpMode {

    Hardware r = new Hardware();

    Auto a = new Auto();

    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "TeleOp_Basic");
        r.initRobot(hardwareMap, telemetry);
        r.initAutonomous();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

    }
}
