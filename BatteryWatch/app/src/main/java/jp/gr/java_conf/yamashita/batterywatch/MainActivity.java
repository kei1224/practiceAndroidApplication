package jp.gr.java_conf.yamashita.batterywatch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver mReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mReciever = new MyBroadcastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mReciever, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReciever);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            // 複数のインテントを受信する場合はif文で分岐する
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                int scale = intent.getIntExtra("scale", 0);
                int level = intent.getIntExtra("level", 0);
                int status = intent.getIntExtra("status", 0);
                String statusString = "";
                switch(status){
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        statusString = "unknown";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusString = "charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusString = "discharging";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusString = "not charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusString = "full";
                        break;
                }
                final Calendar calendar = Calendar.getInstance();
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int minute = calendar.get(Calendar.MINUTE);
                final int second = calendar.get(Calendar.SECOND);
                Log.v("Battery Watch", "" + hour + ":" + minute + ":" + second + " " + statusString + " " + level + "/" + scale);
            }
        }
    }

}
