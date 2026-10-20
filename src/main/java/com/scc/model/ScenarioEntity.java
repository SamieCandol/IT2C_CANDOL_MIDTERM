package com.scc.model;

public class ScenarioEntity {
    private int id;
    private String customerName;
    private String drinkItem;
    private double price;
    private String status;

    public ScenarioEntity(int id, String customerName, String drinkItem, double price, String status) {
        this.id = id;
        this.customerName = customerName;
        this.drinkItem = drinkItem;
        this.price = price;
        this.status = status;
    }

    public ScenarioEntity(String customerName, String drinkItem, double price, String status) {
        this.customerName = customerName;
        this.drinkItem = drinkItem;
        this.price = price;
        this.status = status;
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getDrinkItem() { return drinkItem; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
}