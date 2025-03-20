/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package za.edu.rcconnect.st10101766.prog5121.poe.core;

import za.edu.rcconnect.st10101766.prog5121.poe.core.Registration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {
    private Registration registration;

    // Expected messages
    private static final String SUCCESS_REGISTRATION_MESSAGE = "You have been successfully registered";
    private static final String USERNAME_ERROR_MESSAGE = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
    private static final String PASSWORD_ERROR_MESSAGE = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    private static final String CELL_PHONE_ERROR_MESSAGE = "Cellphone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";

    // Test data
    private static final String VALID_USERNAME = "kyl_1";
    private static final String VALID_PASSWORD = "Ch&&sec@ke99!";
    private static final String VALID_CELL_PHONE_NUMBER = "+27838968976";

    // Invalid test data
    private static final String INVALID_USERNAME = "kyle!!!!!!!";
    private static final String USERNAME_WITHOUT_UNDERSCORE = "kyl1";
    private static final String INVALID_PASSWORD = "password";
    private static final String INVALID_CELL_PHONE_NUMBER_REGISTRATION = "123456789";
    private static final String INVALID_CELL_PHONE_NUMBER_SETTER = "12345";

    @BeforeEach
    public void setup() {
        registration = new Registration();
    }

    // Registration tests via registerUser method
    @Test
    public void registerUser_ValidValues_ReturnsSuccessMessage() {
        String result = registration.registerUser(VALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER);
        assertEquals(SUCCESS_REGISTRATION_MESSAGE, result);
    }

    @Test
    public void registerUser_InvalidUsername_ReturnsErrorMessage() {
        String result = registration.registerUser(INVALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER);
        assertEquals(USERNAME_ERROR_MESSAGE, result);
    }

    @Test
    public void registerUser_InvalidPassword_ReturnsErrorMessage() {
        String result = registration.registerUser(VALID_USERNAME, INVALID_PASSWORD, VALID_CELL_PHONE_NUMBER);
        assertEquals(PASSWORD_ERROR_MESSAGE, result);
    }

    @Test
    public void registerUser_InvalidCellPhoneNumber_ReturnsErrorMessage() {
        String result = registration.registerUser(VALID_USERNAME, VALID_PASSWORD, INVALID_CELL_PHONE_NUMBER_REGISTRATION);
        assertEquals(CELL_PHONE_ERROR_MESSAGE, result);
    }

    // Setter and Getter tests
    @Test
    public void setUserName_Valid_ReturnsTrueAndStoresValue() {
        boolean setResult = registration.setUserName(VALID_USERNAME);
        assertTrue(setResult);
        assertEquals(VALID_USERNAME, registration.getUserName());
    }

    @Test
    public void setUserName_Invalid_ReturnsFalse() {
        boolean setResult = registration.setUserName(USERNAME_WITHOUT_UNDERSCORE);
        assertFalse(setResult);
    }

    @Test
    public void setPassword_Valid_ReturnsTrueAndStoresValue() {
        boolean setResult = registration.setPassword(VALID_PASSWORD);
        assertTrue(setResult);
        assertEquals(VALID_PASSWORD, registration.getPassword());
    }

    @Test
    public void setPassword_Invalid_ReturnsFalse() {
        boolean setResult = registration.setPassword(INVALID_PASSWORD);
        assertFalse(setResult);
    }

    @Test
    public void setCellPhoneNumber_Valid_ReturnsTrueAndStoresValue() {
        boolean setResult = registration.setCellPhoneNumber(VALID_CELL_PHONE_NUMBER);
        assertTrue(setResult);
        assertEquals(VALID_CELL_PHONE_NUMBER, registration.getCellPhoneNumber());
    }

    @Test
    public void setCellPhoneNumber_Invalid_ReturnsFalse() {
        boolean setResult = registration.setCellPhoneNumber(INVALID_CELL_PHONE_NUMBER_SETTER);
        assertFalse(setResult);
    }
}