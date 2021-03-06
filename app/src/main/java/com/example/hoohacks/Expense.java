package com.example.hoohacks;

public class Expense implements Comparable<Expense>{

    private String date;
    private String name;
    private double cost;

    public Expense(String date, String name, String cost) {
        this.date = date;
        this.name = name;
        this.cost = Double.parseDouble(cost.substring(1));
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


    @Override
    public int compareTo(Expense o) {
        return this.date.compareTo(o.date);
    }
}
