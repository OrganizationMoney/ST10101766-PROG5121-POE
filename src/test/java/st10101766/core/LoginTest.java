package st10101766.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private Registration registration;
    private Login login;

    // Test data
    private static final String VALID_USERNAME = "kyl_1";
    private static final String VALID_PASSWORD = "Ch&&sec@ke99!";
    private static final String VALID_CELL_PHONE_NUMBER = "+27838968976";
    private static final String VALID_FIRST_NAME = "Kyle";
    private static final String VALID_LAST_NAME = "Smith";
    private static final String INVALID_USERNAME = "kyle!!!!!!!";
    private static final String INVALID_PASSWORD = "password";

    @BeforeEach
    public void setUp() {
        registration = new Registration();
        registration.registerUser(VALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER, VALID_FIRST_NAME, VALID_LAST_NAME);
        login = new Login(registration);
    }

    @Test
    public void loginUser_CorrectCredentials_ReturnsTrue() {
        boolean loginResult = login.loginUser(VALID_USERNAME, VALID_PASSWORD);
        assertTrue(loginResult);
    }
    @Test
    public void loginUser_WrongCredentials_ReturnsFalse() {
        boolean loginResult = login.loginUser(VALID_USERNAME, VALID_PASSWORD);
        assertTrue(loginResult);
    }

    @Test
    public void loginUser_WrongPassword_ReturnsFalse() {
        boolean loginResult = login.loginUser(VALID_USERNAME, INVALID_PASSWORD);
        assertFalse(loginResult);
    }

    @Test
    public void loginUser_WrongUsername_ReturnsFalse() {
        boolean loginResult = login.loginUser(INVALID_USERNAME, VALID_PASSWORD);
        assertFalse(loginResult);
    }

    @Test
    public void returnLoginStatus_ValidCredentials_ReturnsWelcomeMessageWithNames() {
        login.loginUser(VALID_USERNAME, VALID_PASSWORD);
        String status = login.returnLoginStatus();
        assertEquals(String.format("Welcome %s %s,\nit is great to see you.", VALID_FIRST_NAME, VALID_LAST_NAME), status);
    }

    @Test
    public void returnLoginStatus_InvalidCredentials_ReturnsFailureMessage() {
        login.loginUser(VALID_USERNAME, INVALID_PASSWORD);
        String status = login.returnLoginStatus();
        assertEquals("Username & Password do not match our records, please try again.", status);
    }
}