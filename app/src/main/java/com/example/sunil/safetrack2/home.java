package com.example.sunil.safetrack2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class home extends AppCompatActivity {
    //Button btnMyJourney;
    ImageButton btnPanic;
    Button btnStop;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // btnMyJourney=(Button) findViewById(R.id.buttonMyjourney);
        btnPanic= (ImageButton) findViewById(R.id.buttonPanic);
        btnStop= (Button) findViewById(R.id.buttonStop);
        Intent intentLogin = getIntent();
        username = intentLogin.getStringExtra("UserName") ;

        /*btnMyJourney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                    Intent intentMyJourney = new Intent(getApplicationContext(), myjourney.class);
                    intentMyJourney.putExtra("UserName", username);
                    startActivity(intentMyJourney);
            }
        });*/


        btnPanic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //SEND PANIC MESSAGE HERE
                Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();


            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(getApplicationContext(), "JOURNEY  STOPPED", Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu manu)
    {
        getMenuInflater().inflate(R.menu.home,manu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_trip:
                // User chose the "Settings" item, show the app settings UI...
                Intent intentMyJourney = new Intent(getApplicationContext(), myjourney.class);
                intentMyJourney.putExtra("UserName", username);
                startActivity(intentMyJourney);

                return true;

            case R.id.action_profile:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intentSettings= new Intent(getApplicationContext(), Settings.class);
                intentSettings.putExtra("UserName", username);
                startActivity(intentSettings);
                return true;

            case R.id.action_exit:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}

