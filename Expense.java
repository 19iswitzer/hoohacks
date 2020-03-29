package com.example.hoohacks;

public class Expense {

    private String date;
    private String name;
    private double cost;

    public Expense(String date, String name, String cost) {
        this.date = date;
        this.name = name;
        this.cost = Double.parseDouble(cost);
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
