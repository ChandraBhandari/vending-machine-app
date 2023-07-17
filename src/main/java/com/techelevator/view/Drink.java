package com.techelevator.view;

public class Drink extends Products{

    public Drink(String name, double price) {
        super(name, price);
    }


    public String getSound() {
        return "Glug Glug, Yum!";

    }
}
