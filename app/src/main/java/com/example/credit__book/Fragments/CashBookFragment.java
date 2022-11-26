package com.example.credit__book.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.credit__book.Adapter.PageAdapter;
import com.example.credit__book.R;
import com.google.android.material.tabs.TabLayout;


public class CashBookFragment extends Fragment {

//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private Button addsupplier;
//    private Button addClient;
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        tabLayout = view.findViewById(R.id.tabLayout);
//        viewPager = view.findViewById(R.id.viewPager);
//     /*   addsupplier= findViewById(R.id. btnsupplier);
//        addClient= findViewById(R.id.btnclient);
//        addsupplier.setOnClickListener(this);
//        addClient.setOnClickListener(this);*/
//
//
////        tabLayout.setupWithViewPager2(viewPager);
////
//        PageAdapter pageAdapter = new PageAdapter(getParentFragmentManager(),2);
//        viewPager.setAdapter(pageAdapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cash_book, container, false);
    }
}