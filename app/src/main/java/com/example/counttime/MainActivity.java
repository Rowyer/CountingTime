package com.example.counttime;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Message;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
//import java.util.logging.Handler;
import java.util.Timer;
import java.util.TimerTask;

//import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements OnClickListener, View.OnClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private EditText inputEt;
    private Button getTimeB,startTimeB,stopTimeB;
    private TextView timeTx;
    private int i = 0;
    private Timer timer=null;
    private TimerTask task=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        InitView();
    }

    private void InitView(){
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        inputEt=(EditText)findViewById(R.id.inputtime);
        getTimeB=(Button)findViewById(R.id.gettime);
        startTimeB=(Button)findViewById(R.id.starttime);
        stopTimeB=(Button)findViewById(R.id.stoptime);
        timeTx=(TextView)findViewById(R.id.time);
        getTimeB.setOnClickListener(this);
        startTimeB.setOnClickListener(this);
        stopTimeB.setOnClickListener(this);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gettime:
                timeTx.setText(inputEt.getText().toString());
                i=Integer.parseInt(inputEt.getText().toString());
                break;
            case R.id.starttime:
                starttime();
                break;
            case R.id.stoptime:
                stoptime();
                break;
            default:
                break;
        }

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg){

            timeTx.setText(msg.arg1+"");
            starttime();
        };
    };


    public void starttime(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message = mHandler.obtainMessage();
               // Message message=new Message();
                message.arg1=i;

                mHandler.sendMessage(message);
            }
        };
        timer.schedule(task,1000);

    }
    public void stoptime(){
        timer.cancel();
    }
}
