package com.example.goro.embrynotifications;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.goro.embrynotifications.fragments.WeightFragment;

public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private EditText emailText;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginText = findViewById(R.id.login_et);
        passwordText = findViewById(R.id.password_et);
        emailText = findViewById(R.id.email_et);
        submitBtn = findViewById(R.id.registration_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                WeightFragment speedFragment = new WeightFragment();
                transaction.add(R.id.main, speedFragment).addToBackStack(null).commit();

            }
        });

    }

}
