package com.example.credit__book.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.credit__book.Fragments.client_fragment;
import com.example.credit__book.Fragments.supplier_fragment;

public class PageAdapter extends FragmentPagerAdapter {

    int counttab;

    public PageAdapter(@NonNull FragmentManager fm,int counttab) {
        super(fm);
        this.counttab=counttab;
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
        return counttab;
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
