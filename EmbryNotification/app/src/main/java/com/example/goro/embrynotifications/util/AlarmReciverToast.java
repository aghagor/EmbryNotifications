package com.example.goro.embrynotifications.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.goro.embrynotifications.fragments.WeightFragment;

/**
 * Created by Goro on 25.11.2017.
 */

public class AlarmReciverToast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "this is my alarm", Toast.LENGTH_SHORT).show();

        context.startActivity(new Intent(context, WeightFragment.class));
    }
}
