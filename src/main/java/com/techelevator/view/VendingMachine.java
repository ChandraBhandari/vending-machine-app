package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class VendingMachine {
    private double balance = 0;
    List<String> list = new ArrayList<>();



    public File getInputFile() {
        String path = "vendingmachine.csv";
        File inputFile = new File(path);
        return inputFile;
    }



    public Map<String, Products> getInventory(File inputFile) throws FileNotFoundException {
        Map<String, Products> inventoryMap = new LinkedHashMap<>();

        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] value_split = line.split(Pattern.quote("|"));
                Products product = null;
                if (value_split[3].equals("Chip")) {
                    product = new Chip(value_split[1], Double.parseDouble(value_split[2]));

                } else if (value_split[3].equals("Candy")) {
                    product = new Candy(value_split[1], Double.parseDouble(value_split[2]));

                } else if (value_split[3].equals("Drink")) {
                    product = new Drink(value_split[1], Double.parseDouble(value_split[2]));

                } else if (value_split[3].equals("Gum")) {
                    product = new Gum(value_split[1], Double.parseDouble(value_split[2]));

                }
                inventoryMap.put(value_split[0], product);

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found.");
        }
        return inventoryMap;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getChange() {
        int cents = (int)(getBalance()*100);

        int quarters = cents/25;
        cents=cents%25;
        int dimes = cents/10;
        cents=cents%10;
        int nickels = cents/5;
        cents=cents%5;

        String change = "quarters:" + quarters  + " dimes:" + dimes + " nickles:" + nickels;
        balance = 0;
        return change;

    }

    public List<String> getList() {
        return this.list;
    }

    public List<String> log(String name, double startingAmount, double endingAmount) {

        LocalDateTime time = LocalDateTime.now();
        String str = time + " " + name + " " + startingAmount + " " + endingAmount;
        list.add(str);
        return list;
    }

    public void logFile() throws IOException {
        File recordingFile = new File("log.txt");
        List<String> recordList = getList();
        try(FileWriter logWriter = new FileWriter(recordingFile, true)) {
            for(String str : recordList) {
                logWriter.write(str);
                logWriter.write("\n");
            }
        }
    }



}