package com.example.credit__book.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.credit__book.Fragments.CashBookFragment;
import com.example.credit__book.Fragments.CreditBookFragment;
import com.example.credit__book.Fragments.DashboardFragment;
import com.example.credit__book.Fragments.ProfileFragment;
import com.example.credit__book.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainDashboardActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        bottom_nav = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new DashboardFragment()).commit();
        bottom_nav.setSelectedItemId(R.id.dashboard_nav);


        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.dashboard_nav:
                        fragment = new DashboardFragment();
                        item.setChecked(true);
                        break;
                    case R.id.cash_nav:
                        fragment = new CashBookFragment();
                        break;
                    case R.id.credit_nav:
                        fragment = new CreditBookFragment();
                        break;
                    case R.id.profile_nav:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }
}