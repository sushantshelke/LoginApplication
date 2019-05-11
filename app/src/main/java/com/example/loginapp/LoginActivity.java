package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.editText_username);
        mTextPassword = (EditText) findViewById(R.id.editText_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textView_register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


    mButtonLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = mTextUsername.getText().toString().trim();
            String pwd = mTextPassword.getText().toString().trim();
            Boolean res = db.checkUser(user, pwd);
            if(res == true)
            {
                Intent HomePage = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(HomePage);
            }
            else
            {
                Toast.makeText(LoginActivity.this,"Login Error", Toast.LENGTH_SHORT).show();
            }
        }
    });
}

    public void showProgressingView() {

        if (!isProgressShowing) {
           // View view=findViewById(R.id.progressBar1);
            //view.bringToFront();
        }
    }

    public void hideProgressingView() {
        View v = this.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView(progressView);
        isProgressShowing = false;
    }
}
