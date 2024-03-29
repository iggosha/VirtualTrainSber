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
     * Выводит  количество городов в каждом регионе
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
        getRegionsAndAmountOfTheirAmountOfCitiesMap(citiesList).forEach((key, value) -> System.out.println(key + " - " + value));

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

    /**
     * Выводит часть списка
     *
     * @param list                    Список, который требуется вывести
     * @param amountOfElementsToPrint Количество элементов, которое нужно вывести
     */
    public static void printPartOfList(List list, int amountOfElementsToPrint) {
        list.stream().limit(amountOfElementsToPrint).forEach(System.out::println);
        System.out.println();

    }

    /**
     * @param citiesArray Массив городов
     * @return Индекс города с максимальной численностью населения
     */
    public static int getIndexOfCityWithMaxPopulation(City[] citiesArray) {
        // Необходимо преобразовать список городов в массив.
        // А затем путем перебора массива найти индекс элемента и значение с наибольшим количеством жителей города.
        int indexOfCityWithMaxPopulation = 0;
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() > citiesArray[indexOfCityWithMaxPopulation].getPopulation()) {
                indexOfCityWithMaxPopulation = i;
            }
        }
        return indexOfCityWithMaxPopulation;
    }

    /**
     * Переопределённый метод, возвращающий индекс без преобразования в массив
     *
     * @param citiesList Список городов
     * @return Индекс города с максимальной численностью населения
     */
    public static int getIndexOfCityWithMaxPopulation(List<City> citiesList) {
        int indexOfCityWithMaxPopulation = 0;
        for (int i = 0; i < citiesList.size(); i++) {
            if (citiesList.get(i).getPopulation() > citiesList.get(indexOfCityWithMaxPopulation).getPopulation()) {
                indexOfCityWithMaxPopulation = i;
            }
        }
        return indexOfCityWithMaxPopulation;
    }

    /**
     * Определяет количеством городов в каждом регионе
     * @param citiesList список городов
     * @return HashMap с названием региона : количеством городов
     */
    public static Map<String, Integer> getRegionsAndAmountOfTheirAmountOfCitiesMap(List<City> citiesList) {
        // Необходимо определить количество городов в каждом регионе.
        // Пример полученного результата:
        // Вологодская область - 15
        // Татарстан - 22 ..
        Map<String, Integer> regionsMap = new HashMap<>();

        for (City city : citiesList) {
            String regionOfCity = city.getRegion();
            if (regionsMap.containsKey(regionOfCity)) {
                regionsMap.put(regionOfCity, regionsMap.get(regionOfCity) + 1);
            } else {
                regionsMap.put(regionOfCity, 1);
            }
        }

        return regionsMap;
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