package com.example.goro.embrynotifications.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class StatisticsFragment extends Fragment {

    private DatabaseHelper myDB;
    private List<Object> theList;
    private Cursor data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        myDB = new DatabaseHelper(getContext());
        final ListView listView = view.findViewById(R.id.list_view);

        theList = new ArrayList<>();


        data = myDB.showData();
        if (data.getCount() == 0) {
            Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {


                theList.add("Date:      " + data.getString(1) + "\n" + "Weight:  " + data.getString(2) + " kg");
                ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }

        super.onViewCreated(view, savedInstanceState);
    }

}
