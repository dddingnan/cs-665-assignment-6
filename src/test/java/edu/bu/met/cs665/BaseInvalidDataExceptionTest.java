package edu.bu.met.cs665;

import org.junit.Test;
import static org.junit.Assert.*;

public class BaseInvalidDataExceptionTest {

    @Test
    public void testMessageAndDataType() {
        BaseInvalidDataException exception = new BaseInvalidDataException("Test Message", "Test Type");

        assertEquals("Test Message", exception.getMessage());
        assertEquals("Test Type", exception.getDataType());
    }

    @Test
    public void testExceptionThrowing() {
        try {
            throw new BaseInvalidDataException("Throwing an exception", "Type");
        } catch (BaseInvalidDataException e) {
            assertEquals("Throwing an exception", e.getMessage());
            assertEquals("Type", e.getDataType());
        }
    }
}
