package me.ahmedsmaha.project1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.ahmedsmaha.project1.service.MusicService;
import me.ahmedsmaha.project1.utils.Constants;

public class UserFragment extends Fragment {
    boolean isPlaying = false;
    private Intent musicBackgroundService;
    private boolean isRunning;


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.IS_MUSIC_RUNNING, isRunning);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        if (savedInstanceState != null) {
            isRunning = savedInstanceState.getBoolean(Constants.IS_MUSIC_RUNNING);
        }
        Button btnStartService = view.findViewById(R.id.btn_start1);
        Button btnStopService = view.findViewById(R.id.btn_stop1);
        musicBackgroundService = new Intent(getActivity(), MusicService.class);
        btnStartService.setOnClickListener((View.OnClickListener) v -> startService());
        btnStopService.setOnClickListener((View.OnClickListener) v -> stopService());
        return view;
    }

    public void startService() {
        isRunning = isMyServiceRunning();
        if (!isRunning) {
            new Thread(() -> {
                requireActivity().startService(musicBackgroundService);
                isRunning = true;
            }).start();
        }
    }

    public void stopService() {
        isRunning = isMyServiceRunning();
        if (isRunning) {
            requireActivity().stopService(musicBackgroundService);
            isRunning = false;
        }
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) requireActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (MusicService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}