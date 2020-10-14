/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREV2mDistance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Hardware {
  //created by team 9161 overload on 1/31/20

    /*
    *
    * To add DC motor, use "public DCMotor %var name%;"
    *
    * To add Servo, use "public Servo %var name%;"
    *
    * ====!then refer to initHardware()!====
    *
    * */

  public DcMotor frontLeft;    // Front Left drive motor
  public DcMotor backLeft;     // Back Left drive motor
  public DcMotor frontRight;   // Front Right drive motor
  public DcMotor backRight;    // Back Right drive motor

  public DcMotor LauncherLeft; // Left Firing spin wheel motor
  public DcMotor LauncherRight;// Right Firing spin wheel motor
  public DcMotor LaunchAngle;  // Motor that drives the angle of the launcher

  public DcMotor loadRotate;   // Motor that drives the angle of the loader
  public DcMotor loadLoader;   // Loading spin wheel motor

  public Servo LaunchPist;      // launching piston for firing mechanism

  public DistanceSensor Dist;  // Distance sensor to detect distance to the goal

  public SensorREV2mDistance di;

  Telemetry telemetry;
  HardwareMap hwMap;

  ElapsedTime timer = new ElapsedTime();

  public static final int ticksPerInch=56;

  public static final double ticksPerDeg=3.6; //the number of ticks it takes for the axle of the motor to rotate 90 deg

  public static final int encoderSafeZone=50;/*a motor must be within this many ticks of its
   target to be considered "on target"*/

  public static final int minRotDist = 0;


  public void initRobot(HardwareMap spareMap, Telemetry tempTelemetry){
   hwMap = spareMap;
   telemetry = tempTelemetry;
   initHardware();
  }

  public void initHardware() {
  /*
  *
  * To init a DC motor, use %var name% = hwMap.dcMotor.get("%name of motor%");
  * To set the direction of DC motor, use %var name%.setDirection(DcMotorSimple.Direction.REVERSE);
  *                                                                                       FORWARDS
  *
  * L = Left
  * R = Right
  * F = Front
  * B = Back
  * M = Motor
  *
  * I = intake
  * Lau = Launcher
  *
  *
  *  ___        _____
  * |   |       |   |
  * |FLM|       |FRM|
  * |   ‾‾‾‾‾‾‾‾    |
  * |   ________    |
  * |BLM|       |BRM|
  * |   |       |   |
  * ‾‾‾‾‾       ‾‾‾‾‾
  *
  *
  * To map servo, use %var name% = hwMap.servo.get("%name of servo%");
  *
  * */
   //Wheels
   frontLeft = hwMap.dcMotor.get("FLM");
   frontRight = hwMap.dcMotor.get("FRM");
   backLeft = hwMap.dcMotor.get("BLM");
   backRight = hwMap.dcMotor.get("BRM");

   frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
   backRight.setDirection(DcMotorSimple.Direction.REVERSE);

   //Fly wheels
   LauncherLeft = hwMap.dcMotor.get("LLM");
   LauncherRight = hwMap.dcMotor.get("LRM");

   loadLoader = hwMap.dcMotor.get("LM");

   LauncherRight.setDirection(DcMotorSimple.Direction.REVERSE);

   //Rotators
   LaunchAngle = hwMap.dcMotor.get("LAM");
   loadRotate = hwMap.dcMotor.get("LoAM");

   //Pistons
   LaunchPist = hwMap.servo.get("Pist");

   //Sensors
   Dist = hwMap.get(DistanceSensor.class, "FDist"); // Distance sensor

   LaunchAngle.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }


  // be sure to init all servos in initAutonomous()
  public void initAutonomous(){
   setDriveMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   waiter(500);
  }

  /*=======================================
  *
  * =============Do not edit===============
  *
  * =======================================*/

  public void setDriveMotorMode(DcMotor.RunMode mode) {
   switch (mode) {
    case RUN_USING_ENCODER:
     if (frontLeft.getMode() == DcMotor.RunMode.RUN_USING_ENCODER)
      break;
     frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     break;
    case RUN_WITHOUT_ENCODER:
     if (frontLeft.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
      break;
     frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
     frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
     backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
     backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
     break;
    case STOP_AND_RESET_ENCODER:
     if (frontLeft.getMode() == DcMotor.RunMode.STOP_AND_RESET_ENCODER)
      break;
     frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     break;
    case RUN_TO_POSITION:
     if (frontLeft.getMode() == DcMotor.RunMode.RUN_TO_POSITION)
      break;
     frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     break;
   }
  }

  //methods to set the four drive wheels in specific directions
  public void setMotorEncoderForward(int distance){
   frontLeft.setTargetPosition(distance);
   frontRight.setTargetPosition(distance);
   backLeft.setTargetPosition(distance);
   backRight.setTargetPosition(distance);
  }
  public void setMotorEncoderBackward(int distance) {
   frontLeft.setTargetPosition(-distance);
   frontRight.setTargetPosition(-distance);
   backLeft.setTargetPosition(-distance);
   backRight.setTargetPosition(-distance);
  }
  public void setMotorEncoderLeft(int distance) {
   frontLeft.setTargetPosition(-distance);
   frontRight.setTargetPosition(distance);
   backLeft.setTargetPosition(distance);
   backRight.setTargetPosition(-distance);
  }
  public void setMotorEncoderRight(int distance) {
   frontLeft.setTargetPosition(distance);
   frontRight.setTargetPosition(-distance);
   backLeft.setTargetPosition(-distance);
   backRight.setTargetPosition(distance);
  }
  public void setMotorEncoderClockwise(int distance) {
   frontLeft.setTargetPosition(distance);
   frontRight.setTargetPosition(-distance);
   backLeft.setTargetPosition(distance);
   backRight.setTargetPosition(-distance);
  }
  public void setMotorEncoderCounterwise(int distance) {
   frontLeft.setTargetPosition(-distance);
   frontRight.setTargetPosition(distance);
   backLeft.setTargetPosition(-distance);
   backRight.setTargetPosition(distance);
  }

  public void setToForward(double power) {
   frontLeft.setPower(power);
   frontRight.setPower(power);
   backLeft.setPower(power);
   backRight.setPower(power);
  }
  public void setToBackward(double power) {
   frontLeft.setPower(-1 * power);
   frontRight.setPower(-1 * power);
   backLeft.setPower(-1 * power);
   backRight.setPower(-1 * power);
  }
  public void setToCounterwise(double power) {
   frontLeft.setPower(-1 * power);
   frontRight.setPower(1 * power);
   backLeft.setPower(-1 * power);
   backRight.setPower(1 * power);
  }
  public void setToClockwise(double power) {
   frontLeft.setPower(1 * power);
   frontRight.setPower(-1 * power);
   backLeft.setPower(1 * power);
   backRight.setPower(-1 * power);
  }
  public void setToRight(double power) {
   frontLeft.setPower(1 * power);
   frontRight.setPower(-1 * power);
   backLeft.setPower(-1 * power);
   backRight.setPower(1 * power);
  }
  public void setToLeft(double power) {
   frontLeft.setPower(-1 * power);
   frontRight.setPower(1 * power);
   backLeft.setPower(1 * power);
   backRight.setPower(-1 * power);
  }
  public void setToStill() {
   frontLeft.setPower(0);
   frontRight.setPower(0);
   backLeft.setPower(0);
   backRight.setPower(0);
  }

  public void driveForwardEncoder(double power, int distance) {

   int frontLDist, frontRDist, backLDist, backRDist;
   setMotorEncoderForward(distance*ticksPerInch+frontLeft.getCurrentPosition());
   setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

   setToForward(power);
   do{
    frontLDist=Math.abs(frontLeft.getTargetPosition()-frontLeft.getCurrentPosition());
    frontRDist=Math.abs(frontRight.getTargetPosition()-frontRight.getCurrentPosition());
    backLDist=Math.abs(backLeft.getTargetPosition()-backLeft.getCurrentPosition());
    backRDist=Math.abs(backRight.getTargetPosition()-backRight.getCurrentPosition());

    telemetry.addData("frontLeft distanceFrom: ", frontLeft.getCurrentPosition());
    telemetry.addData("frontRight distanceFrom: ",frontRight.getCurrentPosition());
    telemetry.addData("backLeft distanceFrom: ",backLeft.getCurrentPosition());
    telemetry.addData("backRight distanceFrom: ",backRight.getCurrentPosition());
    telemetry.update();
   }while(
           frontLDist>encoderSafeZone &&
                   frontRDist>encoderSafeZone &&
                   backLDist>encoderSafeZone &&
                   backRDist>encoderSafeZone
   );
   setToStill();
  }
  public void driveBackwardEncoder(double power, int distance) {
   int frontLDist, frontRDist, backLDist, backRDist;
   setMotorEncoderBackward(distance*ticksPerInch+frontLeft.getCurrentPosition());
   setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

   setToForward(power);
   do{
    frontLDist=Math.abs(frontLeft.getTargetPosition()-frontLeft.getCurrentPosition());
    frontRDist=Math.abs(frontRight.getTargetPosition()-frontRight.getCurrentPosition());
    backLDist=Math.abs(backLeft.getTargetPosition()-backLeft.getCurrentPosition());
    backRDist=Math.abs(backRight.getTargetPosition()-backRight.getCurrentPosition());

    telemetry.addData("frontLeft distanceFrom: ",frontLDist);
    telemetry.addData("frontRight distanceFrom: ",frontRDist);
    telemetry.addData("backLeft distanceFrom: ",backLDist);
    telemetry.addData("backRight distanceFrom: ",backRDist);
    telemetry.update();
   }while(
           frontLDist>encoderSafeZone &&
                   frontRDist>encoderSafeZone &&
                   backLDist>encoderSafeZone &&
                   backRDist>encoderSafeZone
   );
   setToStill();
  }
  public void driveLeftEncoder(double power, int distance) {

   int frontLDist, frontRDist, backLDist, backRDist;
   setMotorEncoderLeft(distance*ticksPerInch+frontLeft.getCurrentPosition());
   setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

   setToForward(power);
   do{
    frontLDist=Math.abs(frontLeft.getTargetPosition()-frontLeft.getCurrentPosition());
    frontRDist=Math.abs(frontRight.getTargetPosition()-frontRight.getCurrentPosition());
    backLDist=Math.abs(backLeft.getTargetPosition()-backLeft.getCurrentPosition());
    backRDist=Math.abs(backRight.getTargetPosition()-backRight.getCurrentPosition());

    telemetry.addData("frontLeft distanceFrom: ",frontLDist);
    telemetry.addData("frontRight distanceFrom: ",frontRDist);
    telemetry.addData("backLeft distanceFrom: ",backLDist);
    telemetry.addData("backRight distanceFrom: ",backRDist);
    telemetry.update();
   }while(
           frontLDist>encoderSafeZone &&
                   frontRDist>encoderSafeZone &&
                   backLDist>encoderSafeZone &&
                   backRDist>encoderSafeZone
   );
   setToStill();
  }
  public void driveRightEncoder(double power, int distance) {
   int frontLDist, frontRDist, backLDist, backRDist;
   setMotorEncoderRight(distance*ticksPerInch+frontLeft.getCurrentPosition());
   setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

   setToForward(power);
   do{
    frontLDist=Math.abs(frontLeft.getTargetPosition()-frontLeft.getCurrentPosition());
    frontRDist=Math.abs(frontRight.getTargetPosition()-frontRight.getCurrentPosition());
    backLDist=Math.abs(backLeft.getTargetPosition()-backLeft.getCurrentPosition());
    backRDist=Math.abs(backRight.getTargetPosition()-backRight.getCurrentPosition());

    telemetry.addData("frontLeft distanceFrom: ",frontLDist);
    telemetry.addData("frontRight distanceFrom: ",frontRDist);
    telemetry.addData("backLeft distanceFrom: ",backLDist);
    telemetry.addData("backRight distanceFrom: ",backRDist);
    telemetry.update();
   }while(
           frontLDist>encoderSafeZone &&
                   frontRDist>encoderSafeZone &&
                   backLDist>encoderSafeZone &&
                   backRDist>encoderSafeZone
   );
   setToStill();
  }
  public void turnClockwiseEncoder(double power, int distance) {
   int frontLDist, frontRDist, backLDist, backRDist;
   setMotorEncoderClockwise(distance*ticksPerInch+frontLeft.getCurrentPosition());
   setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

   setToForward(power);
   do{
    frontLDist=Math.abs(frontLeft.getTargetPosition()-frontLeft.getCurrentPosition());
    frontRDist=Math.abs(frontRight.getTargetPosition()-frontRight.getCurrentPosition());
    backLDist=Math.abs(backLeft.getTargetPosition()-backLeft.getCurrentPosition());
    backRDist=Math.abs(backRight.getTargetPosition()-backRight.getCurrentPosition());

    telemetry.addData("frontLeft distanceFrom: ",frontLDist);
    telemetry.addData("frontRight distanceFrom: ",frontRDist);
    telemetry.addData("backLeft distanceFrom: ",backLDist);
    telemetry.addData("backRight distanceFrom: ",backRDist);
    telemetry.update();
   }while(
           frontLDist>encoderSafeZone &&
                   frontRDist>encoderSafeZone &&
                   backLDist>encoderSafeZone &&
                   backRDist>encoderSafeZone
   );
   setToStill();
  }
  public void turnCounterwiseEncoder(double power, int distance) {
   int frontLDist, frontRDist, backLDist, backRDist;
   setMotorEncoderCounterwise(distance * ticksPerInch + frontLeft.getCurrentPosition());
   setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

   setToForward(power);
   do {
    frontLDist = Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition());
    frontRDist = Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition());
    backLDist = Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition());
    backRDist = Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition());

    telemetry.addData("frontLeft distanceFrom: ", frontLDist);
    telemetry.addData("frontRight distanceFrom: ", frontRDist);
    telemetry.addData("backLeft distanceFrom: ", backLDist);
    telemetry.addData("backRight distanceFrom: ", backRDist);
    telemetry.update();
   } while (
           frontLDist > encoderSafeZone &&
                   frontRDist > encoderSafeZone &&
                   backLDist > encoderSafeZone &&
                   backRDist > encoderSafeZone
   );
   setToStill();
  }

  public void setDriveMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
   switch(behavior) {
    case BRAKE:
     if(frontLeft.getZeroPowerBehavior()==DcMotor.ZeroPowerBehavior.BRAKE)
      break;
     frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);break;
    case FLOAT:
     if(frontLeft.getZeroPowerBehavior()==DcMotor.ZeroPowerBehavior.FLOAT)
      break;
     frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
     frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
     backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
     backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);break;
   }
  }

  //for use with driveEncoder methods
  public void setDriveMotorPower(double power){
   frontLeft.setPower(power);
   frontRight.setPower(power);
   backLeft.setPower(power);
   backRight.setPower(power);
  }

  //to use with servos or to wait
  public void waiter(int time) {
   timer.reset();
   while (timer.milliseconds() < time) {
   }
  }
