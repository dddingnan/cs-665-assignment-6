package edu.bu.met.cs665.loader;

import org.junit.Before;
import org.junit.Test;

import edu.bu.met.cs665.beverage.Beverage;
import edu.bu.met.cs665.condiment.Condiment;
import edu.bu.met.cs665.exception.InvalidDataException;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileLoaderTest {

    private FileLoader fileLoader;

    @Before
    public void setUp() {
        fileLoader = new FileLoader();
    }

    @Test
    public void testLoadBeverageFileSuccess() throws FileNotFoundException, InvalidDataException, IOException {
        // Provide a path to a known existing file for this test
        String filePath = "src/main/resources/data/beverage.csv";

        List<Beverage> beverages = fileLoader.loadBeverageFile(filePath);

        // Assert that the list is not null and not empty
        assertNotNull(beverages);
        assertFalse(beverages.isEmpty());
    }

    @Test
    public void testLoadBeverageFileFailure() throws FileNotFoundException,
            InvalidDataException, IOException {
        // Provide a path to a non-existing file for this test
        String filePath = "src/main/resources/data/abc.csv";

        List<Beverage> beverages = fileLoader.loadBeverageFile(filePath);

        // Assert that the list is either null or empty
        assertTrue(beverages == null || beverages.isEmpty());
    }

    @Test
    public void testLoadCondimentFileSuccess() throws FileNotFoundException, InvalidDataException, IOException {
        // Provide a path to a known existing file for this test
        String filePath = "src/main/resources/data/sugar.csv";

        List<Condiment> sugar = fileLoader.loadCondimentFile(filePath);

        // Assert that the list is not null and not empty
        assertNotNull(sugar);
        assertFalse(sugar.isEmpty());
    }

    @Test
    public void testLoadCondimentFileFailure() throws FileNotFoundException,
            InvalidDataException, IOException {
        // Provide a path to a non-existing file for this test
        String filePath = "src/main/resources/data/abc.csv";

        List<Condiment> condiments = fileLoader.loadCondimentFile(filePath);

        // Assert that the list is either null or empty
        assertTrue(condiments == null || condiments.isEmpty());
    }
}
