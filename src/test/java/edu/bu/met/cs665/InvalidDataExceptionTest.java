package edu.bu.met.cs665;

import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidDataExceptionTest {

    @Test
    public void testMessageDataTypeAndValue() {
        InvalidDataException exception = new InvalidDataException("Test Message", "Test Type", "Test Value");

        assertEquals("Test Message", exception.getMessage());
        assertEquals("Test Type", exception.getDataType());
        assertEquals("Test Value", exception.getDataValue());
    }

    @Test
    public void testToString() {
        InvalidDataException exception = new InvalidDataException("Test Message", "Test Type", 12345);

        String expectedString = "InvalidDataException: Test Message Type: Test Type Value: 12345";
        assertEquals(expectedString, exception.toString());
    }

    @Test
    public void testExceptionThrowing() {
        try {
            throw new InvalidDataException("Throwing an exception", "Type", "Value");
        } catch (InvalidDataException e) {
            assertEquals("Throwing an exception", e.getMessage());
            assertEquals("Type", e.getDataType());
            assertEquals("Value", e.getDataValue());
        }
    }
}
