package com.example.administrator.myapplication.icompus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.nostra13.universalimageloader.utils.L;

/**
 * Created by Administrator on 2016/10/24.
 */

public class MyReceiver  extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
            String num = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TelephonyManager tm= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int state=tm.getCallState();
            Log.i("111","num:"+num+"     state:"+state);
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.i("111","闲置...");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.i("111","挂断...");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("111","响铃....");
                    break;
            }
        }
    }
}

