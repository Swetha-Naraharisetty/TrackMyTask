package com.microsoft.track_my_task;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NotificationActivity extends AppCompatActivity {
    private static final String TAG = "NotificationActivity";
    int hour, am_pm, minutes;
    int delFlag = -1;
    String dTask = "";
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_notification);
        delFlag = getIntent().getFlags();
        // to dimiss the task when reached the location
        Log.i(TAG, "onCreate: delFlag" + delFlag);
        if (delFlag == 0) {
            Log.i(TAG, "onCreate: in delFlag");
            dTask = getIntent().getStringExtra("task_name");
            db.del_Task(dTask);
            Toast.makeText(NotificationActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
        }
       /* AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);



            Calendar cal = Calendar.getInstance();
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

            try {
                Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alert);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }


    }*/
    }
}
