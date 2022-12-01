package com.example.credit__book.Model;

public class OperationSupplier {
    private String operation_supplier_date;
    private String balance_supplier;
    private String description;
    private String operationType;

    public OperationSupplier() {
    }

    public OperationSupplier(String operation_supplier_date, String balance_supplier, String description, String operationType) {
        this.operation_supplier_date = operation_supplier_date;
        this.balance_supplier = balance_supplier;
        this.description = description;
        this.operationType = operationType;
    }

    public String getOperation_supplier_date() {
        return operation_supplier_date;
    }

    public void setOperation_supplier_date(String operation_supplier_date) {
        this.operation_supplier_date = operation_supplier_date;
    }

    public String getBalance_supplier() {
        return balance_supplier;
    }

    public void setBalance_supplier(String balance_supplier) {
        this.balance_supplier = balance_supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_supplier() {
        return operationType;
    }

    public void setName_supplier(String name_supplier) {
        this.operationType = name_supplier;
    }
}
