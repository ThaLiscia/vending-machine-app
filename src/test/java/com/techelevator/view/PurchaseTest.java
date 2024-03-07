package com.techelevator.view;

import com.techelevator.view.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PurchaseTest {
    private Purchase purchase;

    @Before
    public void setup() throws FileNotFoundException {

        Menu menu = new Menu(System.in, System.out);
        Inventory inventory = new Inventory();
        TransactionLog transactionLog = new TransactionLog();
        Change change = new Change();
        purchase = new Purchase(menu, inventory, transactionLog, change);
    }

    @Test
    public void testFeedMoney() {
        // Arrange
        double initialMoney = purchase.getCurrentMoney(); // Get the initial currentMoney value
        double addedMoney = 5.0; // The amount to add
        String input = "5\n"; // Simulate user input of $5

        // Redirect System.in to provide the input (thank you google)
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        purchase.feedMoney();

        // Restore System.in
        System.setIn(originalSystemIn);

        // Assert
        double updatedMoney = purchase.getCurrentMoney(); // Get the updated currentMoney value
        Assert.assertEquals(initialMoney + addedMoney, updatedMoney, 0.01); // Check if money was added correctly
    }
}
