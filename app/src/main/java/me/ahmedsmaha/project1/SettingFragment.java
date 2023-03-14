package me.ahmedsmaha.project1;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import me.ahmedsmaha.project1.Model.RetroNews;
import me.ahmedsmaha.project1.service.GetData;
import me.ahmedsmaha.project1.utils.CustomDatePicker;
import me.ahmedsmaha.project1.utils.NewsAdapter;
import me.ahmedsmaha.project1.utils.NewsData;
import me.ahmedsmaha.project1.utils.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    NewsAdapter adapter;
    RecyclerView recyclerView;
    Button dateButton, iconButton;
    TextInputEditText editText;

    String date;
    TextView dateView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        dateButton = view.findViewById(R.id.datePicker);
        dateView = view.findViewById(R.id.dateView);
        editText = view.findViewById(R.id.etSearch);
        dateButton.setOnClickListener(v -> {
            CustomDatePicker mDatePickerDialogFragment;
            mDatePickerDialogFragment = new CustomDatePicker();
            mDatePickerDialogFragment.show(getChildFragmentManager(), "DATE PICK");
        });
        iconButton = view.findViewById(R.id.iconButton);
        iconButton.setOnClickListener(v -> {
            GetData service = RetrofitClientInstance.getRetrofitInstance().create(GetData.class);
            Call<RetroNews> call = service.getAllNews(Objects.requireNonNull(editText.getText()).toString(), this.date, "publishedAt", BuildConfig.NEWS_API_KEY);
            System.out.println(call.request());
            call.enqueue(new Callback<RetroNews>() {
                @Override
                public void onResponse(@NonNull Call<RetroNews> call, @NonNull Response<RetroNews> response) {
                    System.out.println(response.body());
                    generateDataList(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<RetroNews> call, @NonNull Throwable t) {
                    Toast.makeText(getActivity(), call.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        this.date = year + "-" + month + "-" + dayOfMonth;
        String selectedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(mCalendar.getTime());
        dateView.setText(selectedDate);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        recyclerView = requireActivity().findViewById(R.id.recyclerView);
//        adapter = new NewsAdapter(this.list, requireActivity().getApplication());
//        System.out.println(recyclerView);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }

    private void generateDataList(RetroNews newsList) {
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        adapter = new NewsAdapter(newsList, requireActivity().getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}