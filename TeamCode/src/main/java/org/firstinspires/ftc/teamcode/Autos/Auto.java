package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware;

/*


This is made to hold all of our methods for the Autonomous period so that we can quickly and easily make Autonomous code to fit any situation



 */

public class Auto {

    int pos = 0; //The position of the robot on the board noted by a 0 to infinity, allows the programs to be smart and self locating

    Hardware r = new Hardware();

    public void wobbleGoal(){
        //this will bring the wobble goal to the correct place
    }

    public void line(){
        //This will move to the line
        //We could just use a color sensor to detect the line and make sure we are behind it
        if(pos == 0){ //hasn't moved

        }else if(pos ==1){ //at block one

        }else if(pos == 2){ //at block 2

        }else if(pos == 3){ //at block 3

        }
    }

    public void launch(){
        //launches the rings into the goal
        r.Angle();
        r.fire(3);
    }

    public void launch(int first, int second, int third){//the number of power shots
        //launches the ring to a specified power shot
        if(first == 1){
            r.Angle();
            r.fire();
        }
        r.driveRightEncoder(0.6, 10);
        if(second == 1){
            r.Angle();
            r.fire();
        }
        r.driveRightEncoder(0.6,10);
        if(third ==1){
            r.Angle();
            r.fire();
        }
    }

    //only used if we have an arm to place rings in
    public void launch(boolean TF){
        //just place the rings in te bottom placement
        if(pos == 0){ //hasn't moved

        }else if(pos ==1){ //at block one

        }else if(pos == 2){ //at block 2

        }else if(pos == 3){ //at block 3

        }

        //rotate any arm that will put the ring in
    }

}