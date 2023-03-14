package me.ahmedsmaha.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import me.ahmedsmaha.project1.utils.SharedPrefs;

public class AuthActivity extends AppCompatActivity {
    private TextInputEditText name, email;
    private SharedPrefs setData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setData = new SharedPrefs(getApplicationContext());
        Button button = findViewById(R.id.log_in);
        name = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        button.setOnClickListener(v -> {
            validate();
        });
    }

    private void validate() {
        if (Objects.requireNonNull(name.getText()).toString().equals("")) {
            name.setError("Enter your name");
        } else if (Objects.requireNonNull(email.getText()).toString().equals("")) {
            email.setError("Enter your email");
        }  else {
            setData.setSignInInfo(name.getText().toString(), email.getText().toString());
            Intent intent = new Intent(this, MainActivity.class); // explicit intent
            startActivity(intent);
        }
    }
}