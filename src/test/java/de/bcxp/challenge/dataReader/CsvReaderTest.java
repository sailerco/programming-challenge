package de.bcxp.challenge.dataReader;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvReaderTest {
    private CsvReader csvReader;

    @Test
    void readFile() throws IOException {
        String csvContent = "name,age,city\nAlice,30,New York\nBob,25,Los Angeles";
        BufferedReader reader = new BufferedReader(new StringReader(csvContent));
        csvReader = new CsvReader(reader, ",");

        ArrayList<Map<String, String>> expected = new ArrayList<>();
        expected.add(Map.of("name", "Alice", "age", "30", "city", "New York"));
        expected.add(Map.of("name", "Bob", "age", "25", "city", "Los Angeles"));

        assertEquals(expected, csvReader.readFile());
    }

    @Test
    void readEmptyFile() throws IOException {
        String csvContent = "";
        BufferedReader reader = new BufferedReader(new StringReader(csvContent));
        csvReader = new CsvReader(reader, ",");
        csvReader.readFile();
        assertTrue(csvReader.fileContent.isEmpty());
    }
}