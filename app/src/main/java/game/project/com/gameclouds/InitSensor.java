package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by TimSvensson on 2016-09-28.
 */

public class InitSensor extends Activity implements SensorEventListener{

    private SensorManager sensorManager;
    Context mContext;
    float[] values = new float[3];
    private Controller Controller;
    private SocketConnect Connect;
    private String currentState = "";
    private ImageView pattern;
    static String move = "";
    private SecondActivity sa;
    //privtate List<SecondActivity> ls = new list......



    public InitSensor(){
    }
    //public InitSensor(Context mContext,Controller _controller, SocketConnect _connect, Actitivity a){
    public InitSensor(Context mContext,Controller _controller, SocketConnect _connect,SecondActivity _sa){
        //ls.add(a)
        this.sa = _sa;
        this.mContext = mContext;
        this.Controller = _controller;
        this.Connect = _connect;

        sensorManager = (SensorManager)mContext.getSystemService(mContext.SENSOR_SERVICE);

    }

    public void start(){

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);

    }

    public void stop(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        //move = "";
        String previousMove ="";

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            values[0] = event.values[0];
            values[1] = event.values[1];
            values[2] = event.values[2];

            move = Controller.getMove(values);




            if(move != currentState && move != null) {

                Connect.sendMove(move);
                currentState = move;
                sa.update(move);
                //viewController.update(move);
            }
        }
        /*for(Activity a : ls)
        * a.update(move)
        *
        * */

    }

    /*This method needs to be implemented when using SensorEventListener but it is
     * not needed in our code */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
