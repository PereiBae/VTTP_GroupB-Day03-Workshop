package io;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class CapitalizeMain{



    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        String inputFile = args[0];
        String outputFile = args[1];

        //Open FileReader to read file
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Open outputFile for writing
        FileWriter writer = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String line = "x";
        while (null != line){
            // Read a line
            line =bufferedReader.readLine();
            

            // If line is null, we have reached the EOF
            if (null == line){
                break;
            }

            // Write to file
            // To UpperCase and Remove Punctuation
            bufferedWriter.write(line.toUpperCase() + "\n");
            writer.flush();
        }

        bufferedWriter.close();
        writer.close();

        //Close the files
        reader.close();

    }

}