package edu.bu.met.cs665;

import org.junit.Test;
import static org.junit.Assert.*;

public class CondimentTest {

    @Test
    public void testNameAndPrice() {
        try {
            Beverage beverage = new Beverage("Sugar", 0.50);
            assertEquals("Sugar", beverage.getName());
            assertEquals(0.50, beverage.getPrice(), 0.001);
        } catch (InvalidDataException e) {
            fail("No exception should be thrown with valid data.");
        }
    }

    @Test
    public void testInvalidName() {
        try {
            new Condiment(null, 0.50);
            fail("Expected an InvalidDataException to be thrown");
        } catch (InvalidDataException e) {
            assertEquals("Name is invalid.", e.getMessage());
            assertEquals("Name", e.getDataType());
            assertNull(e.getDataValue());
        }
    }

    @Test
    public void testInvalidPrice() {
        try {
            new Beverage("Sugar", -1.00);
            fail("Expected an InvalidDataException to be thrown");
        } catch (InvalidDataException e) {
            assertEquals("Price is invalid.", e.getMessage());
            assertEquals("Price", e.getDataType());
            assertEquals(-1.00, (double) e.getDataValue(), 0.001);
        }
    }
}
