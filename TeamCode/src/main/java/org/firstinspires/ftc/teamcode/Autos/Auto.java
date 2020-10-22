package org.firstinspires.ftc.teamcode.Autos;

import org.firstinspires.ftc.teamcode.Hardware;

/*

This is made to hold all of our methods for the Autonomous period so that we can quickly and easily make Autonomous code to fit any situation

 */

public class Auto {

    Hardware r = new Hardware();

    int pos = 0; //The position of the robot on the board noted by a 0 to infinity, allows the programs to be smart and self locating
    int side = r.side;

    int mult = 1;

    private void init(){
        if(side == 0){
            mult = -1;
        }
    }


    public void wobbleGoal(){
        //this will bring the wobble goal to the correct place
    }

    public void line(){
        //This will move to the line
        //We could just use a color sensor to detect the line and make sure we are behind it
        if(pos == 0){ //hasn't moved

        }else if(pos == 1){ //at block one

        }else if(pos == 2){ //at block 2

        }else if(pos == 3){ //at block 3

        }else if(pos == 4){ //at line

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

        }else if(pos == 1){ //at block one

        }else if(pos == 2){ //at block 2

        }else if(pos == 3){ //at block 3

        }else if(pos == 4){

        }

        //rotate any arm that will put the ring in
    }

}
