package akhfa.in.jerrytracker;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView isConnectedTextView;
    Button scannerButton, radarButton;
    ConnectivityReceiver connectionChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isConnectedTextView = (TextView) findViewById(R.id.isConnected);
        scannerButton = (Button) findViewById(R.id.scannerButton);
        radarButton = (Button) findViewById(R.id.radarButton);

        if(isConnected())
        {
            isConnectedTextView.setText("You are connected");
            radarButton.setEnabled(true);
            scannerButton.setEnabled(true);
        }
        else
        {
            isConnectedTextView.setText("You are disconnected \nPlease enable internet connection");
            radarButton.setEnabled(false);
            scannerButton.setEnabled(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMap(View v)
    {
        Intent secondIntent = new Intent(MainActivity.this, MapsActivity.class);
        MainActivity.this.startActivity(secondIntent);
    }

    public void scanQR(View v)
    {
        Intent secondIntent = new Intent(MainActivity.this, QRCodeScannerActivity.class);
        MainActivity.this.startActivity(secondIntent);
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

//    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
//            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
//            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);
//
//            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
//            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);
//
//            // do application-specific task(s) based on the current network state, such
//            // as enabling queuing of HTTP requests when currentNetworkInfo is connected etc.
//        }
//    };
//
//    private void registerReceivers() {
//        registerReceiver(mConnReceiver,
//                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//    }


}
