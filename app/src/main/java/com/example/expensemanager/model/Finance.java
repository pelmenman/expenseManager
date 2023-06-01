package com.example.expensemanager.model;

public class Finance {
    String name;
    String type;
    String category;
    String date;
    Double cost;

    public Finance() {}

    public Finance(String name, String type, String category, String date, Double cost) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.date = date;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public Double getCost() {
        return cost;
    }

}
