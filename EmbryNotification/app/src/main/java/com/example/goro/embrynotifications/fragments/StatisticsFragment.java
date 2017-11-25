package com.example.goro.embrynotifications.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.goro.embrynotifications.R;
import com.example.goro.embrynotifications.database.DatabaseHelper;
import com.example.goro.embrynotifications.util.Data;
import com.example.goro.embrynotifications.util.ExpandableListViewAdapter;
import com.example.goro.embrynotifications.util.ExpandableListViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private ExpandableListView expListView;
    private LinkedHashMap<String, List<Data>> mockList;
    private ArrayList<Data> children;
    private ExpandableListViewItem[] collections;
    private ArrayList<Object> theList;
    private Cursor data;
    private StringBuffer buffer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        buffer = new StringBuffer();

//        expListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
//        fillList();
        myDB = new DatabaseHelper(getContext());


        final ListView listView = view.findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (data.getCount() == 0) {
                    Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
                } else {

                    while (data.moveToNext()) {

                        theList.add(data.getString(2) + " kg");
                        ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, theList);
                        listView.setAdapter(listAdapter);

                        buffer.append("Weight: " + data.getString(2) + "kg" + "\n");
                        buffer.append("---------------------------------------" + "\n");
                        display("All Stored Data:", buffer.toString());
                    }
                }
            }
        });
        theList = new ArrayList<>();
        data = myDB.showData();
        if (data.getCount() == 0) {
            Toast.makeText(getContext(), "There are no contents in this list!", Toast.LENGTH_LONG).show();

        } else {


//            while (data.moveToNext()) {
//
//
////                buffer.append("ID: " + data.getString(0) + "\n");
//                buffer.append("Date: " + data.getString(1) + "\n");
//                buffer.append("Weight: " + data.getString(2) + "kg" + "\n");
//                buffer.append("---------------------------------------" + "\n");
//
//
//
//                display("All Stored Data:", buffer.toString());
//            }
            while (data.moveToNext()) {

                theList.add(data.getString(1));
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

    private void fillList() {
        LinkedHashMap<String, List<Data>> input = getMockList(); // get the collection here
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(LayoutInflater.from(getContext()), input);
        expListView.setAdapter(adapter);
    }


    public LinkedHashMap<String, List<Data>> getMockList() {

//        Cursor c = myDB.showData();
//        children = new ArrayList<Data>();
//        if (c != null && c.moveToFirst()) {
//            for (ExpandableListViewItem item : collections) {
//                // search proper child for current item
//                do {
//                    // if current row date value equals with current item datetime
//                    if (item.getTitle().equals(c.getString(1))) {
//                        children.add(new Data(1,c.getString(2),c.getString(3))); // fetch data from columns
//                    }
//                } while (c.moveToNext());
//
//                // assign created children into current item
//                item.setChildren(children);
//
//                // reset List that will be used for next item
//                children = null;
//                children = new ArrayList<Data>();
//
//                // reset Cursor and move it to first row again
//                c.moveToFirst();
//            }
//        }
        return mockList;
    }
//    public LinkedHashMap<String, List<Data>> readData(SQLiteDatabase db) {
//        LinkedHashMap<String, List<Data>> result = new LinkedHashMap<String, List<Data>>();
//        Cursor cursor = null;
//        try {
//            cursor = db.query("MY_TABLE", new String[]{
//                    "datetime", "id", "col1", "col2"
//            }, null, null, null, null, "datetime, id ASC");
//            while (cursor.moveToNext()) {
//                String dateTime = cursor.getString(cursor.getColumnIndex("datetime"));
//                int id = cursor.getInt(cursor.getColumnIndex("id"));
//                String col1 = cursor.getString(cursor.getColumnIndex("col1"));
//                String col2 = cursor.getString(cursor.getColumnIndex("col2"));
//                List<Data> list = null;
//                if (result.containsKey(dateTime)) {
//                    list = result.get(dateTime);
//                } else {
//                    list = new ArrayList<Data>();
//                    result.put(dateTime, list);
//                }
//                list.add(new Data(id, col1, col2));
//            }
//        } catch (Exception ex) {
//            Log.e("TAG", null, ex);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        return result;
//    }

}
