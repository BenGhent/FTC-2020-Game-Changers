package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp_Basic", group="Iterative Opmode")
//@Disabled
public class TeleOp_Dev extends OpMode {

    boolean inReverse=false;//reverse button is b
    boolean bWasPressed=false;

    Hardware r = new Hardware();

    @Override
    public void init() {

        r.initRobot(hardwareMap, telemetry);
        //r.deInitColor(hardwareMap);
    }

    @Override
    public void loop() {
        //int speed = 0;
        double deflator = .9;

        //this code determines what percentage of the motor power that will be used.
        if(gamepad1.right_bumper){
            deflator = .4;
        }else {
            deflator = .9;
        }

        if(gamepad1.left_bumper)
            deflator = 1;

        //legacy code that runs our mecanum drive wheels in any direction we want

        //this first section creates the variables that will be used later

        if(gamepad1.b && !bWasPressed)
            inReverse=!inReverse;
        bWasPressed=gamepad1.b;
        //first we must translate the rectangular values of the joystick into polar coordinates;
        double y=-1*gamepad1.left_stick_x;
        double x=gamepad1.left_stick_y;
        double angle=0;
        //this section uses trig to determine the angle at which the left joystick is pointing
        if(y>0 && x>0)//quadrant 1
            angle=Math.atan(y/x);
        else if(y>0 && x<0)//quadrant 2
            angle=Math.toRadians(180)+Math.atan(y/x);
        else if(y<0 && x<0)//quadrant 3
            angle=Math.toRadians(180)+Math.atan(y/x);
        else if(y<0 && x>0)//quadrant 4
            angle=Math.toRadians(360)+Math.atan(y/x);

        //this accounts for for special cases not included in the previous section
        if(y==0 && x>1)
            angle=0;
        if(y>0 && x==0)
            angle=Math.PI/2;
        if(y==0 && x<0)
            angle=Math.PI;
        if(y<0 && x==0)
            angle=3*Math.PI/2;

        double velocity=Math.sqrt(Math.pow(gamepad1.left_stick_x, 2)+Math.pow(gamepad1.left_stick_y, 2));
        double g2vel=Math.sqrt(Math.pow(gamepad2.right_stick_y,2))+Math.pow(gamepad2.right_stick_x,2);
        double rotation=gamepad1.right_stick_x*-1;
        double g2rot=gamepad1.right_stick_x*-1;

        if(inReverse)//reverse button
            angle+=Math.toRadians(180);

        angle+=Math.toRadians(270);

        //equations taking the polar coordinates and turning them into motor powers
        double power1=velocity*Math.cos(angle+(Math.PI/4))-rotation;
        double power2=velocity*Math.sin(angle+(Math.PI/4))+rotation;
        double power3=velocity*Math.sin(angle+(Math.PI/4))-rotation;
        double power4=velocity*Math.cos(angle+(Math.PI/4))+rotation;
        r.frontLeft.setPower(power1 * deflator);
        r.frontRight.setPower(power2 * deflator);
        r.backLeft.setPower(power3 * deflator);
        r.backRight.setPower(power4 * deflator);

        /*=============================================
        *
        * =================Custom code=================
        *
        * =============================================*/

        if(gamepad2.left_bumper){//rotate to the optimal angle to get ring in goal
            r.Angle();
        }

        if(gamepad2.left_bumper && gamepad2.a){//If both the angle and the firing button is pressed, fire the ring
            r.fire();
        }

        if(gamepad2.a || gamepad1.a && !gamepad2.left_bumper){
            //rotate the loading platform to the opposite position && start or stop the loading motors
        }

    }
}
