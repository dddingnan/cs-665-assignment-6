/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
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
import edu.bu.met.cs665.exception.InvalidDataException;

public class UserInterface {
    private List<Beverage> beverages;
    private List<Condiment> sugar;
    private List<Condiment> milk;
    private Scanner scanner;
    private Double total;

    // Fields to store selected items
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
        this.total = 0.0;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initiates the user interface, allowing users to select beverages and add
     * condiments.
     * Continues until the user chooses to exit.
     * 
     * @throws InterruptedException If the interaction is interrupted unexpectedly.
     */
    public void start() throws InterruptedException {
        while (true) {
            System.out.println("Please select your beverage:");
            for (int i = 0; i < beverages.size(); i++) {
                System.out.println((i + 1) + ". " + beverages.get(i).getName() + " - $" + beverages.get(i).getPrice());
            }

            int beverageIndex = -1;
            while (beverageIndex < 0 || beverageIndex >= beverages.size()) {
                System.out.print("Enter a number between 1 and " + beverages.size() + ": ");
                try {
                    String input = scanner.next();
                    if (!input.matches("\\d+")) {
                        throw new InvalidDataException("Invalid data input. Please enter a valid number.",
                                "UserInterface - Beverage - Not integer", input);
                    }

                    beverageIndex = Integer.parseInt(input) - 1;

                    if (beverageIndex < 0 || beverageIndex >= beverages.size()) {
                        throw new InvalidDataException(
                                "Invalid input range. Please enter a number between 1 and " + beverages.size(),
                                "UserInterface - Not within the valid range", input);
                    }
                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine(); // Discard the invalid input
                    beverageIndex = -1; // Reset the index, forcing the loop to repeat
                    continue;
                }
            }
            selectedBeverage = beverages.get(beverageIndex);
            this.total = selectedBeverage.getPrice(); // Set the total to the beverage price

            // Handle sugar choice
            String sugarChoice = getUserChoice("Do you want to add sugar? (Y/N)");
            if (sugarChoice.equalsIgnoreCase("Y")) {
                // Display sugar options and add sugar price to the total
                handleCondimentOptions(sugar, "Sugar", selectedSugar);
            }

            // Handle milk choice
            String milkChoice = getUserChoice("Do you want to add milk? (Y/N)");
            if (milkChoice.equalsIgnoreCase("Y")) {
                // Display milk options and add milk price to the total
                handleCondimentOptions(milk, "Milk", selectedMilk);
            }

            // Display the selected items and total
            System.out.println("--------------------------------------------------------");
            System.out.println("Selected Beverage:");
            System.out.println("  - Type: " + selectedBeverage.getName() + " - Price: $" + selectedBeverage.getPrice());

            // Display selected sugar items
            System.out.println("Selected Sugar:");
            for (CondimentSelection selection : selectedSugar) {
                System.out.println("  - Type: " + selection.condiment.getName() + ", Units: " + selection.units
                        + " - Price: $" + (selection.condiment.getPrice() * selection.units));
                this.total += selection.condiment.getPrice() * selection.units;
            }

            // Display selected milk items
            System.out.println("Selected Milk:");
            for (CondimentSelection selection : selectedMilk) {
                System.out.println("  - Type: " + selection.condiment.getName() + ", Units: " + selection.units
                        + " - Price: $" + (selection.condiment.getPrice() * selection.units));
                this.total += selection.condiment.getPrice() * selection.units;
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Total Price: $" + this.total);
            System.out.println("--------------------------------------------------------");

            // Ask for another calculation
            String anotherCalculationChoice = getUserChoice("Do you want to make another calculation? (Y/N)");
            if (anotherCalculationChoice.equalsIgnoreCase("N")) {
                System.out.println("See you next time~!");
                scanner.close();
                return; // Exit the method or loop
            } else if (anotherCalculationChoice.equalsIgnoreCase("Y")) {
                selectedBeverage = null;
                selectedSugar.clear();
                selectedMilk.clear();
            }
        }
    }

    /**
     * Retrieves a valid user choice (either 'Y' or 'N') with a custom prompt
     * message.
     * 
     * @param message The prompt message to display.
     * @return A string representing the user's choice ('Y' or 'N').
     */
    private String getUserChoice(String message) {
        String choice = "";
        while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
            System.out.println(message);
            choice = scanner.next();
            if (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
        return choice;
    }

    /**
     * Handles the selection process for condiments, displaying the available
     * options
     * and prompting the user to choose and specify the desired quantity.
     * 
     * @param condiments    A list of available condiments of a specific type
     *                      (sugar/milk).
     * @param condimentType A string indicating the type of condiment being
     *                      processed (e.g., "Sugar" or "Milk").
     * @param selections    A list to store the user's condiment selections.
     */
    private void handleCondimentOptions(List<Condiment> condiments, String condimentType,
            List<CondimentSelection> selections) {
        System.out.println(condimentType + " options:");
        for (int i = 0; i < condiments.size(); i++) {
            System.out.println((i + 1) + ". " + condiments.get(i).getName() + " - $" + condiments.get(i).getPrice());
        }

        // Prompt for a valid condiment choice
        int condimentIndex = -1;
        while (condimentIndex < 1 || condimentIndex > condiments.size()) {
            System.out.print("Enter a number between 1 and " + condiments.size() + ": ");
            try {
                String input = scanner.next();
                if (!input.matches("\\d+")) {
                    throw new InvalidDataException("Invalid data input. Please enter a valid number.",
                            "UserInterface - Condiment - " + condimentType + " - Units - Not integer", input);
                }

                condimentIndex = Integer.parseInt(input);

                if (condimentIndex < 1 || condimentIndex > condiments.size()) {
                    throw new InvalidDataException(
                            "Invalid input range. Please enter a number between 1 and " + condiments.size(),
                            "UserInterface - Condiment - " + condimentType + " - Units - Not within the valid range",
                            input);
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                scanner.nextLine(); // Discard the invalid input
                condimentIndex = -1; // Reset the index, forcing the loop to repeat
            }
        }

        // Prompt for units of condiment (1-3)
        int selectedUnits = -1;
        while (selectedUnits < 1 || selectedUnits > 3) {
            System.out
                    .print("Enter the number of units of " + condiments.get(condimentIndex - 1).getName() + " (1-3): ");
            try {
                String input = scanner.next();
                if (!input.matches("\\d+")) {
                    throw new InvalidDataException("Invalid data input. Please enter a valid number.",
                            "UserInterface - Condiment - " + condimentType + " - Units - Not integer", input);
                }

                selectedUnits = Integer.parseInt(input);

                if (selectedUnits < 1 || selectedUnits > 3) {
                    System.out.println(
                            "Invalid number of units selected for " + condiments.get(condimentIndex - 1).getName()
                                    + ". Please enter a number between 1 and 3.");
                } else {
                    // Add the selection to the list
                    selections.add(new CondimentSelection(condiments.get(condimentIndex - 1), selectedUnits));
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                scanner.nextLine(); // Discard the invalid input
                selectedUnits = -1; // Reset the units, forcing the loop to repeat
            }
        }
    }

    /**
     * Represents a selection made by the user, containing the chosen condiment
     * and the specified quantity.
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
