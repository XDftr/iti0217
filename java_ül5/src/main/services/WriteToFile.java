package main.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {

    final static String outputFilePath
            = "C:\\Users\\Deniel Konstantinov\\Documents\\iti0217\\java_ül5\\src\\main\\";

    public void writeToFile(ArrayList<String> allData, String filename) {
        // new file object
        File file = new File(outputFilePath + filename);

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
