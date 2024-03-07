package com.techelevator.view;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<Product> products;
    public List<Product> getProducts() {
        return products;
    }


    public void Inventory() {
        products = new ArrayList<>(); // Initialize the products list
    }




}
