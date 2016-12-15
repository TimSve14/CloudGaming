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
    Integer outgoingData = 0;

    public SocketConnect(String roomId, String input, String userName,String ipadress){

        // Check if there are three dots in the ip-adress, if not connect
        // to the usual ip adress
    // ipadress.indexOf('.', ipadress.indexOf('.') + 2) != -1
        if(false){
           try {
               System.out.println("The right ip:" + ipadress);
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

    // Send mapped data to server
    public void sendMove(String move){

        JSONObject JsonMove = new JSONObject();

        try {
            JsonMove.put("move", move);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mSocket.emit("new move",JsonMove);

        //how many moves are sent to server
        outgoingData++;
        System.out.println(outgoingData);

    }

    // Send raw data to server
    public void sendMoveRAW(float [] moves){

        JSONObject JsonMove = new JSONObject();

        try {
            JsonMove.put("x", moves[0]);
            JsonMove.put("y", moves[1]);
            JsonMove.put("z", moves[2]);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mSocket.emit("new move",JsonMove);

        //how many packets are sent to server
        outgoingData++;
        System.out.println(outgoingData);

    }

    public Socket getSocket(){
        return mSocket;
    }
    
}