package com.example.myownproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnLoginSignUp,btnLoginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnLoginSignUp = findViewById(R.id.btnLoginSignUp);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);

        btnLoginSignUp.setOnClickListener(this);
        btnLoginLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.btnLoginLogin:
 ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
     @Override
     public void done(ParseUser user, ParseException e) {
         if (user != null && e == null){
             FancyToast.makeText(LogIn.this,user.getUsername() + " is Logged in Successfully", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
       transitionToTwitterCloneApp();
         }
     }
 });

            break;
        case R.id.btnLoginSignUp:
    }
    }
    private void transitionToTwitterCloneApp(){

        Intent intent = new Intent(LogIn.this, TwitterCloneApp.class);
        startActivity(intent);
    }
}
