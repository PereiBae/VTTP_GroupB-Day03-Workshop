package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class ShoppingCartDB {

    public static void loadCustomerFile(String database, String customer, ShoppingCart cart) throws IOException {

        File customerDB = new File(database, customer + ".db");

        if (!customerDB.exists()) {
            customerDB.createNewFile();
            System.out.println(customer + ", your cart is empty");
        } else {
            FileReader fr = new FileReader(customerDB);
            BufferedReader br = new BufferedReader(fr);

            if (customerDB.length() == 0) {
                System.out.println(customer + ", your cart is empty");
            } else {
                String line = "x";
                int index = 1;
                System.out.println(customer + ", your cart contains the following items:\n");

                while ((line = br.readLine()) != null) {
                    cart.getItems().add(line);
                    System.out.println(index++ + ". " + line);
                }
            }
            br.close();
            fr.close();

        }
    }

    public static void saveCustomerCart(String database, String customer, ShoppingCart cart) throws IOException {
        File customerDB = new File(database, customer + ".db");
        FileWriter fw = new FileWriter(customerDB);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String item : cart.getItems()) {
            bw.write(item + System.lineSeparator());
        }
        bw.flush();
        bw.close();
        fw.close();
        System.out.println("Your cart has been saved");
    }

}
