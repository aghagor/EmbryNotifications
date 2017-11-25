package com.example.goro.embrynotifications.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class StatisticsFragment extends Fragment {

    private DatabaseHelper myDB;
    private ArrayList<Object> theList;
    private Cursor data;
    private StringBuffer buffer;
    private TextView loginText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        buffer = new StringBuffer();
        myDB = new DatabaseHelper(getContext());
        final ListView listView = view.findViewById(R.id.list_view);
        loginText = view.findViewById(R.id.login_et);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (data.getCount() == 0) {
//                    Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
//                } else {
//
//                    while (data.moveToNext()) {
//
//                        theList.add(data.getString(2) + " kg");
//                        ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, theList);
//                        listView.setAdapter(listAdapter);
//
//                        buffer.append("Weight: " + data.getString(2) + "kg" + "\n");
//                        buffer.append("---------------------------------------" + "\n");
//                        display("All Stored Data:", buffer.toString());
//                    }
//                }
            }
        });
        theList = new ArrayList<>();
        data = myDB.showData();
        if (data.getCount() == 0) {
            Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();

        } else {


//            while (data.moveToNext()) {
//
//                buffer.append("Date: " + data.getString(1) + "\n");
//                buffer.append("Weight: " + data.getString(2) + "kg" + "\n");
//                buffer.append("---------------------------------------" + "\n");
//
//                display("All Stored Data:", buffer.toString());
//            }
            while (data.moveToNext()) {

                theList.add("Date:      " + data.getString(1) + "\n" + "Weight:  " + data.getString(2) + " kg");
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
