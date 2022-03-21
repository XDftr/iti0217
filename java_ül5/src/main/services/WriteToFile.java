package main.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WriteToFile {

    final static String outputFilePath
            = "C:\\Users\\Deniel Konstantinov\\Documents\\iti0217\\java_ül5\\src\\main\\output.txt";

    public void writeToFile(ArrayList<String> allData) {
        // new file object
        File file = new File(outputFilePath);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            for (String i : allData) {
                bf.write(i);
                if (!i.equals(allData.get(allData.size() - 1))){
                    bf.newLine();
                }
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
