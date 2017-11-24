package com.example.goro.embrynotifications.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    DatabaseHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        statistic = (ArrayList<String>) getArguments().getSerializable("weight");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        myDB = new DatabaseHelper(getContext());


        ListView listView = view.findViewById(R.id.list_view);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if (data.getCount() == 0) {
            Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                Date currentTime = Calendar.getInstance().getTime();
                
                String dayTime = String.valueOf(currentTime);
                theList.add(dayTime + " : " + data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }

        super.onViewCreated(view, savedInstanceState);
    }

}
