package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.github.nkzawa.emitter.Emitter;

/**
 * Created by Lolita & Tim on 2016-09-26.
 */

public class SecondActivity extends FragmentActivity {
    private Button StartBtn;
    private ImageView imageview;
    private InitSensor Sensor1;
    private SimpleController Controller;
    private SocketConnect Connect;
    private boolean activeSensor;
    String room_id;
    String nickname;
    Vibrator v;
    private RelativeLayout relativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        relativeLayout = (RelativeLayout)findViewById(R.id.activity_t);

        View decorView = getWindow().getDecorView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activeSensor = false;

        StartBtn = (Button)findViewById(R.id.start_button);
        StartBtn.setVisibility(View.INVISIBLE);

        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.gamestart);
        ((TransitionDrawable) imageview.getDrawable()).startTransition(4000);

        Bundle extras = getIntent().getExtras();

        nickname = extras.getString("nick");
        room_id = extras.getString("room");

        String ipadress = MyPreferences.getIp(this);

        System.out.println(ipadress);

        Connect = new SocketConnect(room_id,"input",nickname,ipadress);
        Connect.startSocketConnection();
        Connect.getSocket().on("game is ready", gameReady);

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startGame();
            }
        });

    }


    private void startGame() {

        Connect.getSocket().on("feedback",onNewFeedback);
        Connect.getSocket().on("pingding",pingding);
        Connect.getSocket().on("quit",quit);

        Controller = new SimpleController();

        Sensor1 = new InitSensor(this,Controller,Connect,SecondActivity.this);

        Sensor1.start();
        activeSensor = true;

    }

    private Emitter.Listener onNewFeedback = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
              v = (Vibrator) SecondActivity.this.getSystemService(SecondActivity.this.VIBRATOR_SERVICE);
              v.vibrate(50);

        }

    };

    private Emitter.Listener gameReady = new Emitter.Listener() {

        @Override

        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StartBtn.setVisibility(View.VISIBLE);
                    imageview.setVisibility(View.INVISIBLE);
                }
            });
        }
    };


    private Emitter.Listener quit = new Emitter.Listener() {

        @Override
        public void call(Object... args) {

            if(activeSensor) {
                Sensor1.stop();
                Connect.getSocket().disconnect();
                Connect.getSocket().off("feedback", onNewFeedback);
                Connect.getSocket().off("game is ready", gameReady);
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

    public void update(final String move) {
        if (move != null) {
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    if(move.equals("L0")){
                        relativeLayout.setBackgroundResource(R.drawable.left1);

                    }else if(move.equals("L1")){
                        relativeLayout.setBackgroundResource(R.drawable.left2);

                    } else if(move.equals("R0")){
                        relativeLayout.setBackgroundResource(R.drawable.right1);

                    }else if(move.equals("R1")){
                        relativeLayout.setBackgroundResource(R.drawable.right2);

                    }else if(move.equals("U0")){
                        relativeLayout.setBackgroundResource(R.drawable.up1);

                    }else if(move.equals("U1")){
                        relativeLayout.setBackgroundResource(R.drawable.up2);

                    }else if(move.equals("D0")){
                        relativeLayout.setBackgroundResource(R.drawable.down1);

                    }else if(move.equals("D1")){
                        relativeLayout.setBackgroundResource(R.drawable.down2);

                    }else if(move.equals("LU0")){
                        relativeLayout.setBackgroundResource(R.drawable.upleft1);

                    }else if(move.equals("LU1")){
                        relativeLayout.setBackgroundResource(R.drawable.upleft2);

                    }else if(move.equals("RU0")){
                        relativeLayout.setBackgroundResource(R.drawable.upright1);

                    }else if(move.equals("RU1")){
                        relativeLayout.setBackgroundResource(R.drawable.upright2);

                    }else if(move.equals("LD0")){
                        relativeLayout.setBackgroundResource(R.drawable.downleft1);

                    }else if(move.equals("LD1")){
                        relativeLayout.setBackgroundResource(R.drawable.downleft2);

                    }else if(move.equals("RD0")){
                        relativeLayout.setBackgroundResource(R.drawable.downright1);

                    }else if(move.equals("RD1")){
                        relativeLayout.setBackgroundResource(R.drawable.downright2);
                    }
                    else {
                        relativeLayout.setBackgroundResource(R.drawable.start);

                    }

                }
            });
        }else{
            //if non is working
        }
    }


    @Override
    public void onBackPressed() {
        if(activeSensor) {
            Sensor1.stop();
            Connect.getSocket().disconnect();
            Connect.getSocket().off("new message", onNewFeedback);
            Connect.getSocket().off("lobby is full", gameReady);
            Connect.getSocket().off("pingding", pingding);
        }

        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}