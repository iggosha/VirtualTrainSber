package org.train;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Application {

    /**
     * CSV-файл с данными о городах
     */
    static File file = new File("Задача ВС Java Сбер.csv");

    /**
     * Список всех городов, считанных из CSV-файла
     */
    static List<City> citiesList = new ArrayList<>();

    /**
     * bufferedReader для ускорения чтения файла за счёт буферизации
     * Использует блок статической инициализации
     */
    static BufferedReader bufferedReader;

    static {
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Запускает чтение файла
     * Если конец файла - останавливает цикл
     * Если неизвестна дата основания - добавляет к массиву пустой элемент " "
     * Добавляет считанный город в список городов
     * Выводит часть списка городов, отсортированного по различным критериям
     *
     * @see CityNameLowercaseComparator
     * @see CityNameComparator
     * @see CityDistrictComparator
     */
    public static void main(String[] args) {
        while (true) {
            String[] cityStrObj = readStringFromCsvFile();
            if (cityStrObj == null) break; // if EOF
            if (cityStrObj.length == 5) {
                cityStrObj = Stream.concat(Arrays.stream(cityStrObj), Arrays.stream(new String[1])).toArray(String[]::new);
                cityStrObj[5] = "";
            }
            citiesList.add(new City(cityStrObj[1], cityStrObj[2], cityStrObj[3], Integer.parseInt(cityStrObj[4]), cityStrObj[5]));
        }
        // Вывод неотсортированного списка
        printPartOfList(citiesList, 10);

        // Сортировка с помощью лямбда-выражения и Comparator.comparing()
        citiesList.sort(Comparator.comparing((City city) -> city.getName().toLowerCase()));
        printPartOfList(citiesList, 10);

        // Перемешивание списка
        // Collections.shuffle(citiesList);

        // Сортировка с помощью класса, реализующего интерфейс Comparator, затем сортировка с помощью лямбда-выражения
        citiesList.sort(new CityDistrictComparator().thenComparing((City city1, City city2) -> city1.getName().compareTo(city2.getName())));
        printPartOfList(citiesList, 10);
    }

    /**
     * Чтение из файла с помощью bufferedReader
     *
     * @return Строка, считанная из файла, либо null, если EOF
     */
    public static String @Nullable [] readStringFromCsvFile() {
        String[] stringFromCsvFile;
        try {
            stringFromCsvFile = bufferedReader.readLine().split(";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return stringFromCsvFile;
    }

    public static void printPartOfList(List list, int amountOfElementsToPrint) {
        list.stream().limit(amountOfElementsToPrint).forEach(System.out::println);
        System.out.println();

    }
}


/**
 * Класс города
 * Содержит: имя, область, регион, числ. населения (интом), время основания (стрингом)
 */
class City {

    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public String getFoundation() {
        return foundation;
    }

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