/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.edu.rcconnect.st10101766.prog5121.poe.core;

import za.edu.rcconnect.st10101766.prog5121.poe.core.Registration;
import za.edu.rcconnect.st10101766.prog5121.poe.core.Login;
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
    private static final String INVALID_USERNAME = "kyle!!!!!!!";
    private static final String INVALID_PASSWORD = "password";

    @BeforeEach
    public void setup() {
        registration = new Registration();
        // Pre-register a user so that login tests can validate against stored credentials
        registration.registerUser(VALID_USERNAME, VALID_PASSWORD, VALID_CELL_PHONE_NUMBER);
        login = new Login(registration);
    }

    @Test
    public void loginUser_CorrectCredentials_ReturnsTrue() {
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
    public void returnLoginStatus_ValidCredentials_ReturnsWelcomeMessage() {
        boolean loginResult = login.loginUser(VALID_USERNAME, VALID_PASSWORD);
        String status = login.returnLoginStatus(loginResult);
        assertTrue(status.contains("Welcome " + VALID_USERNAME));
    }

    @Test
    public void returnLoginStatus_InvalidCredentials_ReturnsFailureMessage() {
        boolean loginResult = login.loginUser(VALID_USERNAME, INVALID_PASSWORD);
        String status = login.returnLoginStatus(loginResult);
        assertEquals("Username & Password do not match our records, please try again.", status);
    }
}