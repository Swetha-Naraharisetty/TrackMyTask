package com.microsoft.track_my_task;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Ayshu on 18-Feb-17.
 * hgctgvhyv
 */
//when a place is in proximity
public class ProximityReceiver extends BroadcastReceiver {
    private static final String TAG = "info";
    Location_represent lr = new Location_represent();
    String intentExtras;
    @Override
    public void onReceive(Context arg0, Intent intent) {
        //generateNotification(arg0);
        intentExtras = intent.getStringExtra("task_name");
        Log.i(TAG, "onReceive: task name" + intentExtras);
        if (intentExtras == null) {
            Log.i(TAG, "onReceive: "+ intentExtras);
        }
        else {
            //give notification here
            generateNotification(arg0);
            Toast.makeText(arg0, "here in receier", Toast.LENGTH_LONG).show();
            Log.i(TAG, "onReceive: "+ "here in receiceer");
            Log.i(TAG, "onReceive: "+ intentExtras);
            Intent intent1 = new Intent(arg0, Proximity_Alert_Activity.class);
            arg0.startActivity(intent1);
        }
    }


    void generateNotification( Context context){
        //Database database = new Database(context);
        int delFlag = 0;
        Intent notificationIntent = new Intent(context, HomeActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.activity_notification);
        notificationIntent.putExtra("Action", delFlag);
        notificationIntent.putExtra("task", intentExtras);

        PendingIntent deleteTask = PendingIntent.getService(context.getApplicationContext(), delFlag , notificationIntent, 0);

        view.setOnClickPendingIntent(R.id.del_task, deleteTask);


        // PendingIntent deleteTask = PendingIntent.getActivity(context, 124, notificationIntent,);

        /*   TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(HomeActivity.class);
            stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(101, PendingIntent.FLAG_UPDATE_CURRENT);*/

        PendingIntent pending_intent = PendingIntent.getActivity(context, 101, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //view.setOnClickPendingIntent(R.id.del_task, pending_intent);

        Log.i(TAG, "generateNotification: ");
        Notification notification = builder
                .setContentTitle("Track My Task")
                .setContentText("You have  a task here " + intentExtras)
                .setTicker("Task Alert!")
                .setSmallIcon(R.mipmap.track_my_task )
                .setAutoCancel(true)
                .build();

        try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, alert);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(101, notification);

    }
}
