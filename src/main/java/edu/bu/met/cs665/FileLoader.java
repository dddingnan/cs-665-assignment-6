/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
 * File Name: FileLoader.java
 * Description: The FileLoader class provides utility functions to read beverage and condiment data from CSV files.
 * Each method is loading data from a provided file and returning a list of corresponding objects.
 */

package edu.bu.met.cs665;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public String line = "";
    public String splitBy = ",";

    /**
     * Loads beverages data from a given CSV file.
     * The CSV file format: "<beverage_name>,<price>".
     *
     * @param fileName Name of the file to be read.
     * @return A list of Beverage objects.
     * @throws FileNotFoundException If the file does not exist.
     * @throws IOException           If an error occurs while reading the file.
     * @throws InvalidDataException  If data in the file is invalid.
     */
    public List<Beverage> loadBeverageFile(String fileName) {
        List<Beverage> beverages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the line by commas
                if (data.length < 2) {
                    System.out.println("Invalid data format: " + line);
                    continue; // Skip this line and continue with the next line
                }
                String name = data[0];
                double price;
                try {
                    price = Double.parseDouble(data[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in data: " + line);
                    continue; // Skip this line and continue with the next line
                }
                try {
                    beverages.add(new Beverage(name, price));
                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    // If you want to halt processing on first invalid data:
                    // return null;
                    // Otherwise, just skip this line:
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>(); // Return an empty list if file not found
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of IO errors
        }

        return beverages;
    }

    /**
     * Loads condiments data from a given CSV file.
     * The CSV file format: "<condiment_name>,<price>".
     *
     * @param fileName Name of the file to be read.
     * @return A list of Condiment objects.
     * @throws FileNotFoundException If the file does not exist.
     * @throws IOException           If an error occurs while reading the file.
     * @throws InvalidDataException  If data in the file is invalid.
     */
    public List<Condiment> loadCondimentFile(String fileName) {
        List<Condiment> condiments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the line by commas
                if (data.length < 2) {
                    System.out.println("Invalid data format: " + line);
                    continue; // Skip this line and continue with the next line
                }
                String name = data[0];
                double price;
                try {
                    price = Double.parseDouble(data[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in data: " + line);
                    continue; // Skip this line and continue with the next line
                }
                try {
                    condiments.add(new Condiment(name, price));
                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    // Just print the error and continue processing
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>(); // Return an empty list if file not found
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of IO errors
        }

        return condiments;
    }
}
