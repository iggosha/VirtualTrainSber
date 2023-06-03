package org.train;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static File file = new File("Задача ВС Java Сбер.csv");
    static Scanner read;

    static {
        try {
            read = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            while (true) {
                String[] stringCsv = read.nextLine().split(";");
                List<String> lineCsv = new ArrayList<>(Arrays.asList(stringCsv));
                if (stringCsv.length == 5) {
                    lineCsv.add("");
                }
                System.out.println(new City((String) lineCsv.get(1), (String) lineCsv.get(2), (String) lineCsv.get(3),
                        Integer.parseInt(lineCsv.get(4)), (String) lineCsv.get(5)));
            }
        } catch (NoSuchElementException exception){
        }
    }
}

class City {
    String name;
    String region;
    String district;
    int population;
    String foundation;

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }
}