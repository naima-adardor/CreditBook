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

import com.example.credit__book.Activities.AddClientActivity;
import com.example.credit__book.Activities.AddSupplierActivity;
import com.example.credit__book.Adapter.PageAdapter;
import com.example.credit__book.R;
import com.google.android.material.tabs.TabLayout;


public class CreditBookFragment extends Fragment implements View.OnClickListener {

//    private TabLayout tabLayout;
//    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("onCreateView CreditBook");
        View view = inflater.inflate(R.layout.fragment_credit_book, container, false);
        System.out.println("onViewCreated CreditBook");

        final TabLayout  tabLayout = view.findViewById(R.id.tabLayout);
        final ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setSaveEnabled(false);
        final PageAdapter adapter = new PageAdapter(getParentFragmentManager (), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
//        getParentFragmentManager().popBackStack();
//        getChildFragmentManager ().beginTransaction().replace(R.id.viewPager, adapter.getItem(0)).commit();
        System.out.println("CreditBook after pageAdapter");
        viewPager.setCurrentItem(0);
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
        return view;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnsupplier:
                Intent intent1=new Intent(getContext(), AddSupplierActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnclient:
                Intent intent2=new Intent(getContext(), AddClientActivity.class);
                startActivity(intent2);
                break;

        }
    }
}