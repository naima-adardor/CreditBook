package com.example.credit__book.Model;

public class OperationClient {
    private String operation_client_date;
    private double balance_client;
    private String description;
    private String name_client;

    public OperationClient() {

    }

    public OperationClient(String name_client, String operation_client_date, double balance_client, String description ) {
        this.operation_client_date = operation_client_date;
        this.balance_client = balance_client;
        this.description = description;
        this.name_client = name_client;
    }

    public String getOperation_client_date() {
        return operation_client_date;
    }

    public void setOperation_client_date(String operation_client_date) {
        this.operation_client_date = operation_client_date;
    }

    public double getBalance_client() {
        return balance_client;
    }

    public void setBalance_client(double balance_client) {
        this.balance_client = balance_client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }
}
