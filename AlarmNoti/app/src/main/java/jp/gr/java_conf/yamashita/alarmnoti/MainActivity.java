package jp.gr.java_conf.yamashita.alarmnoti;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private InputMethodManager mInputMethodManager;
    private RelativeLayout mLayout;
    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edText = (EditText) findViewById(R.id.editText);
        mLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        Button btnSet = (Button)findViewById(R.id.set);
        btnSet.setOnClickListener(this);;
        Button btnCancel = (Button)findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mInputMethodManager.hideSoftInputFromWindow(mLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        mLayout.requestFocus();
        return false;
    }

    @Override
    public void onClick(View v) {
        EditText edText = (EditText) findViewById(R.id.editText);
        Intent bootIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        bootIntent.putExtra("notificationId", notificationId);
        bootIntent.putExtra("todo", edText.getText());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        TimePicker tPicker = (TimePicker)findViewById(R.id.timePicker);

        switch(v.getId()){
            case R.id.set:
                int hour;
                int minute;
                int currentApiVersion = Build.VERSION.SDK_INT;
                if(currentApiVersion > Build.VERSION_CODES.LOLLIPOP_MR1){
                    hour = tPicker.getHour();
                    minute = tPicker.getMinute();
                }else{
                    hour = tPicker.getCurrentHour();
                    minute = tPicker.getCurrentMinute();
                }

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                alarm.set(
                        AlarmManager.RTC_WAKEUP,
                        alarmStartTime,
                        alarmIntent
                );
                Toast.makeText(MainActivity.this, "通知をセットしました！", Toast.LENGTH_SHORT).show();
                notificationId++;

                break;
            case R.id.cancel:
                alarm.cancel(alarmIntent);
                Toast.makeText(MainActivity.this, "通知をキャンセルしました！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
