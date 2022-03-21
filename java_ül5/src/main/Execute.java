package main;

import main.services.ReadFromFile;
import main.services.WriteToFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Execute {
    private HashMap<String, ArrayList<String>> allData = new HashMap<>();
    private final ArrayList<String> firstEx = new ArrayList<>();

    public void firstExMain() {
        HashMap<String, ArrayList<String>> allData = this.allData;
        HashMap<String, ArrayList<String>> changeMap = this.allData;
        int i = 0;
        int u = 0;
        int d = 0;
        int b = 0;
        for (String key : allData.keySet()) {
            ArrayList<String> data = allData.get(key);
            i++;
            changeMap.replace(key, null);
            for (Map.Entry<String, ArrayList<String>> map2: changeMap.entrySet()) {
                u++;
                if (map2.getValue() != null) {
                    d++;
                    data.retainAll(map2.getValue());
                    if (data.size() > 0) {
                        b++;
                        String answer = key + "," + map2.getKey() + "," + data.size();
                        firstEx.add(answer);
                    }
                }
            }
        }
        System.out.println(i + " " + u + " " + d + " " +b);
        System.out.println(firstEx);
    }

    public void main() {
        ReadFromFile read = new ReadFromFile();
        WriteToFile write = new WriteToFile();
        this.allData = read.ReadData();
        this.firstExMain();
        write.writeToFile(this.firstEx);

    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Execute a = new Execute();
        a.main();
        long endTime = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
        System.out.println("Program finished in: " + seconds + " seconds.");
    }
}
