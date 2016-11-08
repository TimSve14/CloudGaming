package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
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

    public SocketConnect(String roomId, String input, String userName,String ipadress){

        // Check if there are three dots in the ip-adress, if not connect
        // to the usual ip adress

        if(ipadress.indexOf('.', ipadress.indexOf('.') + 2) != -1){
           try {
               ipadress = "104.155.85.38";
               mSocket = IO.socket("http://"+ ipadress + "/");

           } catch (URISyntaxException e) {
           }
       }
        else{
           try {
               mSocket = IO.socket("http://104.155.85.38/");

           } catch (URISyntaxException e) {
           }
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