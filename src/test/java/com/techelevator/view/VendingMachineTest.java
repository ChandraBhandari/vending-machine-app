package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
    VendingMachine vendingMachine;

    @Before
    public void SetUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.setBalance(10);
    }

    @Test
    public void money_balance_is_correct_after_feeding() {

        Assert.assertEquals(10,vendingMachine.getBalance(), 0);
    }
}
