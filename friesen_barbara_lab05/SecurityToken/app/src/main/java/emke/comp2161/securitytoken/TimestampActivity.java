package emke.comp2161.securitytoken;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class TimestampActivity  extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    private RecyclerView timeLog;
    private Button clear, back;
    private LogAdapter adapter;
    public ArrayList<String> timeLogs;

    /*
    Creates a new broadcast receiver that receives the broadcast message that is sent out when the
    code is updated, this allows the log to be updated with the new log time
     */
    private BroadcastReceiver codeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int newCode = intent.getIntExtra("code", 0);

            if (newCode > 0) {
                //Gets current date and  time
                calendar = Calendar.getInstance();
                String date = String.valueOf(calendar.getTime());

                //Updates the recycler view
                timeLogs.add(date);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timestamp);

        //Registers receiver to update log
        registerReceiver(codeReceiver, new IntentFilter("UPDATE_CODE"));

        //Gets current log from MainActivity
        timeLogs = getIntent().getStringArrayListExtra("strArray");

        clear = (Button) findViewById(R.id.clearButton);
        back = (Button) findViewById(R.id.backButton);
        timeLog = (RecyclerView) findViewById(R.id.timestampLog);

        //Sets up RecyclerView
        adapter = new LogAdapter(this, timeLogs);
        timeLog.setAdapter(adapter);
        timeLog.setLayoutManager(new LinearLayoutManager(this));

    }

    //Sends the updated array list back to MainActivity and ends activity
    public void backClicked(View v){
        Intent intent = new Intent();
        intent.putStringArrayListExtra("strArray", timeLogs);
        setResult(1, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(codeReceiver);      //Destroys Broadcast Receiver when unneeded
    }

    //Clears the current time log and updates the view
    public void clearClicked(View v){
        timeLogs.removeAll(timeLogs);
        adapter.notifyDataSetChanged();
    }
}
