package game.project.com.gameclouds;

import java.util.zip.ZipEntry;

/**
 * Created by TimSvensson on 2016-11-08.
 */

public class RawController implements Controller{
    public RawController(){

    }


    @Override
    public String getMove(float[] coordinates) {


        float xAccel = coordinates[0];
        float yAccel = coordinates[1];
        float zAccel = coordinates[2];

        String value = null;

        if(xAccel != 10){
            value = String.valueOf(xAccel);
        }else if(yAccel != 10){
            value = String.valueOf(yAccel);
        }else if(zAccel != 10){
            value = String.valueOf(zAccel);
        }

        return value;

    }


}
