/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/01/2023
 * File Name: Main.java
 * Description: Entry point for the "Automatic Beverage Vending Machine System". This class initializes the 
 * system by loading beverage and condiment data from files and then initiates the user interface to enable 
 * users to interact with the vending machine. It also incorporates error handling for file-related issues.
 */

package edu.bu.met.cs665;

import java.util.List;

import edu.bu.met.cs665.beverage.Beverage;
import edu.bu.met.cs665.condiment.Condiment;
import edu.bu.met.cs665.loader.FileLoader;
import edu.bu.met.cs665.user.UserInterface;

public class Main {

  private static final String BEVERAGE_FILE_PATH = "src/main/resources/data/beverage.csv";
  private static final String SUGAR_FILE_PATH = "src/main/resources/data/sugar.csv";
  private static final String MILK_FILE_PATH = "src/main/resources/data/milk.csv";

  /**
   * Entry point method for the application.
   */
  public static void main(String[] args) {
    try {
      System.out.println("Initializing the Vending Machine...");
      startVendingMachine();
    } catch (Exception e) {
      System.err.println("Error initializing the Vending Machine: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static void startVendingMachine() throws InterruptedException {
    FileLoader loader = new FileLoader();

    List<Beverage> beverages = loadBeverages(loader);
    List<Condiment> sugars = loadCondiments(loader, SUGAR_FILE_PATH);
    List<Condiment> milks = loadCondiments(loader, MILK_FILE_PATH);

    UserInterface ui = new UserInterface(beverages, sugars, milks);
    ui.start();
  }

  private static List<Beverage> loadBeverages(FileLoader loader) {
    System.out.println("Loading beverages...");
    return loader.loadBeverageFile(BEVERAGE_FILE_PATH);
  }

  private static List<Condiment> loadCondiments(FileLoader loader, String filePath) {
    System.out.println("Loading condiments from " + filePath + "...");
    return loader.loadCondimentFile(filePath);
  }
}
