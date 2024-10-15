package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    
     private List<String> cart;
     private String customer;

        public ShoppingCart(String customerName){
            this.customer = customerName;
            this.cart = new ArrayList<>();
        }

        public List<String> getItems(){
            return cart;
        }

        public String getUsername(){
            return customer;
        }

        public void listItems(){
            if (cart.isEmpty()){
                System.out.println("your cart is empty");
            } else{
                for (int i=0; i < cart.size(); i++){
                    System.out.println((i+1) + ". " + cart.get(i) );
                }
            }
        }

        public void addItems(String item){
            cart.add(item.trim().replaceAll("[\\p{Punct}]", ""));
            System.out.println(item.trim() + " added to cart");
        }

        public void removeItems(int index){
            if (index<1 || index >cart.size()){
                System.out.println("Incorrect item index");
                return;
            }
            String removedItem = cart.remove(index -1);
            System.out.println(removedItem + " removed from cart");
        }

        public void saveCart(String database) throws IOException{
            ShoppingCartDB.saveCustomerCart(database, customer, this);
            System.out.println("Your cart has been saved");
        }

        public void loadCart(String database) throws IOException{
            ShoppingCartDB.loadCustomerFile(database, customer, this);
            System.out.println("Your cart has been loaded");
        }

}
