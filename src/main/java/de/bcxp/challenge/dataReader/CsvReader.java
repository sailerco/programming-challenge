package de.bcxp.challenge.dataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;


public class CsvReader implements DataFileReader {
    private static final Logger LOGGER = Logger.getLogger(CsvReader.class.getName());
    private final String seperator;
    private final BufferedReader reader;
    ArrayList<Map<String, String>> fileContent = new ArrayList<>();
    private String[] header;

    public CsvReader(BufferedReader reader, String seperator) {
        this.seperator = seperator;
        this.reader = reader;
    }

    /**
     * Reading and iterating over the CSV file by using the Buffered Reader.
     * The first row is handled as the header which then can be used as the map keys for easy readable access of variables.
     * The CSV content is saved as an ArrayList of a Map.
     *
     * @return fileContent - data of the CSV
     */
    @Override
    public ArrayList<Map<String, String>> readFile() throws IOException {
        String line = reader.readLine();

        if (line == null) {
            LOGGER.log(Level.WARNING, "CSV is empty or header is missing");
            return fileContent;
        } else if (!line.contains(seperator)) {
            LOGGER.log(Level.WARNING, "CSV doesn't contain the given seperator");
            return fileContent;
        }

        setHeader(line);
        try {
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(seperator);
                Map<String, String> rowMap = new HashMap<>();
                IntStream.range(0, header.length).forEach(i -> rowMap.put(header[i], values[i]));
                fileContent.add(rowMap);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE, "The file doesn't match the CSV row-column format");
        }

        return fileContent;
    }

    /**
     * sets the header and logs a warning if it doesn't contain numbers
     *
     * @param headerLine the first row read by the buffered reader
     */
    private void setHeader(String headerLine) {
        if (!headerLine.matches(".*[a-zA-Z].*"))
            LOGGER.log(Level.WARNING, "CSV header " + headerLine + "doesn't contain letters");
        header = headerLine.split(seperator);
    }

}
