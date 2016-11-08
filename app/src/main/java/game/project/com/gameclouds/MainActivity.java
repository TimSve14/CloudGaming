package game.project.com.gameclouds;


import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Lolita & Tim on 2016-09-26.
 */

public class MainActivity extends FragmentActivity {

    private ImageView ConnectBtn;
    private EditText RoomID;
    private EditText Nickname;
    private ImageView sHelpBtn;
    private ImageView sHelpBtn2;
    private ImageView SettingsBtn;
    private ImageView HelpBtn;
    private ImageView SC1;
    private ProgressDialog mProgress;
    final Context context = this;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 42;
    protected static String room_id;
    protected static String nickname;
    private Boolean exit = false;

    /**
     * OnCreate method,
     * Initialise widgets from layout
     * Include onClick event Handlers
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        final String data = getIntent().getStringExtra("key");


        mProgress = new ProgressDialog(this);
        RoomID = (EditText)findViewById(R.id.roomID_edittext);
        RoomID.setText(data);
        room_id = RoomID.getText().toString().trim();


        RoomID.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override

                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        room_id = RoomID.getText().toString().trim();
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            System.out.println(room_id.length());
                            if (room_id.length() == 6) {
                                SC1.setVisibility(View.INVISIBLE);
                                ConnectBtn.setVisibility(View.VISIBLE);

                            } else if (!(room_id.length() == 6)) {
                                SC1.setVisibility(View.VISIBLE);
                                ConnectBtn.setVisibility(View.INVISIBLE);
                            }

                            return false;
                        }
                        return true;
                    }
                });


        Nickname = (EditText) findViewById(R.id.nickname_edittext);

        sHelpBtn = (ImageView) findViewById(R.id.help1_sign);
        sHelpBtn2 = (ImageView) findViewById(R.id.help2_sign);

        SettingsBtn = (ImageView) findViewById(R.id.settings_imageview);
        HelpBtn = (ImageView) findViewById(R.id.help_imageview);

        ConnectBtn = (ImageView) findViewById(R.id.connect_button);
        SC1 = (ImageView) findViewById(R.id.qr1_button);


        SC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if the camera has permission from the player, if it dont
                // then we ask for it

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    nextActivity();

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }

            }
        });

        if (room_id.length() == 6) {
            SC1.setVisibility(View.INVISIBLE);
            ConnectBtn.setVisibility(View.VISIBLE);
        }

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
                dialog.setTitle("RoomId");

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
                dialog.setTitle("Nickname");

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

        Intent HelpIntent = new Intent(MainActivity.this, HelpActivity.class);
        HelpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(HelpIntent);
    }

    private void settingInfo() {

        Intent SettingIntent = new Intent(MainActivity.this, SettingActivity.class);
        SettingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(SettingIntent);

    }

    /**
     * CheckLogin() checks if roomID and Nickname aren't null
     */
    private void checkLogin() {

        nickname = Nickname.getText().toString().trim();
        room_id = RoomID.getText().toString().trim();

        if (!TextUtils.isEmpty(nickname) && !TextUtils.isEmpty(room_id)) {

            connectToGame(room_id, nickname);
        } else {
            if (TextUtils.isEmpty(nickname) && TextUtils.isEmpty(room_id)) {
                new AlertDialog.Builder(context)

                        .setTitle("Please fill in:")
                        .setMessage("RoomID and Nickname")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else if (TextUtils.isEmpty(room_id)) {
                new AlertDialog.Builder(context)

                        .setTitle("Please fill in:")
                        .setMessage("RoomID")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                new AlertDialog.Builder(context)

                        .setTitle("Please fill in:")
                        .setMessage("Nickname")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        }

        try {
            JSONObject player = new JSONObject();

            player.put("player_name", nickname);
            player.put("roomID", room_id);
        } catch (Exception je) {
            //DO something
        }

        mProgress.dismiss();
    }


    private void connectToGame(String _room_id, String _nickname) {

        Intent GameIntent = new Intent(MainActivity.this, SecondActivity.class);
        GameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        GameIntent.putExtra("room", _room_id);
        GameIntent.putExtra("nick", _nickname);

        startActivity(GameIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
