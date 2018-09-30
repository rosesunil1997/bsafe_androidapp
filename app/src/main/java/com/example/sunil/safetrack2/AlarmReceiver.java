package com.example.sunil.safetrack2;

/**
 * Created by Sunil on 17/Nov/2017.
 */

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.SmsManager;
import android.widget.Toast;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message


        myjourney inst = myjourney.instance();

        //inst.setAlarmText("Alarm! Wake up! Wake up!");
        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
      // Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
      //  if (alarmUri == null) {
      //      alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
       // }
       // Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
       // ringtone.play();
        String phoneNo1= inst.tel1 ;
        String phoneNo2= inst.tel2 ;
        String phoneNo3= inst.tel3 ;
        String username= inst.username ;
        String location = inst.edTextLocation.getText().toString()   ;
        String destination= inst.edTextDestination.getText().toString()   ;
        //"9446232515";phone number to which SMS to be send
        String message="I am " + username + " on my way to " + destination + " from " + location + "I think I am in trouble. This is an automated SMS. Please call back or else bring help! "  ;// message to send
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage( phoneNo1, null, message, null, null);
            smsManager.sendTextMessage( phoneNo2, null, message, null, null);
            smsManager.sendTextMessage( phoneNo3, null, message, null, null);
            Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "SMS failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);

    }
}