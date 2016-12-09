package game.project.com.gameclouds;

/**
 * Created by TimSvensson on 2016-12-08.
 */
    import android.app.Activity;
    import android.content.Intent;
    import android.net.TrafficStats;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
public class AndroidTrafficStats extends Activity{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);

        final TextView textMobileRxBytes = (TextView)findViewById(R.id.MobileRxBytes);
        final TextView textMobileRxPackets = (TextView)findViewById(R.id.MobileRxPackets);
        final TextView textMobileTxBytes = (TextView)findViewById(R.id.MobileTxBytes);
        final TextView textMobileTxPackets = (TextView)findViewById(R.id.MobileTxPackets);

        final TextView textTotalRxBytes = (TextView)findViewById(R.id.TotalRxBytes);
        final TextView textTotalRxPackets = (TextView)findViewById(R.id.TotalRxPackets);
        final TextView textTotalTxBytes = (TextView)findViewById(R.id.TotalTxBytes);
        final TextView textTotalTxPackets = (TextView)findViewById(R.id.TotalTxPackets);

        Button buttonLoadTrafficStats = (Button)findViewById(R.id.loadtrafficstats);
        buttonLoadTrafficStats.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(TrafficStats.getMobileRxBytes() == TrafficStats.UNSUPPORTED)
                {
                    textMobileRxBytes.setText("MobileRxBytes: " + "UNSUPPORTED!");
                }
                else{
                    textMobileRxBytes.setText("MobileRxBytes: "
                            + String.valueOf(TrafficStats.getMobileRxBytes()));
                }

                if(TrafficStats.getMobileRxPackets() == TrafficStats.UNSUPPORTED)
                {
                    textMobileRxPackets.setText("MobileRxPackets: " + "UNSUPPORTED!");
                }
                else{
                    textMobileRxPackets.setText("MobileRxPackets: "
                            + String.valueOf(TrafficStats.getMobileRxPackets()));
                }

                if(TrafficStats.getMobileTxBytes() == TrafficStats.UNSUPPORTED)
                {
                    textMobileTxBytes.setText("MobileTxBytes: " + "UNSUPPORTED!");
                }
                else{
                    textMobileTxBytes.setText("MobileTxBytes: "
                            + String.valueOf(TrafficStats.getMobileTxBytes()));
                }

                if(TrafficStats.getMobileTxPackets() == TrafficStats.UNSUPPORTED)
                {
                    textMobileTxPackets.setText("MobileTxPackets: " + "UNSUPPORTED!");
                }
                else{
                    textMobileTxPackets.setText("MobileTxPackets: "
                            + String.valueOf(TrafficStats.getMobileTxPackets()));
                }

                if(TrafficStats.getTotalRxBytes() == TrafficStats.UNSUPPORTED)
                {
                    textTotalRxBytes.setText("TotalRxBytes: " + "UNSUPPORTED!");
                }
                else{
                    textTotalRxBytes.setText("TotalRxBytes: "
                            + String.valueOf(TrafficStats.getTotalRxBytes()));
                }

                if(TrafficStats.getTotalRxPackets() == TrafficStats.UNSUPPORTED)
                {
                    textTotalRxPackets.setText("TotalRxPackets: " + "UNSUPPORTED!");
                }
                else{
                    textTotalRxPackets.setText("TotalRxPackets: "
                            + String.valueOf(TrafficStats.getTotalRxPackets()));
                }

                if(TrafficStats.getTotalTxBytes() == TrafficStats.UNSUPPORTED)
                {
                    textTotalTxBytes.setText("TotalTxBytes: " + "UNSUPPORTED!");
                }
                else{
                    textTotalTxBytes.setText("Total data uploaded over wifi: "
                            + String.valueOf(TrafficStats.getTotalTxBytes() - TrafficStats.getMobileTxBytes()));
                }

                if(TrafficStats.getTotalTxPackets() == TrafficStats.UNSUPPORTED)
                {
                    textTotalTxPackets.setText("TotalTxPackets: " + "UNSUPPORTED!");
                }
                else{
                    textTotalTxPackets.setText("Total data received: "
                            + String.valueOf(TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes()));
                }

            }});

    }

    public String getTotalRecievedData(){
        return String.valueOf(TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes());
    }

    public String getTotalUploadedData(){
        return String.valueOf(TrafficStats.getTotalTxBytes() - TrafficStats.getMobileTxBytes());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HelpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
