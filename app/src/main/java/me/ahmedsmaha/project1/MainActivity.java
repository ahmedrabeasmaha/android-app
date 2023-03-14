package me.ahmedsmaha.project1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import me.ahmedsmaha.project1.utils.Constants;
import me.ahmedsmaha.project1.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private Intent intent;
    private TextView email, name;
    private Button button;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.nav_drawer);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        setNavigationView(findViewById(R.id.nav_view));
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(this);
        setDrawerLayout();
        loadFragment(new NewsFragment());

    }

    public void setDrawerLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    public void setNavigationView(@NonNull NavigationView navigationView) {
        email = navigationView.getHeaderView(0).findViewById(R.id.tvEmail);
        name = navigationView.getHeaderView(0).findViewById(R.id.tvName);
        button = navigationView.getHeaderView(0).findViewById(R.id.sign_in);
        if (sharedPrefs.getSessionInfo().get(Constants.KEY_EMAIL) != null && sharedPrefs.getSessionInfo().get(Constants.KEY_USERNAME) != null) {
            email.setText(sharedPrefs.getSessionInfo().get(Constants.KEY_EMAIL));
            name.setText(sharedPrefs.getSessionInfo().get(Constants.KEY_USERNAME));
            button.setText(R.string.log_out);
        }
        button.setOnClickListener(v -> {
            if (button.getText().toString().equals(getResources().getString(R.string.log_in))) {
                intent = new Intent(MainActivity.this, AuthActivity.class); // explicit intent
                startActivity(intent);
            } else {
                sharedPrefs.setSignInInfo("Username", "Email@example.com");
                email.setText(sharedPrefs.getSessionInfo().get(Constants.KEY_EMAIL));
                name.setText(sharedPrefs.getSessionInfo().get(Constants.KEY_USERNAME));
                button.setText(R.string.log_in);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_webView) {
            Intent("https://www.google.com", Intent.ACTION_VIEW);

        } else if (item.getItemId() == R.id.nav_shareLocation) {
            Intent("geo:" + 0 + "," + 0 + "?q=" + 30.005493 + "," + 31.477898, Intent.ACTION_VIEW);
        } else if (item.getItemId() == R.id.nav_call) {
            Intent("tel:+201022222222", Intent.ACTION_DIAL);
        } else if (item.getItemId() == R.id.nav_share) {
            Intent send = new Intent(Intent.ACTION_SEND);
            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_TEXT, "Checkout this");
            startActivity(Intent.createChooser(send, "Share with"));
        }
        return super.onOptionsItemSelected(item);
    }


    private void Intent(String uri, String intentAction) {
        intent = new Intent(intentAction);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_profile) {
            loadFragment(new ProfileFragment());
            drawer.closeDrawer(GravityCompat.START);
        } else if (item.getItemId() == R.id.nav_users) {
            loadFragment(new UserFragment());
        } else if (item.getItemId() == R.id.nav_settings) {
            loadFragment(new SettingFragment());
            drawer.closeDrawer(GravityCompat.START);
        } else if (item.getItemId() == R.id.nav_news) {
            loadFragment(new NewsFragment());
        }
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment, fragment);
        fragmentTransaction.commit();
    }
}