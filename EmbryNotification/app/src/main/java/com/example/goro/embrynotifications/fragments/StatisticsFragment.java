package com.example.goro.embrynotifications.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.SerializeObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StatisticsFragment extends Fragment {

    private List<String> items;
    private Handler handler;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> statistic;
    private String[] itemsWeight;
    private Context act;
    private ArrayList<String> give;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        statistic =(ArrayList<String>) getArguments().getSerializable("weight");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        TextView statisticTv = view.findViewById(R.id.statistic_tv);

        ListView listView = view.findViewById(R.id.list_view);

        String ser = SerializeObject.ReadSettings(getContext(), "myobject.dat");
        if (ser != null && !ser.equalsIgnoreCase("")) {
            Object obj = SerializeObject.stringToObject(ser);
            // Then cast it to your object and
            if (obj instanceof ArrayList) {
                // Do something
                give = (ArrayList<String>)obj;
            }
        }

//        SharedPreferences preferences = getContext().getSharedPreferences("PREF", 0);
//        String weightsList = preferences.getString("weights", "");
//        itemsWeight = weightsList.split(",");
//        items = new ArrayList<String>();
//        for (int i = 0; i < itemsWeight.length; i++) {
//            items.add(itemsWeight[i]);
//        }

        super.onViewCreated(view, savedInstanceState);
    }

}
