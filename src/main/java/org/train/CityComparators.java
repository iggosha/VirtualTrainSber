package org.train;

import java.util.Comparator;

// Нужно реализовать несколько вариантов сортировки данных справочника:
// Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра;
// Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа в алфавитном порядке по убыванию с учетом регистра;


/**
 * Сортировщик списка городов по наименованию в алфавитном порядке по убыванию без учета регистра;
 */
class CityNameLowercaseComparator implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return city1.getName().toLowerCase().compareTo(city2.getName().toLowerCase());
    }
}


/**
 * Сортировка списка городов по наименованию города в алфавитном порядке по убыванию с учетом регистра;
 */

class CityNameComparator implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return city1.getName().compareTo(city2.getName());
    }
}


/**
 * Сортировка списка городов по федеральному округу в алфавитном порядке по убыванию с учетом регистра;
 */
class CityDistrictComparator implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return city1.getDistrict().compareTo(city2.getDistrict());
    }
}
