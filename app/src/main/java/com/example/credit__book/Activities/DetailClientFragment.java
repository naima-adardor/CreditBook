package com.example.credit__book.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.credit__book.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailClientFragment extends Fragment {



    public DetailClientFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_client_blank, container, false);
    }
}