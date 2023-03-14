package me.ahmedsmaha.project1.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import me.ahmedsmaha.project1.MainActivity;
import me.ahmedsmaha.project1.R;

public class MusicService extends Service {
    private MediaPlayer musicPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = MediaPlayer.create(this, R.raw.music);
        musicPlayer.setLooping(false);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        musicPlayer.start();
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
    }
}