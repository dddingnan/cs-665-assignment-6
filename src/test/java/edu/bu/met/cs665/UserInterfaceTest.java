package edu.bu.met.cs665;

import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import org.junit.*;

import static org.junit.Assert.*;

public class UserInterfaceTest {

    @Test
    public void testInitialization() throws InvalidDataException {
        // Setup test data
        List<Beverage> mockBeverages = List.of(new Beverage("Coffee", 2.0), new Beverage("Tea", 1.5));
        List<Condiment> mockSugar = List.of(new Condiment("White Sugar", 0.1));
        List<Condiment> mockMilk = List.of(new Condiment("Whole Milk", 0.2));

        // Create an instance
        UserInterface ui = new UserInterface(mockBeverages, mockSugar, mockMilk);

        // Use reflection to get private fields if they're not accessible (consider
        // adding getter methods for easier access)
        List<Beverage> beverages = (List<Beverage>) getPrivateField(ui, "beverages");
        List<Condiment> sugar = (List<Condiment>) getPrivateField(ui, "sugar");
        List<Condiment> milk = (List<Condiment>) getPrivateField(ui, "milk");
        Double total = (Double) getPrivateField(ui, "total");
        Scanner scanner = (Scanner) getPrivateField(ui, "scanner");

        // Assert the state
        assertEquals(mockBeverages, beverages);
        assertEquals(mockSugar, sugar);
        assertEquals(mockMilk, milk);
        assertEquals(0.0, total, 0.0001);
        assertNotNull(scanner);
    }

    private Object getPrivateField(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access field using reflection", e);
        }
    }
}
