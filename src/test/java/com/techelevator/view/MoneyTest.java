package com.techelevator.view;

import com.techelevator.view.Money;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyTest {
    private Money money;

    @Before
    public void setUp() {
        money = new Money();
    }

    @Test
    public void formatMoney_shouldFormatMoneyValueCorrectly() {
        // Arrange
        double moneyValue = 3.5; // For example, $3.50

        // Act
        String formattedMoney = money.formatMoney(moneyValue);

        // Assert
        assertEquals("3.50", formattedMoney);
    }

    @Test
    public void formatMoney_shouldFormatMoneyValueWithCents() {
        // Arrange
        double moneyValue = 2.05; // For example, $2.05

        // Act
        String formattedMoney = money.formatMoney(moneyValue);

        // Assert
        assertEquals("2.05", formattedMoney);
    }

    @Test
    public void formatMoney_shouldFormatMoneyValueWithZeroCents() {
        // Arrange
        double moneyValue = 10.0; // For example, $10.00

        // Act
        String formattedMoney = money.formatMoney(moneyValue);

        // Assert
        assertEquals("10.00", formattedMoney);
    }

    @Test
    public void formatMoney_shouldFormatMoneyValueWithNoDollarSign() {
        // Arrange
        double moneyValue = 7.25; // For example, $7.25

        // Act
        String formattedMoney = money.formatMoney(moneyValue);

        // Assert
        assertEquals("7.25", formattedMoney);
    }
}