/*=======================================
 *
 *============End do not edit============
 *
 *=======================================*/

  //custom code for specific challenge

    public void rotate(int speed, int deg){
        int curDeg = 0;
        do {
            telemetry.addData("Deg set: ", deg);
            telemetry.addData("At deg: ", curDeg);
            telemetry.addData("Amount Left: ", deg - curDeg);
            telemetry.update();
        } while (
            true
        );
    }



    double angle;

    public void Angle(){// Firing sequence for launching rings
        //use equation to find the angle that the firing mechanism must rotate to
        //lock("Red");// lock onto target using CV
        //spin up launch motors
        LauncherLeft.setPower(0.6);
        LauncherRight.setPower(0.6);
        //use mathe to move into correct angle
        if(LaunchAngle.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            LaunchAngle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        angle = 0; // Do the math here

        angle = angle * ticksPerDeg;

        LaunchAngle.setTargetPosition((int)angle-LaunchAngle.getCurrentPosition());

        do{
            if(LaunchAngle.getTargetPosition() < LaunchAngle.getCurrentPosition()){
                LaunchAngle.setPower(-0.6);
            }else if(LaunchAngle.getTargetPosition() > LaunchAngle.getCurrentPosition()){
                LaunchAngle.setPower(0.6);
            }
            telemetry.addData("Angle set: ", angle);//in deg
            telemetry.addData("At angle: ", LaunchAngle.getCurrentPosition());
            telemetry.update();
        }while(
            LaunchAngle.getCurrentPosition() < LaunchAngle.getTargetPosition() - 50 &&
            LaunchAngle.getCurrentPosition() > LaunchAngle.getTargetPosition() + 50
        );


    }

    public void fire(){
        //rotate servo to fire
        LaunchPist.setPosition(1);

        waiter(500);
        //rotate back
        LaunchPist.setPosition(0);
    }

    public double getDist(){// Get distance to the wall using distance sensor
        telemetry.addData("Dist = ", Dist.getDistance(DistanceUnit.MM));
        telemetry.update();
        return Dist.getDistance(DistanceUnit.MM);
    }

    public void lock(String RB){// RB = which side, Red or Blue
        // This method will lock onto the target on the wall
        // This will use the camera and the image to locate where the image is
        // Use a do while

        setDriveMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        do{
            if(RB == "Red"){
                //rotate +x
                setToClockwise(0.6);
            }else if(RB == "Blue"){
                //rotate -x
                setToCounterwise(0.6);
            }
        }while(
            // image ! within the range of a specified value
            true
        );

    }

 }

