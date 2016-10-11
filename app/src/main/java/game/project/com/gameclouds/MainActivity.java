package game.project.com.gameclouds;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    private ImageView ConnectBtn;
    private EditText RoomID;
    private EditText Nickname;
    private ImageView sHelpBtn;
    private ImageView sHelpBtn2;
    private ImageView SettingsBtn;
    private ImageView HelpBtn;
    private ImageButton SC;
    private ProgressDialog mProgress;
    final Context context = this;

    



    /**
     * OnCreate method,
     * Initialise widgets from layout
     * Include onClick event Handlers
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String data= getIntent().getStringExtra("key");

        mProgress = new ProgressDialog(this);
        RoomID = (EditText)findViewById(R.id.roomID_edittext);
        RoomID.setText(data);
        Nickname = (EditText)findViewById(R.id.nickname_edittext);

        sHelpBtn = (ImageView)findViewById(R.id.help1_sign);
        sHelpBtn2 = (ImageView)findViewById(R.id.help2_sign);

        SettingsBtn = (ImageView)findViewById(R.id.settings_imageview);
        HelpBtn = (ImageView)findViewById(R.id.help_imageview);

        ConnectBtn = (ImageView)findViewById(R.id.connect_button);

        SC = (ImageButton)findViewById(R.id.qr_button);

        SC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          //Put code here to check if the camera has permission

                nextActivity();

            }
        });

        ConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

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





        sHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Okay, okay I will help you");

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(R.string.help1);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        sHelpBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Okay, okay I will help you");

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(R.string.help2);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

    }

    private void nextActivity() {

        Intent nextA = new Intent(MainActivity.this, ScannerActivity.class);
        nextA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(nextA);
        finish();

    }

    /*
     * helpInfo() and settingInfo() are two methods which redirect to a new Activity
     */
    private void helpInfo() {

        Intent HelpIntent = new Intent(MainActivity.this,HelpActivity.class);
        HelpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(HelpIntent);
    }

    private void settingInfo() {

        Intent SettingIntent = new Intent(MainActivity.this,SettingActivity.class);
        SettingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(SettingIntent);

    }

    /**
     * CheckLogin() checks if roomID and Nickname aren't null
     */
    private void checkLogin() {

        String player_name = Nickname.getText().toString().trim();
        String room_id = RoomID.getText().toString().trim();
        if(!TextUtils.isEmpty(player_name) && !TextUtils.isEmpty(room_id)){

            mProgress.setMessage("Checking player_name...");
            mProgress.show();


            connectToGame(room_id,player_name);
       } else {

            mProgress.setMessage("Please fill the fields!");
            mProgress.dismiss();
       }

       try
       {
           JSONObject player=new JSONObject();

           player.put("player_name", player_name);
           player.put("roomID", room_id);
       }
       catch (Exception je)
       {
           //DO something
       }

        mProgress.dismiss();
    }

    public String getRoomId(){
        return RoomID.getText().toString().trim();
    }

    public String getNickname(){
        return Nickname.getText().toString().trim();
    }

    private void connectToGame(String room_id,String nickname) {

        Intent GameIntent = new Intent(MainActivity.this, SecondActivity.class);
        GameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        GameIntent.putExtra("room",room_id);
        GameIntent.putExtra("nick",nickname);

        startActivity(GameIntent);
        finish();
    }


}
