# 9161 Overload
### This is our TeamCode for the 2020-2021 FTC robotics competition Game Changers.

# Files ([Location](https://github.com/BenGhent/FTC-2020-Game-Changers/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode))
  ## Hardware.java

  Our [Hardware file](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Hardware.java) is our backbone of all of our programs, we init Hardware as "r" so that it is less clunky to write and easier to read.
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

  ## Autonomous ([Files](https://github.com/BenGhent/FTC-2020-Game-Changers/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Autos))

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

  ## TeleOp ([Files](https://github.com/BenGhent/FTC-2020-Game-Changers/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp))

  There are three different types of our TeleOp:
    1. [Basic](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/TeleOp_Basic.java) - We have basic tools to move around, no firing or loading, just movement
    2. [Dev](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/TeleOp_Dev.java) - Testing TeleOp which is our testing code, where we test out code before comiting it to Comp
    3. [Pub](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/TeleOp_Pub.java) - Competition code, tested and proven code meant for competition and is also our practicing file, where we practice for comp

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

  ## AutoTransitioner.java

  [This class](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/AutoTransitioner.java) was created to quickly and easly transfer from our autonomous to our TeleOp.

  This is not our software, all credit goes to [KNO3 Robotics](https://github.com/KNO3Robotics)

  ## Auto

  [This class](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Autos/Auto.java) was created to ease the creation of the autonomous programs.

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

  ## CV.java
  
  This is our [Vuforia](https://developer.vuforia.com/) and [Tensorflow lite](https://www.tensorflow.org/lite) Computer Vision class. [This class](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/CV.java) allows us to detect objects weather we are in Autonomus or Teleop so that we can accuratly detect and lign up with objects such as the ring stack or the goals. This was taken from [ConceptTensorFlowObjectDetectionWebcam](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples/ConceptTensorFlowObjectDetectionWebcam.java) in the examples located [here.](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)
  
  This uses the Tensorflow modles(.tflite) that were generously provided by the wonderful people at First Inspires. (See [the FTC wiki](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Java-Sample-TensorFlow-Object-Detection-Op-Mode) for more information.)
  
  Things to still do:
  - [ ] Test on robot
  - [ ] Convert to refrence class
  - [ ] Make compatable with Autonomus
  - [ ] Make compatable with Teleop
  - [ ] Add reference images (the ones under the goals and outer walls)
  
  # Overall Things
  
  ## Things to do
  
  ### Robot
  - [X] Complete the initial chassis designe
  - [ ] Build the chassis
  - [X] Designe the launching mechanism
  - [ ] Build the launching mechanism
  - [ ] Mount the launching mechanism
  
  ### Software
  - [ ] Get Basic software working ([File](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/TeleOp_Basic.java))
  - [ ] Get [CV.java](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/CV.java) working
  - [ ] Get launching code working ([File](https://github.com/BenGhent/FTC-2020-Game-Changers/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/TeleOp_Dev.java))
  - [ ] Get comp code working after dev is working
  
  ## This year
  
  Due to the global pandeic we will not be doing in-person competitions for the forseable future. This will allow our team to branch out and understand and develop new tools that will help us in the future competitions. We are learning new ways to make the robot modular and easily changeable, we are diping out toes in computer vision, we are also developing new ways to organize our teams to make the job of our journelists (notebook people) easier by documenting every step of the designe process.
