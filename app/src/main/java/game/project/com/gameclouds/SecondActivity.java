package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

/**
 * Created by Lolita on 2016-09-26.
 */
public class SecondActivity extends Activity{
    private Button StartBtn;
    private Button ResumeBtn;
    private Button QuitBtn;
    private ImageView SettingsBtn;
    private ImageView HelpBtn;
    private InitSensor Sensor1;
    private SimpleController Controller;
    private SocketConnect Connect;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        StartBtn = (Button)findViewById(R.id.start_button);
        ResumeBtn = (Button)findViewById(R.id.resume_button);
        QuitBtn = (Button)findViewById(R.id.quit_button);

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
        
        ResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeGame();
            }
        });
        
        QuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitGame();
            }
        });
        





    }

    private void quitGame() {
        //TODO
    }

    private void resumeGame() {
        //TODO
    }

    private void startGame() {

        Intent GameIntent = new Intent(SecondActivity.this,ThirdActivity.class);
        GameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Bundle extras = getIntent().getExtras();

        String nick = extras.getString("nick");
        String room = extras.getString("room");

        Connect= new SocketConnect(room,"input",nick);

        Controller = new SimpleController();

        Connect.startSocketConnection();

        Sensor1 = new InitSensor(this,Controller,Connect);

        Sensor1.start();


        startActivity(GameIntent);




    }


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
        finish();
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
