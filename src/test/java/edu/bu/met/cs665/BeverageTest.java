package edu.bu.met.cs665;

import org.junit.Test;
import static org.junit.Assert.*;

public class BeverageTest {

    @Test
    public void testNameAndPrice() {
        try {
            Beverage beverage = new Beverage("Coffee", 5.00);
            assertEquals("Coffee", beverage.getName());
            assertEquals(5.00, beverage.getPrice(), 0.001); // 0.001 is a delta to compare doubles
        } catch (InvalidDataException e) {
            fail("No exception should be thrown with valid data.");
        }
    }

    @Test
    public void testInvalidPrice() {
        try {
            new Beverage("Coffee", -5.00);
            fail("Expected an InvalidDataException to be thrown");
        } catch (InvalidDataException e) {
            assertEquals("Price is invalid.", e.getMessage());
            assertEquals("Price", e.getDataType());
            assertEquals(-5.00, (double) e.getDataValue(), 0.001);
        }
    }

    @Test
    public void testInvalidName() {
        try {
            new Beverage(null, 5.00);
            fail("Expected an InvalidDataException to be thrown");
        } catch (InvalidDataException e) {
            assertEquals("Name is invalid.", e.getMessage());
            assertEquals("Name", e.getDataType());
            assertNull(e.getDataValue());
        }
    }

}
