/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 09/21/2023
 * File Name: BaseInvalidDataException.java
 * Description: A custom exception class designed to handle invalid data scenarios.
 */

package edu.bu.met.cs665.exception;

public class BaseInvalidDataException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String dataType; // Type of data that caused the exception

    public BaseInvalidDataException(String message, String dataType) {
        super(message);
        this.dataType = dataType;
    }

    /**
     * Returns the type of data that caused the exception.
     *
     * @return The type of data that caused the exception.
     */
    public String getDataType() {
        return dataType;
    }
}
