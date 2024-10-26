package io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WorkshopMain {
    
    public static final int CMD_POS =0;
    private static List<String> userList = new ArrayList<>();

    public static void main (String[] args) throws FileNotFoundException, IOException{
        boolean stop =false;
        Console cons =System.console();
        ShoppingCart shoppingCart = null;
        String database = "";
        ShoppingCartDB customerDB = new ShoppingCartDB();

        if (args.length == 1){
            if (args[0].equalsIgnoreCase("cartdb")){
                database = "/Users/brandonpereira/Code/day03-Workshop/" + args[0];
                System.out.println(database + " has been accessed");
            } else{
                System.err.println("This database does not exist. Run the program with the correct database\n");
                System.exit(1);
            }
        } else {
            database = "/Users/brandonpereira/Code/day03-Workshop/db";
            System.out.println("Default database being accessed\n");
        }
        System.out.println("Welcome to your shopping cart");

        String customer;

        while(!stop){

            String input = cons.readLine("> ").trim();

            String[] terms = input.split(" ");

            String cmd = terms[CMD_POS];

            switch(cmd.toUpperCase()){
                
                case "LOGIN":
                if (terms.length == 1){
                    System.out.println("Please enter name");
                } else{
                    customer = terms[1];
                    shoppingCart = new ShoppingCart(customer);
                    ShoppingCartDB.loadCustomerFile(database, customer, shoppingCart);
                    if(!userList.contains(customer)){
                        userList.add(customer);
                    } else {
                        System.out.println();
                        System.out.println(customer + " is already logged in");
                    }
                }
                break;

                case "SAVE":
                if (shoppingCart != null){
                    ShoppingCartDB.saveCustomerCart(database, shoppingCart.getUsername(), shoppingCart);
                } else{
                    System.out.println("You must be logged in to save your cart");
                }
                break;

                case "USERS":
                System.out.println("The following users are registered:");
                for(int i =0; i < userList.size(); i++ ){
                    System.out.println((i+1) + "." + userList.get(i));
                }
                break;

                case "LIST":
                if (shoppingCart != null){
                    shoppingCart.listItems();
                } else{
                    System.out.println("Please add items into your cart, It is empty");
                }
                break;

                case "STOP":
                stop = true;
                break;

                case "ADD":
                for (int idx =1; idx < terms.length; idx++){
                    shoppingCart.addItems(terms[idx]);
                }
                break;

                case "DELETE":
                int itemIndexRemove = Integer.parseInt(terms[1]);
                shoppingCart.removeItems(itemIndexRemove);
                break;

                default:
                System.out.println("Unknown command. Please use 'list', 'add', 'delete', 'login', 'save', 'users', or 'stop'.");
                break;
            }

        }

    }

}
