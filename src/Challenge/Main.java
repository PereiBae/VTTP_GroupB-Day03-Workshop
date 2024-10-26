package Challenge;

import java.util.*;
import java.io.*;

public class Main {

    private static final int CMD_POS = 0;

    public static void main(String[] args) throws IOException {

        Console cons = System.console();
        boolean stop = false;
        ShoppingCart shoppingCart = null;
        ShoppingCart guestCart = new ShoppingCart();
        String database = "";
        List<String> userList = new ArrayList<>();
        int userCount = 0;
        String customer = "";

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("cartdb")) {
                database = "/Users/brandonpereira/Code/day03-Workshop/" + args[0];
                System.out.println(database + " has been accessed");
            } else {
                System.err.println("This database does not exist. Run the program with the correct database\n");
                System.exit(1);
            }
        } else {
            database = "/Users/brandonpereira/Code/day03-Workshop/db";
            System.out.println("Default database being accessed\n");
        }

        System.out.println("Welcome to your Shopping Cart");

        while (!stop) {
            String input = cons.readLine(">").trim();
            String[] terms = input.split(" ");

            switch (terms[CMD_POS].toUpperCase()) {

                case "STOP":
                    stop = true;
                    break;

                case "LIST":
                    if (shoppingCart != null) {
                        shoppingCart.listItems();
                    } 
                    if (shoppingCart == null){
                        guestCart.listItems();
                    }
                    break;

                case "ADD":
                    if (shoppingCart != null) {
                        for (int i = 1; i < terms.length; i++) {
                            shoppingCart.addItems(terms[i]);
                        }
                    } else {
                        for (int i = 1; i < terms.length; i++) {
                            guestCart.addItems(terms[i]);
                        }
                    }
                    break;

                case "DELETE":
                    if (shoppingCart != null) {
                        int removedIndex = Integer.parseInt(terms[1]);
                        shoppingCart.deleteItems(removedIndex);
                    } else {
                        int removedIndex = Integer.parseInt(terms[1]);
                        guestCart.deleteItems(removedIndex);
                    }
                    break;

                case "LOGIN":
                    if (terms.length == 1) {
                        System.out.println("Please enter a valid name");
                    } else {
                        customer = terms[1];
                        shoppingCart = new ShoppingCart(customer);
                        shoppingCart.loadCart(database);
                        if (!userList.contains(customer)) {
                            userList.add(customer);
                            userCount++;
                        } else {
                            System.out.println(customer + " is already logged in");
                        }
                    }
                    break;

                case "USERS":
                    System.out.println("The following users are registered:");
                    for (int i = 0; i < userCount; i++) {
                        System.out.println((i + 1) + ". " + userList.get(i));
                    }
                    break;

                case "SAVE":
                    if (shoppingCart != null) {
                        ShoppingCartDB.saveUserCart(database, shoppingCart.getUsername(), shoppingCart);
                        ;
                    } else {
                        System.out.println("Unable to save cart without logging in");
                    }
                    break;

                default:
                    System.out.println("Unknown command. Please use ADD, STOP, DELETE or LIST only");
            }

        }

    }

}