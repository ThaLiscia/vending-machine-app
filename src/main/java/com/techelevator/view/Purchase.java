package com.techelevator.view;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private final Change change;
    private final TransactionLog transactionLog;
    private Menu menu;
    private Inventory inventory;
    private boolean returnToPurchaseMenu;

    private double currentMoney;
    Money money = new Money();
    public Purchase(Menu menu, Inventory inventory, TransactionLog transactionLog, Change change) {
        this.menu = menu;
        this.inventory = inventory;
          this.transactionLog = transactionLog;
          this.change = change;
        this.currentMoney = 0.0;
        this.returnToPurchaseMenu = true;
    }

    public void processPurchase() {
        while (returnToPurchaseMenu) {
            String[] purchaseMenuOptions = {
                    "Feed Money",
                    "Select Product",
                    "Finish Transaction",
                    "Return to Purchase Menu"
            };

            String purchaseChoice = (String) menu.getChoiceFromOptions(purchaseMenuOptions);

            if (purchaseChoice.equals("Feed Money")) {
                feedMoney();
            } else if (purchaseChoice.equals("Select Product")) {
                inventory.loadInventoryFromFile("vendingmachine.csv");
                userProductCode(inventory.getProducts());
            } else if (purchaseChoice.equals("Finish Transaction")) {
                finishTransaction();
            } else if (purchaseChoice.equals("Return to Purchase Menu")) {
                // Set the bool to true to return to the purchase menu
                returnToPurchaseMenu = true;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


    public void feedMoney() {
        System.out.print("Enter the amount you want to add (in whole dollars): ");
        try {
            Scanner scanner = new Scanner(System.in);
            int moneyToAdd = scanner.nextInt();
            if (moneyToAdd > 0) {
                currentMoney += moneyToAdd;
                System.out.println("Added: $" + moneyToAdd);
                System.out.println("Current Money Provided: $" + currentMoney);
                logTransaction("FEED MONEY", moneyToAdd, currentMoney);
                System.out.println("Added: $" + money.formatMoney(moneyToAdd));
                System.out.println("Current Money Provided: $" + money.formatMoney(currentMoney));
            } else {
                System.out.println("Invalid input. Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private Product getProductByCode(String productCode, List<Product> inventory) {
        for (Product product : inventory) {
            if (product.getSlot().equalsIgnoreCase(productCode)) {
                return product; // return the matching product
            }
        }
        return null; // Product not found
    }

    public void purchaseProduct(Product product) {
        if (product.getQuantity() < 1) {
            System.out.println("Sorry, the item you selected is sold out. Please pick a different item!");
        } else if (currentMoney >= product.getPrice()) {
            if (currentMoney - product.getPrice() >= 0) {
                currentMoney -= product.getPrice();
                product.setQuantity(product.getQuantity() - 1);
                logTransaction(product.getName(), product.getPrice(), currentMoney);
                displayProductMessage(product.getType());
            } else {
                System.out.println("Insufficient funds. Please select a different product or add more money.");
            }
        } else {
            System.out.println("Insufficient funds. Please add more money.");
        }
    }


    public void displayProductMessage(String productType) {
        switch (productType.toLowerCase()) {
            case "chip":
                System.out.println("Crunch Crunch, Yum!");
                break;
            case "candy":
                System.out.println("Munch Munch, Yum!");
                break;
            case "drink":
                System.out.println("Glug Glug, Yum!");
                break;
            case "gum":
                System.out.println("Chew Chew, Yum!");
                break;
            default:
                System.out.println("Enjoy your purchase!");
        }
    }

    public void userProductCode(List<Product> inventory) {
        while (returnToPurchaseMenu) { // check the bool to return to the purchase menu
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the product code or type 'R' to return to the Purchase Menu: ");
            String userProductCode = scanner.nextLine();
            System.out.println("User entered product code: " + userProductCode);

            if (userProductCode.equalsIgnoreCase("R")) {
                // Set the bool to return to the purchase menu
                returnToPurchaseMenu = true;
                break; // exit the product selection loop and return to the purchase menu
            }

            Product selectedProduct = getProductByCode(userProductCode, inventory);

            if (selectedProduct != null) {
                if (selectedProduct.getQuantity() < 1) {
                    System.out.println("Sorry, the item you selected is sold out. Please pick a different item!");
                } else if (currentMoney >= selectedProduct.getPrice()) {
                    purchaseProduct(selectedProduct);
                    currentMoney -= selectedProduct.getPrice();
                    logTransaction(selectedProduct.getName(), selectedProduct.getPrice(), currentMoney);
                } else {
                    System.out.println("Insufficient funds. Please add more money.");
                }
            } else {
                System.out.println("Product code not found in the inventory: " + userProductCode);
            }
        }
    }


    public void finishTransaction() {
        // change back cannot be negative
        if (currentMoney < 0) {
            System.out.println("Error: Negative change detected. Please contact the service team.");
            currentMoney = 0; // Reset the currentMoney
        } else {
            // user can't purchase anything if it would make their current money go below zero
            if (currentMoney > 0) {
                logTransaction("GIVE CHANGE", currentMoney, 0.0);
                System.out.println("Transaction complete. Your change is: $" + currentMoney);
                currentMoney = 0.0;
            } else {
                System.out.println("No change to return. Your current money is already at $0.");
            }
        }
    }

    private void logTransaction(String function, double amount, double balance) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", true))) {
            String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
            writer.println(timeStamp + " " + function + ": $" + String.format("%.2f", amount) + " $" + String.format("%.2f", balance));
        } catch (IOException e) {
            System.out.println("Error logging to the file: " + e.getMessage());
        }
    }
    public double getCurrentMoney() {
        return currentMoney;
    }
    public Product getProductByCodeForTest(String productCode) {
        return getProductByCode(productCode, inventory.getProducts());
    }

}

