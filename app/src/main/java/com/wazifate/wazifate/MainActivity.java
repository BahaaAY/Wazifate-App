package com.wazifate.wazifate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wazifate.wazifate.HomeScreen.HomeActivity;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton= findViewById(R.id.loginButton);
        mContext = this;
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, HomeActivity.class);
                startActivity(i);

            }
        });

    }




}