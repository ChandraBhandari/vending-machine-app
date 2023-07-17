package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Products;
import com.techelevator.view.VendingMachine;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private Menu menu;
	List<Products> purchasedItems = new ArrayList<>();


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		VendingMachine vendingMachine = new VendingMachine();
		File file = vendingMachine.getInputFile();
		Map<String, Products> inventoryMap = vendingMachine.getInventory(file);
		System.out.println("Current money provided: $" + vendingMachine.getBalance());


		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, vendingMachine.getBalance());


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				while(true) {
					String[] productArray = new String[inventoryMap.size()];
					int itr = 0;
					Set<Map.Entry<String, Products>> entrySet = inventoryMap.entrySet();
					for (Map.Entry<String, Products> entry : entrySet) {
						String key = entry.getKey();
						Products value = entry.getValue();
						productArray[itr] = key + " " + value.toString();
						itr++;
					}
					menu.displayMenuOptionsForItems(productArray);
					break;
				}

				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					// do purchase
				while(true) {
					String choice2 = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, vendingMachine.getBalance());
					if(choice2.toLowerCase().equals("r")) {
						break;
					}
					if(choice2.equals(PURCHASE_MENU_PRODUCT)) {
						while(true) {
							try {
								System.out.println("Enter desired slot number or press 'R' to return to previous menu -->");

								Scanner in = new Scanner(System.in);
								String input = in.nextLine();

								if(input.toLowerCase().equals("r")) {
									break;
								} else if (inventoryMap.containsKey(input)) {
									if(inventoryMap.get(input).isInStock() && vendingMachine.getBalance() >= inventoryMap.get(input).getPrice()) {
										inventoryMap.get(input).purchaseItem();
										purchasedItems.add(inventoryMap.get(input));
										vendingMachine.setBalance(inventoryMap.get(input).getPrice());
										vendingMachine.log(inventoryMap.get(input).getName(), (vendingMachine.getBalance() + inventoryMap.get(input).getPrice()), vendingMachine.getBalance());
										System.out.println("Purchased!");
										break;
									} else {
										System.out.println("Balance may be low or product may be out of stock.");
										break;
									}
								} else {
									System.out.println("Product not found.");
									break;
								}
							} catch (NullPointerException npe) {
								System.err.println("error");
							}
						}
						break;
					}
					if(choice2.equals(PURCHASE_MENU_FEED_MONEY)) {
						System.out.print("Please enter dollar amount($1, $5, $10, $20): ");

						Scanner in = new Scanner(System.in);
						String input = in.nextLine();

						if(input.toLowerCase().equals("r")) {
							break;
						} else {
							if(input.equals("1") || input.equals("5") || input.equals("10") || input.equals("20")) {
								double moneyTaken = Double.parseDouble(input);
								vendingMachine.setBalance(vendingMachine.getBalance() + moneyTaken);

							} else {
								System.err.println("Invalid input");
								break;
							}

						}
						break;
					}
				}



				} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					//Exit transaction

				System.out.println(vendingMachine.getChange());
				System.out.println("Final balance: $" + vendingMachine.getBalance());

				for (Products product : purchasedItems) {
					System.out.println(product.getSound());
				}
				vendingMachine.logFile();
				}

			}
		}

		public static void main (String[]args) throws IOException {
			Menu menu = new Menu(System.in, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(menu);
			cli.run();
		}
	}

