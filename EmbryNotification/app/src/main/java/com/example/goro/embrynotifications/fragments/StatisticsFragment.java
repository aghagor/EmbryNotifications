package com.example.goro.embrynotifications.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
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
    private DatabaseHelper myDB;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

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
        Cursor data = myDB.showData();
        if (data.getCount() == 0) {
            Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {

            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext()) {


//                buffer.append("ID: " + data.getString(0) + "\n");
                buffer.append("Date: " + data.getString(1) + "\n");
                buffer.append("Weight: " + data.getString(2) + "kg" + "\n");
                buffer.append("---------------------------------------" + "\n");



                display("All Stored Data:", buffer.toString());
            }
            while (data.moveToNext()) {

                theList.add(data.getString(2) + " kg");
                ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }

        super.onViewCreated(view, savedInstanceState);
    }

    private void display(String s, String s1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(s);
        builder.setMessage(s1);
        builder.show();
    }

}
