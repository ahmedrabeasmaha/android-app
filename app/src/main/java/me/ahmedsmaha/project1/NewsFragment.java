package me.ahmedsmaha.project1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import me.ahmedsmaha.project1.service.NotificationService;

public class NewsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Button btnStartService = view.findViewById(R.id.btn_start);
        Button btnStopService = view.findViewById(R.id.btn_stop);
        btnStartService.setOnClickListener((View.OnClickListener) v -> startService());
        btnStopService.setOnClickListener((View.OnClickListener) v -> stopService());
        return view;
    }
    public void startService() {
        Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(requireActivity(), serviceIntent);
    }
    public void stopService() {
        Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
        getActivity().stopService(serviceIntent);
    }
}