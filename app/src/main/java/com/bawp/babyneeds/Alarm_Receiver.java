package com.bawp.babyneeds;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String get_your_string = Objects.requireNonNull(intent.getExtras()).getString("extra");
        assert get_your_string != null;
        Log.e("What is your key ?", get_your_string);
        Intent intent_service = new Intent(context,RingtonePlayingService.class);
        intent_service.putExtra("extra",get_your_string);
        context.startService(intent_service);
    }
}
