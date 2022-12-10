package com.example.credit__book.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
    private Fragment dashboard = new DashboardFragment();
    private Fragment credit = new CreditBookFragment();
    private Fragment cash = new CashBookFragment();
    private Fragment profile = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        bottom_nav = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DashboardFragment()).commit();
        bottom_nav.setSelectedItemId(R.id.dashboard_nav);


        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().popBackStack();
                switch (item.getItemId()) {
                    case R.id.dashboard_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, dashboard).commit();
                        return true;
//                    case R.id.cash_nav:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, cash).commit();
//                        return true;
                    case R.id.credit_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, credit).commit();
                        return true;
                    case R.id.profile_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profile).commit();
                        return true;
                }

                return false;
            }
        });
    }
}