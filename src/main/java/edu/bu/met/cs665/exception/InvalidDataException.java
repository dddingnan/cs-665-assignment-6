/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
 * File Name: InvalidDataException.java
 * Description: A specialized exception class derived from BaseInvalidDataException. 
 * It captures both the type and the specific invalid value of data causing the exception.
 * The class provides methods to retrieve the data type and invalid value.
 */

package edu.bu.met.cs665.exception;

public class InvalidDataException extends BaseInvalidDataException {
    private static final long serialVersionUID = 1L;
    private final Object dataValue; // Invalid value that caused the exception

    /**
     * Constructor for InvalidDataException
     *
     * @param message   The message describing the error.
     * @param dataType  The type of data that caused the exception.
     * @param dataValue The invalid value that caused the exception.
     */
    public InvalidDataException(String message, String dataType, Object dataValue) {
        super(message, dataType);
        this.dataValue = dataValue;
    }

    /**
     * Returns the invalid value that caused the exception.
     *
     * @return The invalid value that caused the exception.
     */
    public Object getDataValue() {
        return dataValue;
    }

    /**
     * Returns a string representation of this exception, including the message,
     * data type and invalid value.
     *
     * @return A string representation of this exception.
     */
    @Override
    public String toString() {
        return "InvalidDataException: " + super.getMessage() + " Type: " + getDataType() + " Value: " + this.dataValue;
    }
}
