package com.example.goro.embrynotifications.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.database.DatabaseHelper;
import com.example.goro.embrynotifications.util.AlarmReciverNotification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class WeightFragment extends Fragment {


    private String loginText;
    private TextView loginTextWeight;
    private EditText weightEditText;
    private Button statisticBtn;
    private Button addBtn;
    private CheckBox notifyChB;

    private List<String> list;
    private DatabaseHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        loginText = getArguments().getString("login");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        loginTextWeight = view.findViewById(R.id.login_tv_weight);
        weightEditText = view.findViewById(R.id.weight_et);
        statisticBtn = view.findViewById(R.id.statistic_btn);
        addBtn = view.findViewById(R.id.add_btn);
        myDB = new DatabaseHelper(getContext());
        notifyChB = view.findViewById(R.id.notifeied_chB);


        list = new ArrayList<String>();
        list.add(weightEditText.getText().toString());


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
                String currentTime = df.format(Calendar.getInstance().getTime());

                String dayTime = String.valueOf(currentTime);

                String newEntry = weightEditText.getText().toString();
                if (weightEditText.length() != 0) {
                    AddData(dayTime, newEntry);
                    weightEditText.setText("");
                    startAlarm();
                    notifyChB.setChecked(false);
                } else {
                    Toast.makeText(getContext(), "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }
        });

        statisticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                StatisticsFragment statisticsFragment = new StatisticsFragment();
                transaction.replace(R.id.main, statisticsFragment).addToBackStack(null).commit();
            }
        });

        loginTextWeight.setText("Hello " + loginText);
        super.onViewCreated(view, savedInstanceState);
    }

    public void AddData(String date, String newEntry) {

        boolean insertData = myDB.addData(date, newEntry);

        if (insertData == true) {
            Toast.makeText(getContext(), "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

    private void startAlarm() {
        AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(getContext(), AlarmReciverNotification.class);
        PendingIntent contentIntent = PendingIntent.getService(getContext(), 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + AlarmManager.INTERVAL_HALF_HOUR , AlarmManager.INTERVAL_DAY, contentIntent);
        manager.cancel(contentIntent);
    }
}






