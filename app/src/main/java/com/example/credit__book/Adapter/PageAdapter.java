package com.example.credit__book.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.credit__book.Fragments.client_fragment;
import com.example.credit__book.Fragments.supplier_fragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int totalTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.totalTabs = NumOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment = new client_fragment();break;
            case 1 :
                fragment = new supplier_fragment();break;
            default: fragment = new client_fragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

//    public void addFragment(Fragment fragment, String title) {
//        fragmentArrayList.add(fragment);
//        fragmentTitle.add(title);
//    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fragmentTitle.get(position);
//    }
}
