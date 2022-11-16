package com.example.creditbook;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    List<Operation> listOperations = new ArrayList<>();

    public List<Operation> getListOperations() {
        return listOperations;
    }
}
