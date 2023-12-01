/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
 * File Name: Main.java
 * Description: Entry point for the "Automatic Beverage Vending Machine System". This class initializes the 
 * system by loading beverage and condiment data from files and then initiates the user interface to enable 
 * users to interact with the vending machine. It also incorporates error handling for file-related issues.
 */

package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Main class.
 */
public class Main {

  /**
   * Entry point method for the application. This method initializes the system by
   * loading
   * beverage and condiment data from CSV files and then starts the user interface
   * for interaction.
   */
  public static void main(String[] args) throws InvalidDataException, InterruptedException {
    System.out.println("Hello! Welcome to the Automatic Beverage Vending Machine System!");
    System.out.println("--------------------------------------------------------");
    List<Beverage> beverages = new ArrayList<>();
    List<Condiment> sugar = new ArrayList<>();
    List<Condiment> milk = new ArrayList<>();
    FileLoader loader = new FileLoader();
    beverages = loader.loadBeverageFile("src/data/beverage.csv");
    sugar = loader.loadCondimentFile("src/data/sugar.csv");
    milk = loader.loadCondimentFile("src/data/milk.csv");
    UserInterface ui = new UserInterface(beverages, sugar, milk);
    ui.start();
  }
}
