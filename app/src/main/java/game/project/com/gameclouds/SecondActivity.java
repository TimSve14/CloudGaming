package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import com.github.nkzawa.emitter.Emitter;



/**
 * Created by Lolita & Tim on 2016-09-26.
 */

public class SecondActivity extends Activity{
    private Button StartBtn;
    private Button StartBtn2;
    private Button Left;
    private Button LeftMore;
    private Button Right;
    private Button RightMore;
    private Button Up;
    private Button UpMore;
    private Button Down;
    private Button DownMore;
    private Button UpLeft;
    private Button UpRight;
    private Button DownLeft;
    private Button DownRight;
    private Button UpLeftMore;
    private Button UpRightMore;
    private Button DownLeftMore;
    private Button DownRightMore;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        StartBtn = (Button)findViewById(R.id.start_button);
        StartBtn2 = (Button)findViewById(R.id.start_button2);



        Left = (Button)findViewById(R.id.btnleft);
        LeftMore = (Button)findViewById(R.id.btnleftmore);
        Right = (Button)findViewById(R.id.btnright);
        RightMore = (Button)findViewById(R.id.btnrightmore);

        Up = (Button)findViewById(R.id.btnup);
        UpMore = (Button)findViewById(R.id.btnupmore);
        Down = (Button)findViewById(R.id.btndown);
        DownMore = (Button)findViewById(R.id.btndownmore);


        UpLeft = (Button)findViewById(R.id.buttonupleft);
        UpRight = (Button)findViewById(R.id.buttonupright);
        DownLeft = (Button)findViewById(R.id.buttondownleft);
        DownRight = (Button)findViewById(R.id.buttondownright);

        UpLeftMore = (Button)findViewById(R.id.btnupleftmore);
        UpRightMore = (Button)findViewById(R.id.btnuprightmore);
        DownLeftMore = (Button)findViewById(R.id.btndownleftmore);
        DownRightMore = (Button)findViewById(R.id.btndownrightmore);

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

    public  void update (final String move){

        if(move != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (move.equals("R0")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.VISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("R1")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);
                        //Right.startAnimation(AnimationUtils.loadAnimation(SecondActivity.this,android.R.anim.slide_in_left));
                        Right.setVisibility(View.VISIBLE);

                        RightMore.setVisibility(View.VISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("L0")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);


                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.VISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("L1")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.VISIBLE);
                        LeftMore.setVisibility(View.VISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("U0")) {
                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.VISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("U1")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.VISIBLE);
                        UpMore.setVisibility(View.VISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("D0")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.VISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("D1")) {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.VISIBLE);
                        DownMore.setVisibility(View.VISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("LU0")) {

                        UpLeft.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    }else if (move.equals("RU0")) {

                        UpRight.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);
                        UpLeft.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("LD0")) {

                        DownLeft.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        DownRight.setVisibility(View.INVISIBLE);
                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    }else if (move.equals("RD0")) {

                        DownRight.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("RD1")) {

                        DownRightMore.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.VISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    } else if (move.equals("LD1")) {

                        DownLeftMore.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        DownLeft.setVisibility(View.VISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);
                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    }else if (move.equals("RU1")) {

                        UpRightMore.setVisibility(View.VISIBLE);

                        UpLeftMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);
                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.VISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    }else if (move.equals("LU1")) {

                        UpLeftMore.setVisibility(View.VISIBLE);

                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpRight.setVisibility(View.INVISIBLE);
                        UpLeft.setVisibility(View.VISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);

                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                    }
                    else {
                        UpLeftMore.setVisibility(View.INVISIBLE);
                        UpRightMore.setVisibility(View.INVISIBLE);
                        DownLeftMore.setVisibility(View.INVISIBLE);
                        DownRightMore.setVisibility(View.INVISIBLE);

                        UpLeft.setVisibility(View.INVISIBLE);
                        UpRight.setVisibility(View.INVISIBLE);
                        DownLeft.setVisibility(View.INVISIBLE);
                        DownRight.setVisibility(View.INVISIBLE);


                        Right.setVisibility(View.INVISIBLE);
                        RightMore.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                        LeftMore.setVisibility(View.INVISIBLE);

                        Up.setVisibility(View.INVISIBLE);
                        UpMore.setVisibility(View.INVISIBLE);
                        Down.setVisibility(View.INVISIBLE);
                        DownMore.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }else {
            Log.i("LALALLA", "YOU");
        }

    }



    private void startGame() {

        StartBtn.setVisibility(View.INVISIBLE);
        StartBtn2.setVisibility(View.VISIBLE);

        Connect = new SocketConnect(room_id,"input",nickname);
        Connect.startSocketConnection();

        Connect.getSocket().on("move received",onNewVibrate);
        Connect.getSocket().on("lobby is full", lobbyFull);
        Connect.getSocket().on("game is running", gameRunning);
        Connect.getSocket().on("quit",quit);

        Controller = new SimpleController();


        Sensor1 = new InitSensor(this,Controller,Connect,SecondActivity.this);
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
