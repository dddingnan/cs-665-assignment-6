/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/01/2023
 * File Name: UserInterface.java
 * Description: Provides a console-based interface allowing users to choose and customize beverages
 * by adding condiments like sugar or milk. This class interacts with the user, displays available options,
 * calculates the total price based on the chosen items, and deals with invalid data entries.
 */

package edu.bu.met.cs665.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import edu.bu.met.cs665.beverage.Beverage;
import edu.bu.met.cs665.condiment.Condiment;

public class UserInterface {
    private List<Beverage> beverages;
    private List<Condiment> sugar;
    private List<Condiment> milk;
    private Scanner scanner;
    private double total;

    private Beverage selectedBeverage;
    private List<CondimentSelection> selectedSugar = new ArrayList<>();
    private List<CondimentSelection> selectedMilk = new ArrayList<>();

    /**
     * Initializes the UserInterface with the given beverage and condiment options.
     *
     * @param beverages A list of available beverages for the user to choose from.
     * @param sugar     A list of sugar condiment options.
     * @param milk      A list of milk condiment options.
     */
    public UserInterface(List<Beverage> beverages, List<Condiment> sugar, List<Condiment> milk) {
        this.beverages = beverages;
        this.sugar = sugar;
        this.milk = milk;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interaction process for selecting beverages and condiments.
     * Continues until the user chooses not to make another order.
     */
    public void start() {
        try {
            do {
                displayBeverageOptions();
                selectBeverage();
                if (askForCondiments("sugar")) {
                    selectCondiments(sugar, selectedSugar);
                }
                if (askForCondiments("milk")) {
                    selectCondiments(milk, selectedMilk);
                }
                displayOrderSummary();
                resetOrder();
            } while (askToContinue());
        } finally {
            scanner.close();
        }
    }

    /**
     * Displays the available beverage options to the user.
     */
    private void displayBeverageOptions() {
        System.out.println("Please select your beverage:");
        for (int i = 0; i < beverages.size(); i++) {
            Beverage beverage = beverages.get(i);
            System.out.println((i + 1) + ". " + beverage.getName() + " - $" + beverage.getPrice());
        }
    }

    /**
     * Handles the user's beverage selection.
     * Validates the input and sets the selected beverage and initial total price.
     */
    private void selectBeverage() {
        int choice = getValidatedInput("Enter a number for the beverage: ", beverages.size());
        selectedBeverage = beverages.get(choice - 1);
        total = selectedBeverage.getPrice();
    }

    /**
     * Asks the user if they would like to add a type of condiment.
     * 
     * @param type The type of condiment to ask about (e.g., "sugar" or "milk").
     * @return True if the user wants to add the condiment, false otherwise.
     */
    private boolean askForCondiments(String type) {
        String response = getYesOrNoInput("Do you want to add " + type + "? (Y/N): ");
        return response.equalsIgnoreCase("Y");
    }

    /**
     * Handles the process of selecting condiments and their quantities.
     * 
     * @param condiments The list of available condiments of a specific type.
     * @param selections The list to store the user's condiment selections.
     */
    private void selectCondiments(List<Condiment> condiments, List<CondimentSelection> selections) {
        displayCondimentOptions(condiments);
        int choiceIndex = getValidatedInput("Enter a number for the condiment: ", condiments.size()) - 1;
        Condiment selectedCondiment = condiments.get(choiceIndex);
        int units = getValidatedInput("Enter the number of units for " + selectedCondiment.getName() + " (1-3): ", 3);
        selections.add(new CondimentSelection(selectedCondiment, units));
    }

    /**
     * Displays the available options for a specific type of condiment.
     * 
     * @param condiments The list of condiments to display.
     */
    private void displayCondimentOptions(List<Condiment> condiments) {
        System.out.println("Available options:");
        for (int i = 0; i < condiments.size(); i++) {
            Condiment condiment = condiments.get(i);
            System.out.println((i + 1) + ". " + condiment.getName() + " - $" + condiment.getPrice());
        }
    }

    /**
     * Prompts the user for input and validates it against the expected range.
     * 
     * @param prompt The prompt to display to the user.
     * @param max    The maximum valid input value.
     * @return The validated input from the user.
     */
    private int getValidatedInput(String prompt, int max) {
        int input = 0;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number. Please enter a number.");
                scanner.next(); // to discard the non-int input
            }
            input = scanner.nextInt();
            if (input < 1 || input > max) {
                System.out.println("Please enter a number between 1 and " + max + ".");
            }
        } while (input < 1 || input > max);
        return input;
    }

    /**
     * Displays a summary of the selected order, including beverage and condiments,
     * and the total price.
     */
    private void displayOrderSummary() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Selected Beverage: " + selectedBeverage.getName() + " - $" + selectedBeverage.getPrice());
        displayCondimentSummary("Sugar", selectedSugar);
        displayCondimentSummary("Milk", selectedMilk);
        System.out.println("Total Price: $" + total);
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Displays a summary of the selected condiments and their quantities.
     * 
     * @param type       The type of condiment (e.g., "Sugar").
     * @param selections The list of condiment selections made by the user.
     */
    private void displayCondimentSummary(String type, List<CondimentSelection> selections) {
        if (!selections.isEmpty()) {
            System.out.println("Selected " + type + ":");
            for (CondimentSelection selection : selections) {
                Condiment condiment = selection.condiment;
                System.out.println("  - " + condiment.getName() + ", Units: " + selection.units
                        + " - Price: $" + (condiment.getPrice() * selection.units));
                total += condiment.getPrice() * selection.units;
            }
        }
    }

    /**
     * Resets the order details to prepare for a new order.
     */
    private void resetOrder() {
        selectedBeverage = null;
        selectedSugar.clear();
        selectedMilk.clear();
        total = 0;
    }

    /**
     * Asks the user if they want to make another order.
     * 
     * @return True if the user wants to continue, false if they want to exit.
     */
    private boolean askToContinue() {
        String response = getYesOrNoInput("Do you want to make another order? (Y/N): ");
        return response.equalsIgnoreCase("Y");
    }

    /**
     * Prompts the user with the given message and validates the input to be 'Y' or
     * 'N'.
     * 
     * @param message The message to prompt the user with.
     * @return The validated input, guaranteed to be 'Y' or 'N'.
     */
    private String getYesOrNoInput(String message) {
        String input;
        do {
            System.out.print(message);
            input = scanner.next();
            if (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
        return input;
    }

    /**
     * Represents a selection of a condiment and its quantity made by the user.
     */
    private static class CondimentSelection {
        private Condiment condiment;
        private int units;

        public CondimentSelection(Condiment condiment, int units) {
            this.condiment = condiment;
            this.units = units;
        }
    }
}
