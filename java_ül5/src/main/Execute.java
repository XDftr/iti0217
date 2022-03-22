package main;

import main.services.ReadFromFile;
import main.services.WriteToFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Execute {
    private HashMap<String, ArrayList<String>> allData = new HashMap<>();
    private final ArrayList<String> secondEx = new ArrayList<>();
    private final ArrayList<String> thirdEx = new ArrayList<>();

    public void firstExMain(String bookA, String bookB) {
        long startTime = System.nanoTime();
        ArrayList<String> firstBookOwners = allData.get(bookA);
        ArrayList<String> secondBookOwners = allData.get(bookB);
        int count = 0;
        if (!firstBookOwners.isEmpty()) {
            firstBookOwners.retainAll(secondBookOwners);
            count = firstBookOwners.size();
        }

        long endTime = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
        System.out.println("Raamatut " + bookA + " ja " + bookB + " on korraga ostnud " + count + " inimest.");
        System.out.println("Program A finished in: " + seconds + " seconds.");
    }

    public void secondExMain() {
        long startTime = System.nanoTime();
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
            for (Map.Entry<String, ArrayList<String>> map2 : changeMap.entrySet()) {
                u++;
                if (map2.getValue() != null) {
                    d++;
                    data.retainAll(map2.getValue());
                    if (data.size() > 0) {
                        b++;
                        String answer = key + "," + map2.getKey() + "," + data.size();
                        secondEx.add(answer);
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
        System.out.println("Number of persons: " + i + "\n" + "Number of pairs: " + u + "\n" +
                "Number of valid tries: " + d + "\n" + "Number of final answers: " + b);
        System.out.println("Program B finished in: " + seconds + " seconds.");
    }

    public void thirdExMain() {
        long startTime = System.nanoTime();
        HashMap<String, ArrayList<String>> allData = this.allData;
        HashMap<String, ArrayList<String>> changeMap = this.allData;

        int i = 0;
        int u = 0;
        int d = 0;
        int b = 0;

        for (String key : allData.keySet()) {
            i++;
            for (Map.Entry<String, ArrayList<String>> map2 : changeMap.entrySet()) {
                u++;
                if (map2.getValue() != null && !Objects.equals(map2.getKey(), key)) {
                    d++;
                    ArrayList<String> personA = allData.get(key);
                    ArrayList<String> personB = map2.getValue();

                    int pa = personA.size();
                    int pb = personB.size();

                    if (pb > 0 && pa > 0) {
                        b++;
                        ArrayList<String> personAA = allData.get(key);
                        ArrayList<String> personBB = map2.getValue();

                        personAA.retainAll(personB);
                        personBB.retainAll(personA);

                        int paa = personAA.size();
                        int pbb = personBB.size();

                        int percentA = (pbb * 100) / pa;
                        int percentB = (paa * 100) / pb;
                        String a = key + "," + map2.getKey() + "," + percentA;
                        String c = map2.getKey() + "," + key + "," + percentB;

                        if (percentA > 0) {
                            thirdEx.add(a);
                        }

                        if (percentB > 0) {
                            thirdEx.add(c);
                        }

                    }
                }
            }
        }


        long endTime = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
        System.out.println("Number of persons: " + i + "\n" + "Number of pairs: " + u + "\n" +
                "Number of valid tries: " + d + "\n" + "Number of final answers: " + b);
        System.out.println("Program C finished in: " + seconds + " seconds.");
    }

    public void main() {
        ReadFromFile read = new ReadFromFile();
        WriteToFile write = new WriteToFile();
        allData = read.ReadData();

        this.firstExMain("5806", "5807");
        allData = read.ReadData();
        System.out.println("\n");

        this.secondExMain();
        allData = read.ReadData();
        write.writeToFile(this.secondEx, "outputB.txt");
        System.out.println("\n");

        this.thirdExMain();
        write.writeToFile(this.thirdEx, "outputC.txt");
    }

    public static void main(String[] args) {
        Execute a = new Execute();
        a.main();
    }
}
