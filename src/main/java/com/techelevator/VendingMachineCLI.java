package com.techelevator;

import com.techelevator.view.*;

import java.io.FileNotFoundException;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private Inventory inventory;
	private TransactionLog transactionLog;
	private Change change;

	public VendingMachineCLI(Menu menu, Inventory inventory) {
		this.menu = menu;
		this.inventory = inventory;
    }

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.displayProducts();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				handlePurchase(); // Call the handlePurchase method
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Have a nice day!");
				break;
			}
		}
	}

	private void handlePurchase() {
		Purchase purchase = new Purchase(menu, inventory, transactionLog, change);
		purchase.processPurchase();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		Inventory inventory = new Inventory();
		FileReader fileReader = new FileReader(); // Create an instance of FileReader
		TransactionLog transactionLog = new TransactionLog();
		Change change = new Change();
		inventory.loadInventoryFromFile("vendingmachine.csv");
		VendingMachineCLI cli = new VendingMachineCLI(menu, inventory);
		cli.run();
	}
}
