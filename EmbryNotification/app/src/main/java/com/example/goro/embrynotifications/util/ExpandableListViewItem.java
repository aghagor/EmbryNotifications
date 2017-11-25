package com.example.goro.embrynotifications.util;

import android.database.Cursor;
import android.util.Log;

import com.example.goro.embrynotifications.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListViewItem {

    private String title;
    private List<Data> children;
    private ExpandableListViewItem[] collections;

    public void setChildren(List<Data> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void addChild(Data child) {
        this.children.add(child);
    }
    private void getAll(DatabaseHelper myDB){
        Cursor c = myDB.showData();
        children = new ArrayList<Data>();
        if (c != null && c.moveToFirst()) {
            for (ExpandableListViewItem item : collections) {
                // search proper child for current item
                do {
                    // if current row date value equals with current item datetime
                    if (item.getTitle().equals(c.getString(1))) {
                        children.add(new Data(1,c.getString(2),c.getString(3))); // fetch data from columns
                    }
                } while (c.moveToNext());

                // assign created children into current item
                item.setChildren(children);

                // reset List that will be used for next item
                children = null;
                children = new ArrayList<Data>();

                // reset Cursor and move it to first row again
                c.moveToFirst();
            }
        }
    }

//    public LinkedHashMap<String, List<Data>> readData(DatabaseHelper myDB) {
//        LinkedHashMap<String, List<Data>> result = new LinkedHashMap<String, List<Data>>();
//        Cursor cursor = null;
//        try {
//            cursor = myDB.showData();
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