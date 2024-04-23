package com.wazifate.wazifate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wazifate.wazifate.HomeScreen.HomeActivity;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView signUpButton;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton= findViewById(R.id.loginButton);
        signUpButton= findViewById(R.id.signupButton);
        mContext = this;
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, HomeActivity.class);
                startActivity(i);

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, SignUp.class);
                startActivity(i);

            }
        });

    }




}