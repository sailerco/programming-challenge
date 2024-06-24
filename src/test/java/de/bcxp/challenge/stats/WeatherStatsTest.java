package de.bcxp.challenge.stats;

import de.bcxp.challenge.dataReader.DataFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class WeatherStatsTest {
    DataFileReader mockFileReader;
    WeatherStats stats;

    @BeforeEach
    void setUp() {
        mockFileReader = mock(DataFileReader.class);
        stats = new WeatherStats(mockFileReader);
        stats.fileContent = new ArrayList<>();

        stats.fileContent.add(Map.of("Day", "0", "MxT", "88", "MnT", "79"));
        stats.fileContent.add(Map.of("Day", "1", "MxT", "77", "MnT", "75"));
        stats.fileContent.add(Map.of("Day", "2", "MxT", "77", "MnT", "70"));
    }

    @Test
    void getSmallestTempSpreadTest() throws IOException, ParseException {
        assertEquals("1", stats.getSmallestTempSpread());
    }
}