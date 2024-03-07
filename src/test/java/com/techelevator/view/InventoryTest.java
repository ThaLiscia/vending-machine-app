package com.techelevator.view;

import com.techelevator.view.Inventory;
import com.techelevator.view.Product;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setUp() throws FileNotFoundException {
        inventory = new Inventory();
    }

    @Test
    public void addProduct_shouldAddProductToInventory() throws FileNotFoundException {
        // Arrange
        String slot = "A1";
        String name = "Chips";
        double price = 1.50;
        String type = "Chip";

        // Act
        inventory.addProduct(slot, name, price, type);

        // Assert
        assertEquals(1, inventory.getProducts().size());
        Product addedProduct = inventory.getProducts().get(0);
        assertEquals(slot, addedProduct.getSlot());
        assertEquals(name, addedProduct.getName());
        assertEquals(price, addedProduct.getPrice(), 0.001); // Using delta for double comparison
        assertEquals(type, addedProduct.getType());
    }

    @Test
    public void parseProduct_shouldParseProductFromString() {
        // Arrange
        String inputLine = "A1|Chips|1.50|Chip";

        // Act
        Product product = inventory.parseProduct(inputLine);

        // Assert
        assertNotNull(product);
        assertEquals("A1", product.getSlot());
        assertEquals("Chips", product.getName());
        assertEquals(1.50, product.getPrice(), 0.001);
        assertEquals("Chip", product.getType());
    }

    @Test
    public void loadInventoryFromFile_shouldLoadProductsFromCSV() {
        // Arrange
        String filename = "testinventory.csv"; // Create a test CSV file

        // Act
        inventory.loadInventoryFromFile(filename);

        // Assert
        assertTrue(inventory.getProducts().size() > 0);
    }
}
