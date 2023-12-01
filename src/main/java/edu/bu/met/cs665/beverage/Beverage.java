/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
 * File Name: Beverage.java
 * Description: Represents a beverage item with attributes for its name and price. 
 * The class provides methods to retrieve the name and price of the beverage.
 */

package edu.bu.met.cs665.beverage;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Beverage {
    private String name;
    private double price;

    /**
     * Constructs a Beverage object with the specified name and price.
     *
     * @param name  The name of the beverage.
     * @param price The price of the beverage.
     * @throws InvalidDataException If provided data for beverage creation is
     *                              invalid.
     */
    public Beverage(String name, double price) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name is invalid.", "Name", name);
        }
        if (price < 0) {
            throw new InvalidDataException("Price is invalid.", "Price", price);
        }
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the name of the beverage.
     *
     * @return The name of the beverage.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the beverage.
     *
     * @return The price of the beverage.
     */
    public double getPrice() {
        return price;
    }

}
