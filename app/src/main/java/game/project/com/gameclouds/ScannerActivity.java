package game.project.com.gameclouds;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Lolita on 2016-10-04.
 */
public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler{
private String info = "";
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.w("HandleResult",result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());

        AlertDialog alertDialog = builder.create();
        //alertDialog.show();

        info = result.getText();

        if(!TextUtils.isEmpty(info)){

            mScannerView.stopCamera();


            //Intent SettingsIntent = new Intent(this, MainActivity.class);
            Intent SettingsIntent = new Intent(this, MainActivity.class); //might be here TODO
            SettingsIntent.putExtra("key",result.getText());
            Log.i("bla", result.getText());
            startActivity(SettingsIntent);
            finish();

            Log.w("Test: show QRcode: ", info);
        } else {

            Log.e("QRcode is missing ","ERROR");
        }



        // mScannerView.resumeCameraPreview(this);
    }
    @Override
    public void onBackPressed() {
        mScannerView.stopCamera();
        Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
