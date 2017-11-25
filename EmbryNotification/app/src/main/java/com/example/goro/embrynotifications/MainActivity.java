package com.example.goro.embrynotifications;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goro.embrynotifications.fragments.WeightFragment;

public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private EditText emailText;
    private TextView loginTv;
    private TextView passwordTv;
    private TextView emailTv;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginText = findViewById(R.id.login_et);
        passwordText = findViewById(R.id.password_et);
        emailText = findViewById(R.id.email_et);

        loginTv = findViewById(R.id.login_tv);
        passwordTv = findViewById(R.id.password_tv);
        emailTv = findViewById(R.id.email_tv);

        submitBtn = findViewById(R.id.registration_btn);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginText.getText().toString().matches("")) {
                    loginTv.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), "Enter login please!!!", Toast.LENGTH_SHORT).show();
                    return;
                } else loginTv.setTextColor(getResources().getColor(R.color.black));
                if (passwordText.getText().toString().matches("")) {
                    passwordTv.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), "Enter password please!!!", Toast.LENGTH_SHORT).show();
                    return;
                } else passwordTv.setTextColor(getResources().getColor(R.color.black));
                if (emailText.getText().toString().matches("")) {
                    emailTv.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), "Enter your Email please!!!", Toast.LENGTH_SHORT).show();
                    return;
                } else emailTv.setTextColor(getResources().getColor(R.color.black));
                if (loginText.getText().length() != 0 &&
                        passwordText.getText().length() != 0 &&
                        emailText.getText().length() != 0) {
                    goToWeightFragment();
                    setContentView(R.layout.activity_main);
                }

            }
        });
    }

    private void goToWeightFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("login", loginText.getText().toString());


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        WeightFragment weightFragment = new WeightFragment();
        weightFragment.setArguments(bundle);
        transaction.add(R.id.main, weightFragment).addToBackStack(null).commit();

    }

}


