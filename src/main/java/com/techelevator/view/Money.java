package com.techelevator.view;
import java.text.DecimalFormat;

public class Money {
    private DecimalFormat moneyFormat;



    public static final Money PENNY = new Money(0.01);
    public static final Money NICKEL = new Money(0.05);
    public static final Money DIME = new Money(0.10);
    public static final Money QUARTER = new Money(0.25);

    private double value;

    private Money(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    public Money() {
        moneyFormat = new DecimalFormat("#0.00");
    }

    public String formatMoney(double value) {
        return moneyFormat.format(value);
    }
}
