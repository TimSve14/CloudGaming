package game.project.com.gameclouds;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by TimSvensson on 2016-10-04.
 */

public class SimpleController implements Controller{


    public SimpleController(){

    }

    @Override
    public String getMove(float[] coordinates) {

        float xAccel = coordinates[0];
        float yAccel = coordinates[1];
        float zAccel = coordinates[2];



        if ((xAccel > 3 && xAccel < 7) && (yAccel < 2 && yAccel > -2) && zAccel > 7) {
            return "C";
        }
        //Right move
        else if ((xAccel < 6 && xAccel > 2) && (yAccel > 2 && yAccel < 4) && zAccel > 2) {
            return "R0";
        }
        else if ((xAccel < 6 && xAccel > 2) && (yAccel > 4 && yAccel < 9) && zAccel > 2) {
            return "R1";
        }
        // Down right
        else if (((xAccel > 6 && xAccel < 9.5) && (yAccel > 2 && yAccel < 5) && (zAccel < 6 && zAccel > 3))) {
            return "RD0";
        }
        else if (((xAccel > 6 && xAccel < 9.5) && (yAccel > 2 && yAccel < 5) && (zAccel < 6 && zAccel < 3))) {
            return "RD1";
        }
        else if (((xAccel > 6 && xAccel < 9.5) && (yAccel > -5 && yAccel < -2) && (zAccel < 6 && zAccel > 3))) {
            return "LD0";
        }
        else if (((xAccel > 6 && xAccel < 9.5) && (yAccel > -9.5 && yAccel < -2) && (zAccel < 6 && zAccel < 3))) {
            return "LD1";
        }
        // Down move
        else if (((xAccel > 6 && xAccel < 8) && (yAccel > -2 && yAccel < 2) && zAccel > 2)) {
            return "D0";
        }
        else if ((xAccel > 8 && (yAccel > -2 && yAccel < 2) && zAccel > -2)) {
            return "D1";
        }
        // left move
        else if ((xAccel < 6 && xAccel > 2) && (yAccel < -2 && yAccel > -4) && zAccel > 2) {
            return "L0";
        }
        else if ((xAccel < 6 && xAccel > 2) && (yAccel < -4 && yAccel > -9) && zAccel > 2) {
            return "L1";
        }
        else if (((xAccel < 2 && xAccel > -7) && (yAccel < -4 && yAccel > -9) && zAccel > 7)) {
            return "LU0";
        }
        else if (((xAccel < 2 && xAccel > -7) && (yAccel < -4 && yAccel > -9) && zAccel < 7)) {
            return "LU1";
        }
        else if (((xAccel < 2 && xAccel > -7) && (yAccel > 4 && yAccel < 9) && zAccel > 7)) {
            return "RU0";
        }
        else if (((xAccel < 2 && xAccel > -7) && (yAccel > 4 && yAccel < 9) && zAccel < 7)) {
            return "RU1";
        }
        // Up
        else if (((xAccel < 1 && xAccel > -2) && (yAccel > -2 && yAccel < 2) && zAccel > 2)) {
            return "U0";
        }
        else if (((xAccel < -2 && xAccel > -7) && (yAccel > -3 && yAccel < 3) && zAccel > 2)) {
            return "U1";
        }

        return null;
    }

}
