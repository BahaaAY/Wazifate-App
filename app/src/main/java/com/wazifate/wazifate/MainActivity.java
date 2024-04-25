package com.wazifate.wazifate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.wazifate.wazifate.ApiService.ApiService;
import com.wazifate.wazifate.ApiService.RetrofitClient;
import com.wazifate.wazifate.HomeScreen.HomeActivity;
import com.wazifate.wazifate.Models.auth.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView signUpButton;
    EditText usernameTextField, passwordTextField;
    Context mContext;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton= findViewById(R.id.loginButton);
        signUpButton= findViewById(R.id.signupButton);
        usernameTextField= findViewById(R.id.usernameTextField);
        passwordTextField= findViewById(R.id.passwordTextField);
        apiService = RetrofitClient.getInstance().getRestApi();
        mContext = this;
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                if(usernameTextField.getText().toString().isEmpty() || passwordTextField.getText().toString().isEmpty())
                {
                    Snackbar.make(view, "Please fill all the fields", Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.red)).show();
                    return;
                }

                apiService.loginUser(usernameTextField.getText().toString(), passwordTextField.getText().toString()).enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        if(response.isSuccessful())
                        {
                            Intent i = new Intent(mContext, HomeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Snackbar.make(view, "Invalid username or password", Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.red)).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        System.out.println("Failed to login" + t.getMessage());
                    }
                });


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