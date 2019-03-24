package com.example.myownproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtSignUpEmail,edtSignUpName,edtSignUpPassword;
    private Button btnSignUpSignUp,btnSignUpLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtSignUpEmail =findViewById(R.id.edtLoginEmail);
        edtSignUpName = findViewById(R.id.edtSignUpName);
        edtSignUpPassword = findViewById(R.id.edtLoginPassword);

        btnSignUpSignUp = findViewById(R.id.btnLoginSignUp);
        btnSignUpLogIn = findViewById(R.id.btnSignUpLogIn);

      edtSignUpPassword.setOnKeyListener(new View.OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
              if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_DOWN){
                  onClick(btnSignUpSignUp);
              }

              return false;
          }
      });

        btnSignUpSignUp.setOnClickListener(this);
        btnSignUpLogIn.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
           // ParseUser.getCurrentUser().logOut();
            transitionToTwitterCloneApp();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

     case R.id.btnLoginSignUp:
         if (edtSignUpEmail.getText().toString().equals("") ||
             edtSignUpName.getText().toString().equals("") ||
                 edtSignUpPassword.getText().toString().equals("")){
             FancyToast.makeText(SignUp.this,"Email,UserName or Password is required", Toast.LENGTH_SHORT,FancyToast.INFO,true).show();

         }else {

             final ParseUser appUser = new ParseUser();
             appUser.setEmail(edtSignUpEmail.getText().toString());
             appUser.setUsername(edtSignUpName.getText().toString());
             appUser.setPassword(edtSignUpPassword.getText().toString());

             final ProgressDialog progressDialog = new ProgressDialog(this);
             progressDialog.setMessage("Signing Up" + edtSignUpName);

             appUser.signUpInBackground(new SignUpCallback() {
                 @Override
                 public void done(ParseException e) {
                     if (e == null){
                  FancyToast.makeText(SignUp.this,appUser.getUsername() +
                          "is Signed up" , Toast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                         transitionToTwitterCloneApp();

                     }else {
              FancyToast.makeText(SignUp.this,"There was an error:" + e.getMessage()  , Toast.LENGTH_LONG, FancyToast.SUCCESS,true).show();

                     }
                progressDialog.dismiss();
                 }
             });
         }

        break;
     case R.id.btnSignUpLogIn:

         Intent intent = new Intent(SignUp.this,LogIn.class);

            startActivity(intent);
        break;

        }
    }

    public void rootLayoutTapped(View view){


        try {

            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToTwitterCloneApp(){

        Intent intent = new Intent(SignUp.this, TwitterCloneApp.class);
        startActivity(intent);
    }
}
