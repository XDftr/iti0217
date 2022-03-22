package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFromFile {
    /**
     * Reads data from a file
     * @return Hashmap with all data
     */
    public HashMap<String, ArrayList<String>> ReadData() {
        HashMap<String, ArrayList<String>> allData = new HashMap<>();

        String file = "C:\\Users\\Deniel Konstantinov\\Documents\\iti0217\\java_ül5\\src\\main\\retail_data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String personID = line.split(",")[0];
                String bookID = line.split(",")[1];

                if (personID.equals("person_id")) {
                    continue;
                }

                if (allData.containsKey(bookID)) {
                    ArrayList<String> persons = allData.get(bookID);
                    persons.add(personID);
                    allData.replace(bookID, persons);
                } else {
                    ArrayList<String> persons = new ArrayList<>();
                    persons.add(personID);
                    allData.put(bookID, persons);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allData;

    }
}


