/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/01/2023
 * File Name: FileLoader.java
 * Description: The FileLoader class provides utility functions to read beverage and condiment data from CSV files.
 * Each method is loading data from a provided file and returning a list of corresponding objects.
 */

package edu.bu.met.cs665.loader;

import edu.bu.met.cs665.beverage.Beverage;
import edu.bu.met.cs665.condiment.Condiment;
import edu.bu.met.cs665.exception.InvalidDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private static final String SPLIT_BY = ",";

    /**
     * Loads beverages data from a given CSV file.
     * 
     * @throws InvalidDataException
     */
    public List<Beverage> loadBeverageFile(String fileName) throws InvalidDataException {
        return loadItems(fileName, this::createBeverage);
    }

    /**
     * Loads condiments data from a given CSV file.
     * 
     * @throws InvalidDataException
     */
    public List<Condiment> loadCondimentFile(String fileName) throws InvalidDataException {
        return loadItems(fileName, this::createCondiment);
    }

    private <T> List<T> loadItems(String fileName, ItemCreator<T> creator) throws InvalidDataException {
        List<T> items = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                T item = creator.create(line);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (IOException e) {
            // Log or handle the exception
        }
        return items;
    }

    private Beverage createBeverage(String line) {
        String[] data = line.split(SPLIT_BY);
        if (data.length < 2) {
            // Log invalid data format
            return null;
        }
        try {
            String name = data[0];
            double price = Double.parseDouble(data[1]);
            return new Beverage(name, price);
        } catch (NumberFormatException | InvalidDataException e) {
            // Log invalid price or data exception
            return null;
        }
    }

    private Condiment createCondiment(String line) {
        String[] data = line.split(SPLIT_BY);
        if (data.length < 2) {
            // Log invalid data format
            return null;
        }
        try {
            String name = data[0];
            double price = Double.parseDouble(data[1]);
            return new Condiment(name, price);
        } catch (NumberFormatException | InvalidDataException e) {
            // Log invalid price or data exception
            return null;
        }
    }

    @FunctionalInterface
    private interface ItemCreator<T> {
        T create(String line) throws InvalidDataException;
    }
}
