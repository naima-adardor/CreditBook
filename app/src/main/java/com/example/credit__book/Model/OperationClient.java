package com.example.credit__book.Model;

public class OperationClient {
    private String operation_client_date;
    private String balance_client;
    private String description;
    private String operationType;



    public OperationClient(String operationType, String operation_client_date, String balance_client, String description ) {
        this.operation_client_date = operation_client_date;
        this.balance_client = balance_client;
        this.description = description;
        this.operationType = operationType;

    }
    public OperationClient(String balance_client, String description ) {

        this.balance_client = balance_client;
        this.description = description;


    }
    public  OperationClient(){};

    public String getOperation_client_date() {
        return operation_client_date;
    }

    public void setOperation_client_date(String operation_client_date) {
        this.operation_client_date = operation_client_date;
    }

    public String getBalance_client() {
        return balance_client;
    }

    public void setBalance_client(String balance_client) {
        this.balance_client = balance_client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_client() {
        return operationType;
    }

    public void setName_client(String name_client) {
        this.operationType = name_client;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
