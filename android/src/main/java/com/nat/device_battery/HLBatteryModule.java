package com.nat.device_battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashMap;

/**
 * Created by xuqinchao on 17/2/7.
 * Copyright (c) 2017 Nat. All rights reserved.
 */

public class HLBatteryModule {

    private Context mContext;
    private static volatile HLBatteryModule instance = null;

    private HLBatteryModule(Context context){
        mContext = context;
    }

    public static HLBatteryModule getInstance(Context context) {
        if (instance == null) {
            synchronized (HLBatteryModule.class) {
                if (instance == null) {
                    instance = new HLBatteryModule(context);
                }
            }
        }

        return instance;
    }

    public void status(HLModuleResultListener listener){
        Intent batteryIntent = mContext
                .registerReceiver( null ,
                        new IntentFilter( Intent.ACTION_BATTERY_CHANGED ) ) ;
        HashMap<String, Object> result = new HashMap<>();
        result.put("level", batteryIntent.getIntExtra(android.os.BatteryManager.EXTRA_LEVEL, 0));
        result.put("charging", batteryIntent.getIntExtra(android.os.BatteryManager.EXTRA_PLUGGED, -1) > 0 ? true : false);
        listener.onResult(result);
    }
}
