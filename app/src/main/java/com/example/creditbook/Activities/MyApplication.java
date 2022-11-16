package com.example.creditbook.Activities;

import android.app.Application;

import com.example.creditbook.Model.Operation;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    List<Operation> listOperations = new ArrayList<>();

    public List<Operation> getListOperations() {
        return listOperations;
    }
}
