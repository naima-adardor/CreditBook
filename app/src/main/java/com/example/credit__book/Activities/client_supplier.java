package com.example.credit__book.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.credit__book.Adapter.PageAdapter;
import com.example.credit__book.R;
import com.google.android.material.tabs.TabLayout;

public class client_supplier extends AppCompatActivity implements View.OnClickListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_supp);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);



//        tabLayout.setupWithViewPager2(viewPager);
//
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        vpAdapter.addFragment(new client_fragment(),"client");
//        vpAdapter.addFragment(new supplier_fragment(),"supplier");

    }
    @Override
    public void onClick(View view) {
    }
}