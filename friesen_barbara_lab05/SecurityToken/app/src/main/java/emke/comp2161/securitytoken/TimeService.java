package emke.comp2161.securitytoken;


import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Calendar;



public class TimeService extends Service {

    Calendar calendar;

    //Mandatory Binder that we are not using
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Creates initial code upon creation and sends out broadcast to update activities
    @Override
    public void onCreate() {
        super.onCreate();
        calendar = Calendar.getInstance();      //Gets current time

        //Creates intent with new calculated code
        Intent intentCode = new Intent("UPDATE_CODE");
        int i = calendar.get(Calendar.MINUTE)*1245+10000;
        intentCode.putExtra("code", i);

        sendBroadcast(intentCode);  //Sends broadcast with intent

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Creates a 60 second timer with a tick each second
       new CountDownTimer(60000, 1000) {
             @Override
             //Executes each second and sends updated time value through broadcast
             public void onTick(long l) {
                 Intent intentTime = new Intent("UPDATE_TIME");
                 int i = Math.round(l/1000);
                 intentTime.putExtra("time", i);
                 sendBroadcast(intentTime);
             }

             @Override
             /*
             Executes after the 60 second timer is done and creates a new code and sends it out
             using a broadcast
             */
             public void onFinish() {
                 calendar = Calendar.getInstance();
                 Intent intentCode = new Intent("UPDATE_CODE");
                 int i = calendar.get(Calendar.MINUTE)*1245+10000;
                 intentCode.putExtra("code", i);
                 sendBroadcast(intentCode);

                 start();       //Restarts the count down timer
             }
         }.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}