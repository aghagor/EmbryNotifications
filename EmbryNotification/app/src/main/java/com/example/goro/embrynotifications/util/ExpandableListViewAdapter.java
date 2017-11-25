package com.example.goro.embrynotifications.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.goro.embrynotifications.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private LinkedHashMap<String, List<Data>> input;

    private LayoutInflater inflater;

    public ExpandableListViewAdapter(LayoutInflater inflater, LinkedHashMap<String, List<Data>> input) {
        super();
        this.input = input;
        this.inflater = inflater;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return getChildData(groupPosition, childPosition);
    }

    private Data getChildData(int groupPosition, int childPosition) {
        String key = getKey(groupPosition);
        List<Data> list = input.get(key);
        return list.get(childPosition);
    }

    private String getKey(int keyPosition) {
        int counter = 0;
        Iterator<String> keyIterator = input.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            if (counter++ == keyPosition) {
                return key;
            }
        }
        // will not be the case ...
        return null;
    }



    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getChildData(groupPosition, childPosition).getId();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        TextView simpleTextView = null;
        if (convertView == null) {
            // inflate what you need, for testing purposes I am using android
            // built-in layout
            simpleTextView = (TextView) inflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
        } else {
            simpleTextView = (TextView) convertView;
        }
        Data data = getChildData(groupPosition, childPosition);
        simpleTextView.setText(data.toString());
        return simpleTextView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = getKey(groupPosition);
        return input.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return getKey(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return input.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        TextView simpleTextView = null;
        if (convertView == null) {
            // inflate what you need, for testing purposes I am using android
            // built-in layout
            simpleTextView = (TextView) inflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
        } else {
            simpleTextView = (TextView) convertView;
        }
        simpleTextView.setText(getKey(groupPosition));
        return simpleTextView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
