package de.bcxp.challenge.stats;

import de.bcxp.challenge.dataReader.DataFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StatsTest {
    ArrayList<Map<String, String>> expectedFileContent;
    DataFileReader mockFileReader;

    @BeforeEach
    void setUp() throws IOException {
        expectedFileContent = new ArrayList<>();
        mockFileReader = mock(DataFileReader.class);
        when(mockFileReader.readFile()).thenReturn(expectedFileContent);
    }

    @Test
    void readContentTest() throws IOException {
        ArrayList<Map<String, String>> expectedFileContent = new ArrayList<>();
        DataFileReader mockFileReader = mock(DataFileReader.class);
        when(mockFileReader.readFile()).thenReturn(expectedFileContent);

        Stats stats = new Stats(mockFileReader);
        stats.readContent();

        assertEquals(expectedFileContent, stats.fileContent);
        // the readFile method should only be executed if the fileContent equals null (in this case it should be null)
        verify(mockFileReader, times(1)).readFile();
    }

    @Test
    void readExistingContentTest() throws IOException {
        Stats stats = new Stats(mockFileReader);
        stats.fileContent = new ArrayList<>();
        Map<String, String> content = Map.of("Name", "Austria", "Population", "8926000", "Area (km²)", "83855");
        expectedFileContent.add(content);
        stats.fileContent.add(content);
        stats.readContent();

        assertEquals(expectedFileContent, stats.fileContent);
        // the readFile method should only be executed if the fileContent equals null (in this case it shouldn't be null)
        verify(mockFileReader, never()).readFile();
    }

    @ParameterizedTest
    @ValueSource(strings = {"4,036,355.00", "4.036.355,00", "4036355,00", "4036355.00", "4,036,355", "4.036.355"})
    void parseValueTest(String numberStr) throws ParseException {
        assertEquals(4036355, Stats.parseValue(numberStr));
    }

}