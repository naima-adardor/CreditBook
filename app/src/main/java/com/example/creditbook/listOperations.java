package com.example.creditbook;

import java.util.Date;

public class listOperations {
    private Date operation_date;
    private double balance;
    private String type_operation;

    public listOperations(Date operation_date, double balance, String type_operation) {
        this.operation_date = operation_date;
        this.balance = balance;
        this.type_operation = type_operation;
    }

    public Date getOperation_date() {
        return operation_date;
    }

    public void setOperation_date(Date operation_date) {
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
