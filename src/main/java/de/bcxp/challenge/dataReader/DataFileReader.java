package de.bcxp.challenge.dataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Interface to abstract from a specific file format reader.
 */
public interface DataFileReader {

    ArrayList<Map<String, String>> readFile() throws IOException;

}
