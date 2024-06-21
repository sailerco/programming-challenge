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

class CountryStatsTest {
    DataFileReader mockFileReader;
    CountryStats stats;

    @BeforeEach
    void setUp() {
        mockFileReader = mock(DataFileReader.class);
        stats = new CountryStats(mockFileReader);
        stats.fileContent = new ArrayList<>();

        stats.fileContent.add(Map.of("Name", "Austria", "Population", "8926000", "Area (km²)", "83855"));
        stats.fileContent.add(Map.of("Name", "Belgium", "Population", "11.566.041", "Area (km²)", "30528"));
        stats.fileContent.add(Map.of("Name", "Bulgaria", "Population", "6916548,00", "Area (km²)", "110994"));
    }

    @Test
    void getHighestPopulationDensityTest() throws ParseException, IOException {
        assertEquals("Belgium", stats.getHighestPopulationDensity());
    }
}