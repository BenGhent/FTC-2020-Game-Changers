package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.CV;

@Autonomous(name="Dev_Auto", group="Linear Opmode")
//@Disable
public class Dev_Auto extends LinearOpMode {//This will test the new methods for the auto programs, will not use in comp
    Hardware r = new Hardware();

    Auto a = new Auto();

    Thread CV = new CV();

    @Override
    public void runOpMode() {
        CV.start();

        r.setDriveMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        do{
            if(gamepad1.a != false) {
                CV.stop();
            }
            r.frontLeft.setPower(0.6);
        }while(CV.isAlive());
    }

}
