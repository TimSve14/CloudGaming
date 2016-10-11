package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by Lolita on 2016-10-07.
 */
public class ThirdActivity extends Activity{

    private InitSensor Sensor1;
    private SimpleController Controller;
    private SocketConnect Connect;
    String room_id;
    String nickname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle extras = getIntent().getExtras();
        nickname = extras.getString("nick");
        room_id = extras.getString("room");

        Connect= new SocketConnect(room_id,"input",nickname);

        Controller = new SimpleController();

        Connect.startSocketConnection();

        Sensor1 = new InitSensor(this,Controller,Connect);

        Sensor1.start();

    }

    public void onBackPressed() {
        Sensor1.stop();
        Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
        intent.putExtra("room",room_id);
        intent.putExtra("nick",nickname);
        startActivity(intent);
        finish();

    }

}
