package Challenge;

import java.io.*;

public class ShoppingCartDB {
    
    public static void loadUserCart(String database, String customer, ShoppingCart cart ) throws IOException{
        
        File customerFile = new File(database, customer + ".db");
        if(!customerFile.exists()){
            customerFile.createNewFile();
            System.out.println(customer + " , your cart is empty");
        } else{
            FileReader fr = new FileReader(customerFile);
            BufferedReader br = new BufferedReader(fr);

            if(customerFile.length() == 0){
                System.out.println(customer + " , your cart is empty");
            } else{
                System.out.println(customer + ", your cart contains the following items:");
                String line ="x";
                int idx = 1;
                while ((line = br.readLine()) != null){
                    cart.getItems().add(line);
                    System.out.println(idx++ + ". " + line);
                }
            }
            br.close();
            fr.close();
        }

    }

    public static void saveUserCart(String database, String customer, ShoppingCart cart) throws IOException{

        File customerDB = new File(database, customer + ".db");

        if(!customerDB.exists()){
            System.out.println("Please login to save your cart");
            return;
        } else{
        FileWriter fw = new FileWriter(customerDB);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String item: cart.getItems()){
            bw.write(item + System.lineSeparator());
        }
        bw.flush();
        bw.close();
        fw.close();

        System.out.println("Your cart has been saved");
        }
    }

}
