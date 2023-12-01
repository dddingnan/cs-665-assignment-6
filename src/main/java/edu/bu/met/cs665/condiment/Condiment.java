/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
 * File Name: Condiment.java
 * Description: Represents a beverage condiment or additive with attributes for its name and price. 
 * The class provides methods to retrieve the name and price of the condiment.
 */

package edu.bu.met.cs665.condiment;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Condiment {
    private String name;
    private double price;

    /**
     * Constructs a Condiment object with the specified name and price.
     *
     * @param name  The name of the condiment.
     * @param price The price of the condiment.
     * @throws InvalidDataException If provided data for condiment creation is
     *                              invalid.
     */
    public Condiment(String name, double price) throws InvalidDataException {
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
     * Returns the name of the condiment.
     *
     * @return The name of the condiment.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the condiment.
     *
     * @return The price of the condiment.
     */
    public double getPrice() {
        return price;
    }
}
