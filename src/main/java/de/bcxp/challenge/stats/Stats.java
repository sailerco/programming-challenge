package de.bcxp.challenge.stats;

import de.bcxp.challenge.dataReader.DataFileReader;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Stats {
    private final DataFileReader file;
    ArrayList<Map<String, String>> fileContent = null;

    public Stats(DataFileReader file) {
        this.file = file;
    }

    /**
     * reads a file, if it hasn't been read before
     */
    void readContent() throws IOException {
        if (fileContent == null) fileContent = file.readFile();
    }

    /**
     * parses numbers into integer whilst considering the number format
     *
     * @param numberStr the string to parse into an int
     * @return the int value that was parsed
     */
    static int parseValue(String numberStr) throws ParseException {
        Locale locale = detectLocale(numberStr);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        Number populationNumber = numberFormat.parse(numberStr);
        return populationNumber.intValue();
    }

    /**
     * this method checks if the number format is in english or german convention
     *
     * @param numberStr the string whose Locale type should be detected
     * @return detected Locale type of the number string
     */
    private static Locale detectLocale(String numberStr) {
        int commaCount = numberStr.length() - numberStr.replace(",", "").length();
        int dotCount = numberStr.length() - numberStr.replace(".", "").length();

        // Check if there are both commas and dots, if comma appears after dot German locale is implied
        if (commaCount > 0 && dotCount > 0)
            return numberStr.lastIndexOf(",") > numberStr.lastIndexOf(".") ? Locale.GERMANY : Locale.US;
        else if (commaCount > 0) return commaCount > 1 ? Locale.US : Locale.GERMANY; // Multiple commas suggest US locale
        else if (dotCount > 0) return dotCount > 1 ? Locale.GERMANY : Locale.US; // Multiple dots suggest German locale
        else return Locale.US;
    }


}
