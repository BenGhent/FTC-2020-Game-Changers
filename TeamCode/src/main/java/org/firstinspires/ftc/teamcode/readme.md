# 9161 Overload
### This is our TeamCode for the 2020-2021 FTC robotics competition Game Changers.

## Autonomous

We have many different names for our files, Red, Blue are for the different sides. Basic is a basic move to position, Inter is the intermediate step which only fires the canon, Advanced which uses CV, via TensorFlow, to look and know how many rings are anf moves there, and Supreme does all.
  Side | Type | Location | Auto | Example |
  -----|------|----------|----- |---------|
  Red | Basic | Left | Auto  |   Ex: Red_Advanced_Left_Auto.java |
  Blue | Inter |  Right | |
  | | Advanced | |
  | | Supreme | |
       
  * Red - For the red side
  * Blue - For the blue side
  * Adv - The code decides which side it is on
  * Left - For the left square when looking at goals
  * Right - For the right square when looking at goals
  
  * Basic - This is a basic program, this program just moves the robot in a direction for a specified amount distance in one or two directions
  * Inter - This is the intermediate stage, this program is an upgrade from Basic program. This program will do the basic tasks that Basic uses but will also add an atempt to launch three rings into the top goal
  * Advanced - This is the advanced version of the basic program. This program will detect how many rings are stacked and move to the correct position then move back to the white line
  * Supreme - This program is the most advanced version of the Basic program. This program does everything, launches the rings into the top goal, detects rings and moves wobble goal into position, and them moves back onto the white line.

## TeleOp

There are three different types of our TeleOp
  1. Basic - We have basic tools to move around, no firing or loading, just movement
  2. Dev - Testing TeleOp which is our testing code, where we test out code before comiting it to Comp
  3. Pub - Competition code, tested and proven code meant for competition and is also our practicing file, where we practice for comp
  
TeleOp is what we call the driver control programe. Here we have all of our if statements and movement math. We are using mechanum wheels so that we can move in each direction just by varying our speeds of the motors.

```java
double velocity=Math.sqrt(Math.pow(gamepad1.left_stick_x, 2)+Math.pow(gamepad1.left_stick_y, 2));
double g2vel=Math.sqrt(Math.pow(gamepad2.right_stick_y,2))+Math.pow(gamepad2.right_stick_x,2);
double rotation=gamepad1.right_stick_x*-1;
double g2rot=gamepad1.right_stick_x*-1;

double power1=velocity*Math.cos(angle+(Math.PI/4))-rotation;
double power2=velocity*Math.sin(angle+(Math.PI/4))+rotation;
double power3=velocity*Math.sin(angle+(Math.PI/4))-rotation;
double power4=velocity*Math.cos(angle+(Math.PI/4))+rotation;
r.frontLeft.setPower(power1 * deflator);
r.frontRight.setPower(power2 * deflator);
r.backLeft.setPower(power3 * deflator);
r.backRight.setPower(power4 * deflator);
```

This uses Trig to calculate the angle of the gamepad sticks to get the direction of movement, then how far the stick has moved to calculate speed. This allows us to accuratly control the robot in all directions.
  
## Files
  ### Hardware
  
  Our Hardware file is our backbone of all of our programs, we init Hardware as "r". 
  ```java
  Hardware r = new Hardware();
  ```
  The Hardware file inits all of our motors, servos, and sensors. 
  
  ```java
  public DcMotor frontLeft;    // Front Left drive motor
  
  public Servo LanchPist;      // launching piston for firing mechanism
  
  public DistanceSensor Dist;  // Distance sensor to detect distance to the goal
  ```
  
  This is a good representation of how we decided to name our public variables. We named them by the position they are or by what they are.
  
   * L = Left
   * R = Right
   * F = Front
   * B = Back
   * M = Motor
  
   * I = intake
   * Lau = Launcher
  ```
   _____       _____
   |   |       |   |
   |FLM|       |FRM|
   |   ‾‾‾‾‾‾‾‾    |
   |   ________    |
   |BLM|       |BRM|
   |   |       |   |
   ‾‾‾‾‾       ‾‾‾‾‾
   ```
   ```java
   frontLeft = hwMap.dcMotor.get("FLM");
   frontRight = hwMap.dcMotor.get("FRM");
   backLeft = hwMap.dcMotor.get("BLM");
   backRight = hwMap.dcMotor.get("BRM");
   ```
  
  ### AutoTransitioner
  
  This class was created to quickly and easly transfer from our autonomous to our TeleOp.
  
  This is not our software, all credit goes to KNO3 Robotics

  ###Auto

  This class was created to ease the creation of the autonomous programs.

  This class was created to allow us to make custom autonomous code on the fly to adapt to our teammates autonomous.

  referenced as:
  ```java
  Auto a = new Auto();
  ```

  We use redefinition of methods so that we can easily understand what they do, such as:

  ```java
  public void launch(){
    //meant to launch into the goal
}

public void launch(int a, int b, int c){
    //this method will shoot down the power shots depending on which we want
    if(a == 1){
        //angle cannon and fire
    }
    //move to the right
    if(b == 1){
        //angle cannon and fire
    }
    //move the robot
    if(c == 1){
        //angle cannon and fire
    }
}

  ```
  
  ###CV
  
  This program is the backbone of our computer vision and holds all of our methods to get information from the camera, x, and y locations and the probability of if what the bot is looking at is the intended target
