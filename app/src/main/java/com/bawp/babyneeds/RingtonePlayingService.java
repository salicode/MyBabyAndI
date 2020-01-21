package com.bawp.babyneeds;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class RingtonePlayingService extends Service {
    MediaPlayer mediaPlayer;
    int start_Id;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        String state = Objects.requireNonNull(intent.getExtras()).getString("extra");
        assert state != null;
        Log.e("Ringtone State :", state);
        if (state.equals("alarm on")) {
            start_Id = 1;
        } else if (state.equals("alarm off")) {
            start_Id = 0;
        } else {
            start_Id = 0;
        }

        if (!this.isRunning && startId == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.myalarm);
            mediaPlayer.start();
            this.isRunning = true;
            this.start_Id = 0;

        } else if (this.isRunning && startId == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            this.isRunning = false;
            this.start_Id = 0;

        } else if (!this.isRunning && startId == 0) {
            this.isRunning = false;
            this.start_Id = 0;


        } else if (this.isRunning && startId == 1) {
            this.isRunning = true;
            this.start_Id = 1;

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        super.onDestroy();
        this.isRunning=false;
        // Tell the user we stopped.
        Toast.makeText(this, "onDestroyed Called", Toast.LENGTH_SHORT).show();
    }






}