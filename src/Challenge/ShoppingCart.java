package Challenge;

import java.util.*;
import java.io.*;

public class ShoppingCart {

    private List<String> cart;
    private String customer;

    public ShoppingCart(){
        cart = new ArrayList<>();
    }

    public ShoppingCart(String customerName) {
        this.cart = new ArrayList<>();
        this.customer = customerName;
    }

    public List<String> getItems() {
        return cart;
    }

    public String getUsername() {
        return customer;
    }

    public void addItems(String item) {
        cart.add(item.trim().replaceAll(",", ""));
        System.out.println(item + " has been add to your cart");
    }

    public void listItems() {

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        } else {
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i));
            }
        }
    }

    public void deleteItems(int index) {
        if (index < 1 || index > cart.size()) {
            System.out.println("Index is out of bounds. Please try again.");
            return;
        }

        String removedItem = cart.remove(index - 1);
        System.out.println(removedItem + " has been removed from your cart");
    }

    public void saveCart(String database) throws IOException{
        ShoppingCartDB.saveUserCart(database, customer, this);
    }

    public void loadCart(String database) throws IOException{
        ShoppingCartDB.loadUserCart(database, customer, this);
    }

}