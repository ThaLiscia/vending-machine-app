package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class Change {
    private final Map<Money, Integer> change;

    public Change() {
        change = new HashMap<>();
    }

    public void add(Money money, int quantity) {
        if (change.containsKey(money)) {
            change.put(money, change.get(money) + quantity);
        } else {
            change.put(money, quantity);
        }
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Money, Integer> entry : change.entrySet()) {
            total += entry.getKey().getValue() * entry.getValue();
        }
        return total;
    }

    public void makeChange(double currentMoney) {
        // Define the values of different coins
        int quartersValue = 25;
        int dimesValue = 10;
        int nickelsValue = 5;

        // calculate the change amount in cents
        int changeAmountInCents = (int) (currentMoney * 100);

        // calculate the number of quarters, dimes, and nickels needed
        int quarters = changeAmountInCents / quartersValue;
        changeAmountInCents %= quartersValue;
        int dimes = changeAmountInCents / dimesValue;
        changeAmountInCents %= dimesValue;
        int nickels = changeAmountInCents / nickelsValue;

        // Update the change object with the calculated change
        change.put(Money.QUARTER, quarters);
        change.put(Money.DIME, dimes);
        change.put(Money.NICKEL, nickels);

        // update the current balance to $0
        currentMoney = 0.0;
    }
}
