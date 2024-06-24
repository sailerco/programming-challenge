package de.bcxp.challenge.stats;

import de.bcxp.challenge.dataReader.DataFileReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public class WeatherStats extends Stats {

    public WeatherStats(DataFileReader data) {
        super(data);
    }

    /**
     * Determines the day with the lowest temperature spread
     * and compares the result to the current lowest-ranking day.
     *
     * @return lowestDay - the day with the lowest temperature spread
     */
    public String getSmallestTempSpread() throws IOException, ParseException {
        readContent();
        String lowestDay = null;
        int lowestSpread = Integer.MAX_VALUE;
        for (int i = 1; i < fileContent.size(); i++) {
            Map<String, String> row = fileContent.get(i);
            int currentSpread = parseValue(row.get("MxT")) - parseValue(row.get("MnT"));
            if (currentSpread < lowestSpread) {
                lowestDay = row.get("Day");
                lowestSpread = currentSpread;
            }
        }
        return lowestDay;
    }
}
