package com.example.goro.embrynotifications;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

//        if (loginText.equals(" ") || passwordText.equals(" ") || emailText.equals(" ")) {
//            submitBtn.setEnabled(false);
//        }
//

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginText.getText().toString().matches(" ")) {
                    Toast.makeText(getBaseContext(), "Enter login please!!!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (passwordText.getText().toString().matches(" ")) {
                    Toast.makeText(getApplicationContext(), "Enter password please!!!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (emailText.getText().toString().matches(" ")) {
                    Toast.makeText(getApplicationContext(), "Enter your Email please!!!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("login", loginText.getText().toString());


                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    WeightFragment weightFragment = new WeightFragment();
                    weightFragment.setArguments(bundle);
                    transaction.replace(R.id.main, weightFragment).addToBackStack(null).commit();


                }

            }
        });

    }
}


