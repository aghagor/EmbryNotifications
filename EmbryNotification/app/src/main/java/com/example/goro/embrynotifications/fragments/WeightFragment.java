package com.example.goro.embrynotifications.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.goro.embrynotifications.SerializeObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class WeightFragment extends Fragment {


    private String loginText;
    private TextView loginTextWeight;
    private EditText weightEditText;
    private Button analiticsBtn;

    private List<String> list;
    private ConcurrentHashMap<String, String> sharedPreferencesEditor;
    private List<String> items;
    private ArrayList<String> give;
    private Context act;

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

        list = new ArrayList<String>();
        list.add(weightEditText.getText().toString());

        analiticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ser = SerializeObject.objectToString(give);
                if (ser != null && !ser.equalsIgnoreCase("")) {
                    SerializeObject.WriteSettings(getContext(), ser, "myobject.dat");
                } else {
                    SerializeObject.WriteSettings(getContext(), "", "myobject.dat");
                }

//                StringBuilder csvList = new StringBuilder();
//                for (String s : list) {
//                    csvList.append(s);
//                    csvList.append(",");
//                }
//
//                SharedPreferences preferences = getContext().getSharedPreferences("PREF", 0);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("weights", csvList.toString());
//                editor.commit();

                Bundle bundle = new Bundle();
                bundle.putSerializable("weight", (Serializable) items);


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






