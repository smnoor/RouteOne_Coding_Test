package com.routeone.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class StoreRegister {

    // Adds all the elements from csv file
    List<OrderComponent> unsortedPriceList = new ArrayList<OrderComponent>();

    public void loadInventory(File inventoryFile) {

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(inventoryFile)));
            int lineNumber = 0;

            while (sc.hasNextLine()) {
                lineNumber++;

                String currentLine = sc.nextLine();

                if (currentLine == null || currentLine.length() == 0) {
                    continue;
                }

                String[] stringParts = currentLine.split(",");

                OrderComponent orderItem = new OrderComponent();
                orderItem.setComponentName(stringParts[0]);
                Double price= Double.parseDouble(stringParts[1]);
                orderItem.setPrice(price);
                orderItem.setCategory(stringParts[2]);
                unsortedPriceList.add(orderItem);
            }

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in file...");
        }

    }


    public Receipt checkoutOrder(List<String> items) {
        if (!items.isEmpty()) {
            ReceiptImpl receiptImpl = new ReceiptImpl();

            // Loading inventory from csv file
            loadInventory(new File("sample-inventory.csv"));

            if (unsortedPriceList != null) {
                List<OrderComponent> sortedPriceList = new ArrayList<OrderComponent>();
                List<String> finalOrderList = new ArrayList<String>();
                double totalPrice = 0.0;


                for (String item : items) {
                    for (OrderComponent orderComponentItem : unsortedPriceList) {
                        if (orderComponentItem.getComponentName().equalsIgnoreCase(item)) {
                            sortedPriceList.add(orderComponentItem);
                        }
                    }
                }

                // Find if any invalid item in the input list

                if (sortedPriceList.size() != items.size()) {
                    List<String> validComponentItemList = new ArrayList<>();

                    for (OrderComponent orderComponent : sortedPriceList) {
                        validComponentItemList.add(orderComponent.getComponentName());
                    }

                    for (String inputItem : items) {

                        if (!validComponentItemList.contains(inputItem)) {
                            System.out.println(inputItem + " is not a valid component Item...");
                            receiptImpl = null;
                            return receiptImpl;
                        }
                    }

                }


                // Sorting the list based on the comparator
                Collections.sort(sortedPriceList, new PriceComparator());


                // Getting the final Ordered list from OrderItem list
                for (OrderComponent orderComponentItem : sortedPriceList) {
                    totalPrice = totalPrice + orderComponentItem.getPrice();
                    finalOrderList.add(orderComponentItem.getComponentName());
                }

                // Formatting the total price
                String formattedTotalPrice = PriceDecimalFormatter.formattedValue(totalPrice);

                if (finalOrderList.isEmpty()) {
                    System.out.println("No matching records found in the inventory");

                    //return null;
                }


                receiptImpl.setFormattedTotal(formattedTotalPrice);
                receiptImpl.setOrderedItems(finalOrderList);
                return receiptImpl;
            } else {
                System.out.println("There is some problem reading the file");
                return null;
            }
        } else {
            return null;
        }

        }



        // Main method to test the functionality
        public static void main (String[]args){
            StoreRegister register = new StoreRegister();
            List<String> inputItems = new ArrayList<String>();
            inputItems.add("PC1033");
            inputItems.add("GenericMotherboard");
            inputItems.add("Mouse");
            inputItems.add("LCD");
//            inputItems.add("GenericMotherboardhgjjhjh");
            System.out.println("Input : " + inputItems);
            try {
                if (!inputItems.isEmpty()) {
                    Receipt receipt = register.checkoutOrder(inputItems);
                    System.out.println("Receipt.getFormattedTotal(): "
                            + receipt.getFormattedTotal());
                    System.out.println("Receipt.getOrderedItems() : "
                            + receipt.getOrderedItems());
                } else {
                    System.out.println("Input item is empty...!!!");
                }
            } catch (NullPointerException e) {
                System.out.print("Transaction cancelled!!!");
            }
        }
    }