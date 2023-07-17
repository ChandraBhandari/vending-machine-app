package com.techelevator.view;

import java.text.DecimalFormat;

public abstract class Products {
    private String name;
    private double price;
    private int remainingInventory = 5;

    public Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String getSound();

    public String toString() {
        DecimalFormat formattedPrice = new DecimalFormat("0.00");
        String menuFormat = String.format(" %1$-20s %2$-10s %3$s", name, "$" + formattedPrice.format(price) , this.remainingInventory);
        return menuFormat;
    }

    public int getRemainingInventory() {
        return remainingInventory;
    }

    public boolean isInStock() {
        if(this.remainingInventory >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public void purchaseItem() {
        remainingInventory -=1;
    }
}
