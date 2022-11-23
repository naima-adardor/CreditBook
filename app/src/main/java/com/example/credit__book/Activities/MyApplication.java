package com.example.credit__book.Activities;

import android.app.Application;
import android.content.Context;

import com.example.credit__book.Model.Operation;
import com.example.credit__book.Model.OperationClient;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    List<OperationClient> listClientOperation = new ArrayList<>();

    public List<OperationClient> getListClientOperation() {
        return listClientOperation;
    }

    List<Operation> listOperations = new ArrayList<>();

    public List<Operation> getListOperations() {
        return listOperations;
    }
}
