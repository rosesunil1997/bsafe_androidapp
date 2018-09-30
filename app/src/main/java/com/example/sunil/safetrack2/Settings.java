package com.example.sunil.safetrack2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    EditText  etPassword, etConfirm, etEmail, etTelOne, etTelTwo, etTelThree;
    TextView tvUserName;
    Button btnEditAccount;
    LoginDataBaseAdapter db;
    String username ;
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //instance of database adaptor
        db = new LoginDataBaseAdapter(this);
        //opens database if exits otherwise creates one

        // Refferences of Edittexts
        tvUserName = (TextView) findViewById(R.id.TextViewUserName);
        etPassword = (EditText) findViewById(R.id.editTextPassWord);
        etConfirm = (EditText) findViewById(R.id.editTextConfirm);
        etEmail = (EditText) findViewById(R.id.editTextUserName);
        etTelOne = (EditText) findViewById(R.id.editTextTelOne);
        etTelTwo = (EditText) findViewById(R.id.editTextTelTwo);
        etTelThree = (EditText) findViewById(R.id.editTextTelThree);
        btnEditAccount = (Button) findViewById(R.id.buttonSaveChanges);
        Intent intentUserName = getIntent() ;
        username = intentUserName.getStringExtra("UserName") ;
        Users user = db.getUser(username) ;
        id = user.getID() ;
        tvUserName.setText (user.getUserName() ) ;
        etEmail.setText (user.getEmail() ) ;
        etPassword.setText(user.getPassWord() );
        etConfirm.setText(user.getPassWord() );
        etTelOne.setText(user.getTelePhone1() ) ;
        etTelTwo.setText(user.getTelePhone2() ) ;
        etTelThree.setText(user.getTelePhone3() ) ;
        //etUserName.setText (user.getUserName() ) ;
        btnEditAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String userName = tvUserName.getText().toString();
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
                    Users users = new Users ( id,userName, password ,email,
                            telone,teltwo,telthree);

                    db.updateUser( users) ;

                    //db.updateuser( userName, password, email, telone, teltwo, telthree);
                    Toast.makeText(getApplicationContext(), "Account Successfully Updated ", Toast.LENGTH_LONG).show();
                    Intent intentEditAccount = new Intent(getApplicationContext(), home.class);
                    startActivity(intentEditAccount);
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