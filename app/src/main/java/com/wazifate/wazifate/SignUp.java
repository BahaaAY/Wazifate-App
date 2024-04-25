package com.wazifate.wazifate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.wazifate.wazifate.ApiService.ApiService;
import com.wazifate.wazifate.ApiService.RetrofitClient;
import com.wazifate.wazifate.HomeScreen.HomeActivity;
import com.wazifate.wazifate.Models.auth.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText usernameTextField, passwordTextField, firstNameTextField, lastNameTextField, majorTextField, phoneTextField, addressTextField;
    Button signUpButton;

    ApiService apiService;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usernameTextField = findViewById(R.id.signupUsernameTextField);
        passwordTextField = findViewById(R.id.signupPasswordTextField);
        firstNameTextField = findViewById(R.id.signupFirstNameTextField);
        lastNameTextField = findViewById(R.id.signupLastNameTextField);
        majorTextField = findViewById(R.id.signupMajorTextField);
        phoneTextField = findViewById(R.id.signupPhoneNumberTextField);
        addressTextField = findViewById(R.id.signupAddressTextField);
        signUpButton = findViewById(R.id.signUpSignButton);
        apiService = RetrofitClient.getInstance().getRestApi();
        mContext = this;

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide Keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if(usernameTextField.getText().toString().isEmpty() || passwordTextField.getText().toString().isEmpty() || firstNameTextField.getText().toString().isEmpty() || lastNameTextField.getText().toString().isEmpty() || majorTextField.getText().toString().isEmpty() || phoneTextField.getText().toString().isEmpty() || addressTextField.getText().toString().isEmpty())
                {
                    Snackbar.make(view, "Please fill all the fields", Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.red)).show();
                    return;
                }

                // Send Signup request to server

                apiService.signupUser(usernameTextField.getText().toString(), passwordTextField.getText().toString(), firstNameTextField.getText().toString(), lastNameTextField.getText().toString(), majorTextField.getText().toString(), phoneTextField.getText().toString(), addressTextField.getText().toString()).enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        if(response.isSuccessful())
                        {
                            //clear all fields
                            usernameTextField.setText("");
                            passwordTextField.setText("");
                            firstNameTextField.setText("");
                            lastNameTextField.setText("");
                            majorTextField.setText("");
                            phoneTextField.setText("");
                            addressTextField.setText("");
                           Snackbar.make(view, "Signup Successful", Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.green)).addCallback(new Snackbar.Callback(){
                                 @Override
                                 public void onDismissed(Snackbar transientBottomBar, int event) {

                                     //redirect to login page

                                     Intent intent = new Intent(SignUp.this, MainActivity.class);
                                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                                     startActivity(intent);
                                     finish();


                                 }
                           }).show();










                        }
                        else
                        {
                            Snackbar.make(view, "Error: " + response.message(), Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.red)).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Snackbar.make(view, "Error: " + t.getMessage(), Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.red)).show();
                    }
                });


            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}