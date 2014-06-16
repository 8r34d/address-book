package spencer.dean.addressbook;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Data {

    public static Iterator<String[]> getDataAsIterator(String csvFile) {
        return getDataAsIterator(csvFile, CSVReader.DEFAULT_SKIP_LINES);
    }

    public static Iterator<String[]> getDataAsIterator(String csvFile, int skipLine) {        
        return (Iterator<String[]>) getDataAsList(csvFile, skipLine).iterator();
    }

    public static List<String[]> getDataAsList(String csvFile) {
        return getDataAsList(csvFile, CSVReader.DEFAULT_SKIP_LINES);
    }

    public static List<String[]> getDataAsList(String csvFile, int skipLine) {
        CSVReader reader = null;
        List<String[]> data = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ',', '\'', skipLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            data = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}