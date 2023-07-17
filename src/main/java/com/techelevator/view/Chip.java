package com.techelevator.view;

public class Chip extends Products{

    public Chip(String name, double price) {
        super(name, price);
    }


    public String getSound() {
        return "Crunch Crunch, Yum!";

    }
}
