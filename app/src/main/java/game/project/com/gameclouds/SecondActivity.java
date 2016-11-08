package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.nkzawa.emitter.Emitter;



/**
 * Created by Lolita & Tim on 2016-09-26.
 */

public class SecondActivity extends Activity{
    private Button StartBtn;
    private Button StartBtn2;
    private ImageView imageview;
    private InitSensor Sensor1;
    private SimpleController Controller;
    private RawController rawController;
    private SocketConnect Connect;
    private boolean activeSensor;
    String room_id;
    String nickname;
    Vibrator v;
    private RelativeLayout relativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);
        setContentView(R.layout.activity_t);

        relativeLayout = (RelativeLayout)findViewById(R.id.activity_t);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activeSensor = false;

        StartBtn = (Button)findViewById(R.id.start_button);
        StartBtn2 = (Button)findViewById(R.id.start_button2);

        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.gamestart);
       ((TransitionDrawable) imageview.getDrawable()).startTransition(4000);





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

    public void update(final String move) {
        if (move != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(move.equals("L0")){
                        relativeLayout.setBackgroundResource(R.drawable.left1);
                        //imageview.setImageResource(R.drawable.transleft1);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);

                    }else if(move.equals("L1")){
                        relativeLayout.setBackgroundResource(R.drawable.left2);
                        //imageview.setImageResource(R.drawable.transleft2);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    } else if(move.equals("R0")){
                        relativeLayout.setBackgroundResource(R.drawable.right1);
                        //imageview.setImageResource(R.drawable.transright1);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("R1")){
                        relativeLayout.setBackgroundResource(R.drawable.right2);
                        //imageview.setImageResource(R.drawable.transright2);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("U0")){
                        relativeLayout.setBackgroundResource(R.drawable.up1);
                        //imageview.setImageResource(R.drawable.transup1);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("U1")){
                        relativeLayout.setBackgroundResource(R.drawable.up2);
                        //imageview.setImageResource(R.drawable.transup2);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("D0")){
                        relativeLayout.setBackgroundResource(R.drawable.down1);
                       // imageview.setImageResource(R.drawable.transdown1);
                       // ((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("D1")){
                        relativeLayout.setBackgroundResource(R.drawable.down2);
                        //imageview.setImageResource(R.drawable.transdown2);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("LU0")){
                        relativeLayout.setBackgroundResource(R.drawable.upleft1);
                        //imageview.setImageResource(R.drawable.transuplefthalf);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("LU1")){
                        relativeLayout.setBackgroundResource(R.drawable.upleft2);
                       // imageview.setImageResource(R.drawable.transupleft);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("RU0")){
                        relativeLayout.setBackgroundResource(R.drawable.upright1);
                        //imageview.setImageResource(R.drawable.transuprighthalf);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("RU1")){
                        relativeLayout.setBackgroundResource(R.drawable.upright2);
                        //imageview.setImageResource(R.drawable.transupright);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("LD0")){
                        relativeLayout.setBackgroundResource(R.drawable.downleft1);
                       // imageview.setImageResource(R.drawable.transdownlefthalf);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("LD1")){
                        relativeLayout.setBackgroundResource(R.drawable.downleft2);
                        //imageview.setImageResource(R.drawable.transdownleft);
                       // ((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("RD0")){
                        relativeLayout.setBackgroundResource(R.drawable.downright1);
                        //imageview.setImageResource(R.drawable.transdownrighthalf);
                       // ((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }else if(move.equals("RD1")){
                        relativeLayout.setBackgroundResource(R.drawable.downright2);
                       // imageview.setImageResource(R.drawable.transdownright);
                        //((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }
                    else {
                        relativeLayout.setBackgroundResource(R.drawable.start);
                       // imageview.setImageResource(R.drawable.transtart);
                       // ((TransitionDrawable) imageview.getDrawable()).startTransition(0);
                    }

                }
            });
        }else{
            //Do what ?
        }
    }



    private void startGame() {

        StartBtn.setVisibility(View.INVISIBLE);
        imageview.setVisibility(View.GONE);
        StartBtn2.setVisibility(View.VISIBLE);



        String ipadress = MyPreferences.getIp(this);
        //System.out.println("Working: " + ipadress);

        Connect = new SocketConnect(room_id,"input",nickname,ipadress);
        Connect.startSocketConnection();

        Connect.getSocket().on("move received",onNewVibrate);
        Connect.getSocket().on("lobby is full", lobbyFull);
        Connect.getSocket().on("game is running", gameRunning);
        Connect.getSocket().on("pingding",pingding);
        Connect.getSocket().on("quit",quit);

        Controller = new SimpleController();

        //rawController = new RawController();


        Sensor1 = new InitSensor(this,Controller,Connect,SecondActivity.this);
        //Sensor1 = new InitSensor(this,rawController,Connect,SecondActivity.this);
        Sensor1.start();
        activeSensor = true;

    }

    private Emitter.Listener onNewVibrate = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
          //  v = (Vibrator) SecondActivity.this.getSystemService(SecondActivity.this.VIBRATOR_SERVICE);
          //  v.vibrate(100);

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

            if(activeSensor) {
                Sensor1.stop();
                Connect.getSocket().disconnect();
                Connect.getSocket().off("new message", onNewVibrate);
                Connect.getSocket().off("lobby is full", lobbyFull);
                Connect.getSocket().off("game is running", gameRunning);
                Connect.getSocket().off("pingding", pingding);
            }
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

    };

    private Emitter.Listener pingding = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            Connect.getSocket().emit("ping response");

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
        if(activeSensor) {
            Sensor1.stop();
            Connect.getSocket().disconnect();
            Connect.getSocket().off("new message", onNewVibrate);
            Connect.getSocket().off("lobby is full", lobbyFull);
            Connect.getSocket().off("game is running", gameRunning);
            Connect.getSocket().off("pingding", pingding);
        }

        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
