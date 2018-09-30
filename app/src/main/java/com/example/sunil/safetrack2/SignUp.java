package com.example.sunil.safetrack2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText etUserName, etPassword, etConfirm, etEmail, etTelOne, etTelTwo, etTelThree;
    Button btnCreateAccount;
    LoginDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //instance of database adaptor
        db = new LoginDataBaseAdapter(this);
        //opens database if exits otherwise creates one

        // Refferences of Edittexts
        etUserName = (EditText) findViewById(R.id.editTextUserName);
        etPassword = (EditText) findViewById(R.id.editTextPassWord);
        etConfirm = (EditText) findViewById(R.id.editTextConfirm);
        etEmail = (EditText) findViewById(R.id.editTextUserName);
        etTelOne = (EditText) findViewById(R.id.editTextTelOne);
        etTelTwo = (EditText) findViewById(R.id.editTextTelTwo);
        etTelThree = (EditText) findViewById(R.id.editTextTelThree);
        btnCreateAccount = (Button) findViewById(R.id.buttonSignUp);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirm.getText().toString();
                String email = etEmail.getText().toString();
                String telone = etTelOne.getText().toString();
                String teltwo = etTelTwo.getText().toString();
                String telthree = etTelThree.getText().toString();
                // check if any of the fields are vaccant
                if (userName.equals("") || password.equals("") || email.equals("") || telone.equals("") || teltwo.equals("") || telthree.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the Data in Database
                    Users users = new Users ( 0,userName, password ,email,
                            telone,teltwo,telthree);

                    db.addUser ( users) ;
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent intentCreateAccount = new Intent(getApplicationContext(), Login.class);
                    startActivity(intentCreateAccount);
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