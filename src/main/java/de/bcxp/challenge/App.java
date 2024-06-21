package de.bcxp.challenge;

import de.bcxp.challenge.dataReader.CsvReader;
import de.bcxp.challenge.stats.CountryStats;
import de.bcxp.challenge.stats.WeatherStats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public final class App {
    private static final String RESOURCE_PATH = "src/main/resources/de/bcxp/challenge/";
    private static final String WEATHER_FILE = "weather.csv";
    private static final String COUNTRIES_FILE = "countries.csv";
    private static final String WEATHER_DELIMITER = ",";
    private static final String COUNTRIES_DELIMITER = ";";

    public static void main(String... args) throws ParseException, IOException {
        WeatherStats weather = initWeather();
        CountryStats countries = initCountry();

        String dayWithSmallestTempSpread = weather.getSmallestTempSpread();
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        String countryWithHighestPopulationDensity = countries.getHighestPopulationDensity();
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }

    private static WeatherStats initWeather() throws FileNotFoundException {
        BufferedReader weatherReader = new BufferedReader(new FileReader(RESOURCE_PATH + WEATHER_FILE));
        CsvReader weatherCSV = new CsvReader(weatherReader, WEATHER_DELIMITER);
        return new WeatherStats(weatherCSV);
    }

    private static CountryStats initCountry() throws FileNotFoundException {
        BufferedReader countriesReader = new BufferedReader(new FileReader(RESOURCE_PATH + COUNTRIES_FILE));
        CsvReader countriesCSV = new CsvReader(countriesReader, COUNTRIES_DELIMITER);
        return new CountryStats(countriesCSV);
    }
}
