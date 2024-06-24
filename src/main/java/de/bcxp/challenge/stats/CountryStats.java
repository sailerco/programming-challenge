package de.bcxp.challenge.stats;

import de.bcxp.challenge.dataReader.DataFileReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public class CountryStats extends Stats {

    public CountryStats(DataFileReader data) {
        super(data);
    }

    /**
     * Determines the country with the highest population density by dividing the population by the area
     * and comparing the result to the current highest-ranking country.
     *
     * @return country with the highest population density
     */
    public String getHighestPopulationDensity() throws ParseException, IOException {
        readContent();
        String countryOfHighestDensity = null;
        double highestDensity = 0.0;
        for (int i = 1; i < fileContent.size(); i++) {
            Map<String, String> row = fileContent.get(i);
            int population = parseValue(row.get("Population"));
            int area = parseValue(row.get("Area (km²)"));
            double currentDensity = (double) population / area;
            if (currentDensity > highestDensity) {
                countryOfHighestDensity = row.get("Name");
                highestDensity = currentDensity;
            }
        }
        return countryOfHighestDensity;
    }
}
