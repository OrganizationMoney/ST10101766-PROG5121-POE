package st10101766.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {
    private Registration registration;

    // Expected messages as constants
    private static final String SUCCESS_REGISTRATION_MESSAGE = "You have been successfully registered";
    private static final String USERNAME_ERROR_MESSAGE = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
    private static final String PASSWORD_ERROR_MESSAGE = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    private static final String CELL_PHONE_ERROR_MESSAGE = "Cellphone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
    private static final String FIRST_NAME_ERROR_MESSAGE = "First name is invalid, please ensure it is not empty and contains only letters.";
    private static final String LAST_NAME_ERROR_MESSAGE = "Last name is invalid, please ensure it is not empty and contains only letters.";

    // Test data as constants
    private static final String VALID_USERNAME = "kyl_1";
    private static final String VALID_PASSWORD = "Ch&&sec@ke99!";
    private static final String VALID_CELL_PHONE_NUMBER = "+27838968976";
    private static final String VALID_FIRST_NAME = "Kyle";
    private static final String VALID_LAST_NAME = "Smith";

    // Invalid test data as constants
    private static final String INVALID_USERNAME = "kyle!!!!!!!";
    private static final String USERNAME_WITHOUT_UNDERSCORE = "kyl1";
    private static final String INVALID_PASSWORD = "password";
    private static final String INVALID_CELL_PHONE_NUMBER = "123456789";
    private static final String INVALID_FIRST_NAME = "Kyle123"; // Numbers not allowed
    private static final String INVALID_LAST_NAME = "";        // Empty not allowed

    @BeforeEach
    public void setUp() {
        registration = new Registration();
    }

    // Registration tests via registerUser method
    @Test
    public void registerUser_ValidValues_ReturnsSuccessMessage() {
        String[] result = registration.registerUser(VALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER, VALID_FIRST_NAME, VALID_LAST_NAME);
        assertEquals(1, result.length);
        assertEquals(SUCCESS_REGISTRATION_MESSAGE, result[0]);
    }

    @Test
    public void registerUser_InvalidUsername_ReturnsErrorMessage() {
        String[] result = registration.registerUser(INVALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER, VALID_FIRST_NAME, VALID_LAST_NAME);
        assertEquals(1, result.length);
        assertEquals(USERNAME_ERROR_MESSAGE, result[0]);
    }

    @Test
    public void registerUser_InvalidPassword_ReturnsErrorMessage() {
        String[] result = registration.registerUser(VALID_USERNAME, INVALID_PASSWORD, VALID_CELL_PHONE_NUMBER, VALID_FIRST_NAME, VALID_LAST_NAME);
        assertEquals(1, result.length);
        assertEquals(PASSWORD_ERROR_MESSAGE, result[0]);
    }

    @Test
    public void registerUser_InvalidCellPhoneNumber_ReturnsErrorMessage() {
        String[] result = registration.registerUser(VALID_USERNAME, VALID_PASSWORD, INVALID_CELL_PHONE_NUMBER, VALID_FIRST_NAME, VALID_LAST_NAME);
        assertEquals(1, result.length);
        assertEquals(CELL_PHONE_ERROR_MESSAGE, result[0]);
    }

    @Test
    public void registerUser_InvalidFirstName_ReturnsErrorMessage() {
        String[] result = registration.registerUser(VALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER, INVALID_FIRST_NAME, VALID_LAST_NAME);
        assertEquals(1, result.length);
        assertEquals(FIRST_NAME_ERROR_MESSAGE, result[0]);
    }

    @Test
    public void registerUser_InvalidLastName_ReturnsErrorMessage() {
        String[] result = registration.registerUser(VALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER, VALID_FIRST_NAME, INVALID_LAST_NAME);
        assertEquals(1, result.length);
        assertEquals(LAST_NAME_ERROR_MESSAGE, result[0]);
    }

    @Test
    public void registerUser_AllInvalid_ReturnsAllErrorMessages() {
        String[] result = registration.registerUser(INVALID_USERNAME, INVALID_PASSWORD, INVALID_CELL_PHONE_NUMBER, INVALID_FIRST_NAME, INVALID_LAST_NAME);
        assertEquals(5, result.length);
        assertTrue(contains(result, USERNAME_ERROR_MESSAGE));
        assertTrue(contains(result, PASSWORD_ERROR_MESSAGE));
        assertTrue(contains(result, CELL_PHONE_ERROR_MESSAGE));
        assertTrue(contains(result, FIRST_NAME_ERROR_MESSAGE));
        assertTrue(contains(result, LAST_NAME_ERROR_MESSAGE));
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
        boolean setResult = registration.setCellPhoneNumber(INVALID_CELL_PHONE_NUMBER);
        assertFalse(setResult);
    }

    @Test
    public void setFirstName_Valid_ReturnsTrueAndStoresValue() {
        boolean setResult = registration.setFirstName(VALID_FIRST_NAME);
        assertTrue(setResult);
        assertEquals(VALID_FIRST_NAME, registration.getFirstName());
    }

    @Test
    public void setFirstName_Invalid_ReturnsFalse() {
        boolean setResult = registration.setFirstName(INVALID_FIRST_NAME);
        assertFalse(setResult);
    }

    @Test
    public void setLastName_Valid_ReturnsTrueAndStoresValue() {
        boolean setResult = registration.setLastName(VALID_LAST_NAME);
        assertTrue(setResult);
        assertEquals(VALID_LAST_NAME, registration.getLastName());
    }

    @Test
    public void setLastName_Invalid_ReturnsFalse() {
        boolean setResult = registration.setLastName(INVALID_LAST_NAME);
        assertFalse(setResult);
    }

    // Helper method
    private boolean contains(String[] array, String value) {
        for (String item : array) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }
}