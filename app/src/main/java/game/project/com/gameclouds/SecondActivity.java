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
    String room_id;
    String nickname;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
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

        nickname = extras.getString("nick");
        room_id = extras.getString("room");

        GameIntent.putExtra("room",room_id);
        GameIntent.putExtra("nick",nickname);

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
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
