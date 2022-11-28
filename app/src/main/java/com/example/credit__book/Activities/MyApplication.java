package com.example.credit__book.Activities;

import android.app.Application;
import android.content.Context;

import com.example.credit__book.Model.Operation;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.Model.OperationSupplier;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    List<OperationClient> listClientOperation = new ArrayList<>();
    List<OperationSupplier> listSupplierOperation = new ArrayList<>();
    List<Operation> listOperations = new ArrayList<>();

    public List<OperationClient> getListClientOperation() {
        return listClientOperation;
    }

    public List<Operation> getListOperations() {
        return listOperations;
    }

    public List<OperationSupplier> getListSupplierOperation() {
        return  listSupplierOperation;
    }
}
