package com.janaka.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String BROADCAST_ACTION;
    private TextView txtViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


    }

    @Override
    protected void onStart() {
        super.onStart();

        Receiver localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Receiver localListener = new Receiver();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){

        if (view.getId() == R.id.btnStartBroadcasst){
            BackgroundService.startAction(getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = txtViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            txtViewMsg.setText(currentText);
        }
    }
}
