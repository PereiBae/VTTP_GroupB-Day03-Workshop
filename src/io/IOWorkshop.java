package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class IOWorkshop {
    
    // Method to read the file, convert its content to upper case, and write it back to the file
    public static void processFile(String filePath) throws IOException{

        //Reading the file using FileReader and BufferedReader
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // writing the file using FileWriter and buffered Writer
        FileWriter fileWriter = new FileWriter(filePath); // Overwriting the same file
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line;

        // read the file line by line
        while ((line = bufferedReader.readLine()) != null){
            //Convert the line to uppercase
            String upperCaseLine = line.toUpperCase();
        }

    }

}
