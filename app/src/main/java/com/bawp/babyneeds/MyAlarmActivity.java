package com.bawp.babyneeds;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MyAlarmActivity extends AppCompatActivity {
    TimePicker mypicker;
    AlarmManager alarmManager;
    TextView myupdatetxt;
    PendingIntent pendingIntent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarm);
        this.context=this;
        alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
        mypicker = findViewById(R.id.timer);
        myupdatetxt = findViewById(R.id.update_text);

        final Calendar calendar = Calendar.getInstance();
        final Button alarm_on = findViewById(R.id.start_alarm);
        Button alarm_off = findViewById(R.id.end_alarm);
        final Intent myintent = new Intent(this.context,Alarm_Receiver.class);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,mypicker.getHour());
                calendar.set(Calendar.MINUTE,mypicker.getMinute());
                int hour = mypicker.getHour();
                int minute = mypicker.getMinute();
                String stringHour = String.valueOf(hour);
                String stringMinute = String.valueOf(minute);
                if (hour > 12) {
                    stringHour =String.valueOf(hour - 12);
                }

                if (minute < 10){
                    stringMinute = "0" + minute;
                }

                set_alarm_text("Alarm set to :" + stringHour +":"+ stringMinute  );
                myintent.putExtra("extra","Alarm on");

                pendingIntent = PendingIntent.getBroadcast(MyAlarmActivity.this,0,myintent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

            }


        });


        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text("Alarm Off");
                alarmManager.cancel(pendingIntent);
                myintent.putExtra("extra","Alarm off");
                sendBroadcast(myintent);
            }
        });

    }

    private void set_alarm_text(String Output) {
        myupdatetxt.setText(Output);
    }

}

