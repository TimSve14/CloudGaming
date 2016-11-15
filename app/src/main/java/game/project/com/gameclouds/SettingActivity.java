package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;

/**
 * Created by Tim & Lolita on 2016-09-27.
 */
public class SettingActivity extends Activity{

    private EditText IpAdress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        IpAdress = (EditText)findViewById(R.id.ip_edittext);

        IpAdress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    MyPreferences.saveIp(SettingActivity.this,IpAdress.getText().toString());

                    Toast.makeText(SettingActivity.this, "The ip-adress has been updated",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        });
        Button button= (Button) findViewById(R.id.test_controll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GameIntent = new Intent(SettingActivity.this, SecondActivity.class);
                GameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                GameIntent.putExtra("room", "1");
                GameIntent.putExtra("nick", "test");
                startActivity(GameIntent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
