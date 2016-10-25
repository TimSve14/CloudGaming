package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.github.nkzawa.emitter.Emitter;

/**
 * Created by Lolita & Tim on 2016-09-26.
 */

public class SecondActivity extends Activity{
    private Button StartBtn;
    private ImageView SettingsBtn;
    private ImageView HelpBtn;
    private InitSensor Sensor1;
    private SimpleController Controller;
    private SocketConnect Connect;
    String room_id;
    String nickname;
    Vibrator v;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        StartBtn = (Button)findViewById(R.id.start_button);

        SettingsBtn = (ImageView)findViewById(R.id.settings_imageview);
        HelpBtn = (ImageView)findViewById(R.id.help_imageview);

        SettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingInfo();
            }
        });
        HelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpInfo();
            }
        });


        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        Bundle extras = getIntent().getExtras();

        nickname = extras.getString("nick");
        room_id = extras.getString("room");

    }


    private void startGame() {

        Connect = new SocketConnect(room_id,"input",nickname);
        Connect.startSocketConnection();

        Connect.getSocket().on("move received",onNewVibrate);
        Connect.getSocket().on("lobby is full", lobbyFull);
        Connect.getSocket().on("game is running", gameRunning);
        Connect.getSocket().on("quit",quit);

        Controller = new SimpleController();

        Sensor1 = new InitSensor(this,Controller,Connect);
        Sensor1.start();

    }

    private Emitter.Listener onNewVibrate = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            v = (Vibrator) SecondActivity.this.getSystemService(SecondActivity.this.VIBRATOR_SERVICE);
            v.vibrate(100);
        }

    };

    private Emitter.Listener lobbyFull = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            // Start game YAY!
        }

    };

    private Emitter.Listener gameRunning = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            // Activate the controller
        }

    };

    private Emitter.Listener quit = new Emitter.Listener() {

        @Override
        public void call(Object... args) {

            Sensor1.stop();
            Connect.getSocket().disconnect();
            Connect.getSocket().off("new message", onNewVibrate);
            Connect.getSocket().off("lobby is full", lobbyFull);
            Connect.getSocket().off("game is running" , gameRunning);
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    };


    /*
    * helpInfo() and settingInfo() are two methods which redirect to a new Activity
    */
    private void helpInfo() {

        Intent HelpIntent = new Intent(SecondActivity.this,HelpActivity.class);
        HelpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(HelpIntent);
    }

    private void settingInfo() {

        Intent SettingIntent = new Intent(SecondActivity.this,SettingActivity.class);
        SettingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(SettingIntent);

    }



    @Override
    public void onBackPressed() {
        Sensor1.stop();
        Connect.getSocket().disconnect();
        Connect.getSocket().off("new message", onNewVibrate);
        Connect.getSocket().off("lobby is full", lobbyFull);
        Connect.getSocket().off("game is running" , gameRunning);
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
