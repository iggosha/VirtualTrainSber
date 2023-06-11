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
                String[] stringObj = new String[6];
                String[] stringCsv = read.nextLine().split(";");
                System.arraycopy(stringCsv, 0, stringObj, 0, stringCsv.length);
                if (stringObj[5] == null) {
                    stringObj[5] = "";
                }
                System.out.println(new City(stringObj[1], stringObj[2], stringObj[3], Integer.parseInt(stringObj[4]), stringObj[5]));
            }
        } catch (NoSuchElementException exception) {
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