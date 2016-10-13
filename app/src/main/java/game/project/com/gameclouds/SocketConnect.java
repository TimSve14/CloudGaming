package game.project.com.gameclouds;

import android.app.Activity;
import android.os.Vibrator;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by TimSvensson on 2016-10-04.
 */

public class SocketConnect extends Activity {

    private Socket mSocket;
    String roomId = "";
    String input = "";
    String userName = "";
    Vibrator v;

    public SocketConnect(String roomId, String input, String userName){

        try {
            mSocket = IO.socket("http://130.211.111.100");

        } catch (URISyntaxException e) {
        }

        this.roomId = roomId;
        this.input = input;
        this.userName = userName;
    }

    public void startSocketConnection(){

        mSocket.connect();

        JSONObject connect = new JSONObject();

        try {
            connect.put("id", roomId);
            connect.put("type", input);
            connect.put("nick", userName);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mSocket.emit("register", connect);

    }


    public void sendMove(String move){

        JSONObject JsonMove = new JSONObject();

        try {
            JsonMove.put("move", move);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mSocket.emit("new move",JsonMove);
    }

    public Socket getSocket(){
        return mSocket;
    }


}