package com.example.sunil.safetrack2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends AppCompatActivity {
    Button btnSignIn, btnRegister;
    //EditText editTextUserName, editTextPassword;
    LoginDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //references fot BUTTONS
        btnSignIn=(Button)findViewById(R.id.buttonSignIn);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        //references for edit text
        final EditText editTextUserName=(EditText) findViewById(R.id.editTextUserName);
        final EditText editTextPassword=(EditText) findViewById(R.id.editTextPassWord);

        // create a instance of SQLite Database
        db =new LoginDataBaseAdapter(this);
        //listner for signup button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUp = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intentSignUp);
            }
        });

        //listner for signin button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String storedPassword=db.getPassword(username) ;

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(Login.this, " Login Successfull", Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                    Intent intentHome= new Intent(getApplicationContext(), home.class);
                    intentHome.putExtra("UserName",username);
                    startActivity(intentHome);

                }
                else
                {
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }

}
