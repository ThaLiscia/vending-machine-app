package com.techelevator.view;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionLog {
    private static final String LOG_FILE = "Log.txt";

    private void logTransaction(String function, double amount, double balance) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", true))) {
            String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
            writer.println(timeStamp + " " + function + ": $" + String.format("%.2f", amount) + " $" + String.format("%.2f", balance));
        } catch (IOException e) {
            System.out.println("Error logging to the file: " + e.getMessage());
        }
    }
}
