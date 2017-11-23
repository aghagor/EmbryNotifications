package com.example.goro.embrynotifications.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goro.embrynotifications.R;


public class WeightFragment extends Fragment {


    private String loginText;
    private TextView loginTextWeight;
    private EditText weightEditText;
    private Button analiticsBtn;

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
        analiticsBtn = view.findViewById(R.id.analitics_btn);

        analiticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("weight", weightEditText.getText().toString());


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                StatisticsFragment statisticsFragment = new StatisticsFragment();
                statisticsFragment.setArguments(bundle);
                transaction.replace(R.id.main, statisticsFragment).addToBackStack(null).commit();
            }
        });

        loginTextWeight.setText("Hello " + loginText);
        super.onViewCreated(view, savedInstanceState);
    }
}






