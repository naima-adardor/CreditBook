package com.example.creditbook;

import java.util.Date;

public class Operation {
    private String operation_date;
    private double balance;
    private String type_operation;

    public Operation(String operation_date, double balance, String type_operation) {
        this.operation_date = operation_date;
        this.balance = balance;
        this.type_operation = type_operation;
    }

    public String getOperation_date() {
        return operation_date;
    }

    public void setOperation_date(String operation_date) {
        this.operation_date = operation_date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType_operation() {
        return type_operation;
    }

    public void setType_operation(String type_operation) {
        this.type_operation = type_operation;
    }
}
