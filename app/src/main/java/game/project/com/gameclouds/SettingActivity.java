package game.project.com.gameclouds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;

/**
 * Created by Tim & Lolita on 2016-09-27.
 */
public class SettingActivity extends Activity{

    private EditText IpAdress;
    private String ip_adress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        IpAdress = (EditText)findViewById(R.id.ip_edittext);


        IpAdress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    setIp(SettingActivity.this, IpAdress.getText().toString());
                    System.out.println(getIp(SettingActivity.this));
                    Toast.makeText(SettingActivity.this, "The ip-adress has been updated",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        });

    }

    public static void setIp(Context context, String _ip) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ipadress", _ip);
        editor.commit();
    }

    public static String getIp(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("ipadress", "");
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
