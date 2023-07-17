package com.techelevator.view;

public class Candy extends Products{

    public Candy(String name, double price) {
        super(name, price);
    }


    public String getSound() {
        return "Munch Munch, Yum!";
    }
}
