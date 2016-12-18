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

    /**
     * The constructor of InitSensor
     * @param mContext
     * @param _controller
     * @param _socket
     * @param _secondActivity
     */
    public InitSensor(Context mContext,Controller _controller, SocketConnect _socket,SecondActivity _secondActivity){
        this.sa = _secondActivity;
        this.mContext = mContext;
        this.Controller = _controller;
        this.Connect = _socket;

        sensorManager = (SensorManager)mContext.getSystemService(mContext.SENSOR_SERVICE);

    }

    /**
     * Starts the accelerometer sensor and needs
     * to be called before accessing data from accelerometer.
     */
    public void start(){

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);

    }

    /**
     * Stops the accelerometer sensor, needs to be called
     * when done
     */
    public void stop(){
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        //move = "";
        String previousMove ="";

        if(!(MyPreferences.isRawData(mContext))) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                values[0] = event.values[0];
                values[1] = event.values[1];
                values[2] = event.values[2];

                move = Controller.getMove(values);

                if (move != currentState && move != null) {

                    Connect.sendMove(move);
                    currentState = move;
                    sa.update(move);
                }
            }
        }

        else{
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                values[0] = event.values[0];
                values[1] = event.values[1];
                values[2] = event.values[2];
            }
            Connect.sendMoveRAW(values);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
        // Do nothing
}
