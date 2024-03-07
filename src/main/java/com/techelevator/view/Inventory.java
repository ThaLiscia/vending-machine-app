package com.techelevator.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inventory extends Product {
    private List<Product> products = new ArrayList<>(); // Initialize the products list
    public List<Product> getProducts() {
        return products;
    }

    public Inventory() throws FileNotFoundException {
        super("", "", 0.0, ""); // Initialize the parent class (Product) as needed
    }

    public void addProduct(String slot, String name, Double price, String type) throws FileNotFoundException {
        products.add(new Product(slot, name, price, type));
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println("Slot: " + product.getSlot());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Type: " + product.getType());
            System.out.println(); // add a blank line for separation
        }
    }
    public Product parseProduct(String line) {
        // split the line into components
        String[] parts = line.split("\\|");

        // check if the line has enough parts to create a Product
        if (parts.length >= 4) {
            String slot = parts[0].trim();
            String name = parts[1].trim();
            double price = Float.parseFloat(parts[2].trim());
            String type = parts[3].trim();

            // create a new Product object and return it
            try {
                return new Product(slot, name, price, type);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void loadInventoryFromFile(String filename){
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("vendingmachine.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Product product = parseProduct(line);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
