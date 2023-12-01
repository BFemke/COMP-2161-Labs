package emke.comp2161.securitytoken;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button verifyBtn;
    TextView code;
    TextView timeRemaining;
    ArrayList<String> timeLogs = new ArrayList<>();
    Calendar calendar;
    private SharedPreferences lastLog;
    private SharedPreferences.Editor editor;

    /*
    Creates a Broadcast Receiver that received the broadcast message sent every second in order to
    update the time remaining
     */
    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int time = intent.getIntExtra("time", -1);

            //Executes if valid time received
            if(time>-1) {
                //Updates time remaining using broadcast value
                timeRemaining.setText(time + " seconds remaining");
            }

        }
    };

    /*
    Creates a new broadcast receiver that receives the broadcast message that is sent out when the
    code is updated, this allows the ArrayList to be updated with another log time and to display
    the newly generated code
     */
    private BroadcastReceiver codeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int newCode = intent.getIntExtra("code", 0);

            if (newCode > 0) {
                //Sets the textview with updated generated code
                code.setText(newCode+"");

                //Gets and adds new time to Array List of logs
                calendar = Calendar.getInstance();
                String date = String.valueOf(calendar.getTime());
                timeLogs.add(date);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastLog = getSharedPreferences("LogPrefFile",0);
        editor = lastLog.edit();

        //If there is a value stored in the shared preferences it will add it to the Array list for logs
        if(lastLog != null){
            String time = lastLog.getString("log", "");
            timeLogs.add(time);
        }

        verifyBtn = (Button) findViewById(R.id.verifyButton);
        code = (TextView) findViewById(R.id.generatedCode);
        timeRemaining = (TextView) findViewById(R.id.timeRemaining);

        //Registers receivers so we can start receiving messages
        registerReceiver(timeReceiver, new IntentFilter("UPDATE_TIME"));
        registerReceiver(codeReceiver, new IntentFilter("UPDATE_CODE"));

        //Ensures that the service will not be started multiple times
        if(savedInstanceState == null) {
            Intent i = new Intent(getApplicationContext(), TimeService.class);
            startService(i);
        }

    }

    @Override
    //Saves code value for orientation changes
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("code", code.getText().toString());
    }

    @Override
    //Restores saved code value after orientation change
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        code.setText(savedInstanceState.getString("code"));
    }

    @Override
    //Stores latest time log from Array List in Shared Preferences
    protected void onPause() {
        String time = timeLogs.get(timeLogs.size()-1);
        if(!time.isEmpty() && !time.equals(null)){
            editor.putString("log", time);
        }
        else{
            editor.clear();
        }
        editor.commit();
        super.onPause();
    }

    @Override
    //Gets the array list back from the TimestampActivity to keep accurate loog
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        timeLogs = data.getStringArrayListExtra("strArray");
        registerReceiver(codeReceiver, new IntentFilter("UPDATE_CODE"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisters receivers
        unregisterReceiver(timeReceiver);
        unregisterReceiver(codeReceiver);

        //Stores latest time log from Array List in Shared Preferences
        String time = timeLogs.get(timeLogs.size()-1);
        if(!time.isEmpty() && !time.equals(null)){
            editor.putString("log", time);
        }
        else{
            editor.clear();
        }
        editor.commit();

        //Stops service
        stopService(new Intent( this, TimeService.class ) );

    }

    /*
    Starts the TimestampActivity using intents with the purpose to get a result back, it sends the
    current Array List and expects to receive the array list back, this is so the log can still be
    updated while in the TimestampActivity and remain consistent
     */
    public void verifyClicked(View v){
        Intent intent = new Intent(v.getContext(), TimestampActivity.class);
        intent.putStringArrayListExtra("strArray", timeLogs);
        unregisterReceiver(codeReceiver);
        startActivityForResult(intent, 1);

    }
}