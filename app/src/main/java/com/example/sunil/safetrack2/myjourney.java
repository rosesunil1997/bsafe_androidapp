package com.example.sunil.safetrack2;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import java.util.Calendar;
public class myjourney extends AppCompatActivity {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static myjourney inst;
    EditText edTextLocation ;
    EditText edTextDestination;
    String username, tel1, tel2, tel3;
    LoginDataBaseAdapter db;
    public static myjourney instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myjourney);
        db = new LoginDataBaseAdapter(this);
        Intent intentMyJourney= getIntent() ;
        username = intentMyJourney.getStringExtra("UserName") ;
        Users user = db.getUser(username) ;
        tel1 = user.getTelePhone1() ;
        tel2 = user.getTelePhone2() ;
        tel3 = user.getTelePhone3() ;
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        edTextLocation= (EditText ) findViewById(R.id.editTextLocation );
        edTextDestination= (EditText ) findViewById(R.id.editTextDestination );
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        if(getSupportActionBar() !=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MyActivity", "Alarm On");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(myjourney.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(myjourney.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            //setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
