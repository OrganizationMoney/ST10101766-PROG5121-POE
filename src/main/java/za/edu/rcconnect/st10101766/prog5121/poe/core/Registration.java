/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.edu.rcconnect.st10101766.prog5121.poe.core;

import java.util.regex.Pattern;

public class Registration {
    private String storedUserName;
    private String storedPassword;
    private String storedCellPhoneNumber;

    /**
     * Registers a user with the provided credentials and returns a message based on the success of the registration.
     *
     * @param newUserName      the username to register
     * @param newPassword      the password to register
     * @param newCellPhoneNumber the cellphone number to register
     * @return a message indicating success or the specific validation error
     */
    public String registerUser(String newUserName, String newPassword, String newCellPhoneNumber) {
        if (!setUserName(newUserName)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!setPassword(newPassword)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!setCellPhoneNumber(newCellPhoneNumber)) {
            return "Cellphone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }

        return "You have been successfully registered";
    }

    /**
     * Sets the username if it is correctly formatted and returns a boolean indicating success.
     *
     * @param userName the username to set
     * @return true if the username is valid and set, false otherwise
     */
    public boolean setUserName(String userName) {
        if (checkUserName(userName)) {
            this.storedUserName = userName;
            return true;
        } else {
            return false;
        }
    }

    public String getUserName() {
        return this.storedUserName;
    }

    /**
     * Sets the password if it meets complexity requirements and returns a boolean indicating success.
     *
     * @param password the password to set
     * @return true if the password is valid and set, false otherwise
     */
    public boolean setPassword(String password) {
        if (checkPasswordComplexity(password)) {
            this.storedPassword = password;
            return true;
        } else {
            return false;
        }
    }

    public String getPassword() {
        return this.storedPassword;
    }

    /**
     * Sets the cellphone number if it is correctly formatted and returns a boolean indicating success.
     *
     * @param cellPhoneNumber the cellphone number to set
     * @return true if the cellphone number is valid and set, false otherwise
     */
    public boolean setCellPhoneNumber(String cellPhoneNumber) {
        if (checkCellPhoneNumber(cellPhoneNumber)) {
            this.storedCellPhoneNumber = cellPhoneNumber;
            return true;
        } else {
            return false;
        }
    }

    public String getCellPhoneNumber() {
        return this.storedCellPhoneNumber;
    }

    /**
     * Checks if the username contains an underscore and is no more than 5 characters long.
     *
     * @param userName the username to check
     * @return true if the username is valid, false otherwise
     */
    private boolean checkUserName(String userName) {
        if (userName == null) {
            return false;
        }
        final int MAX_USERNAME_LENGTH = 5;
        return userName.contains("_") && userName.length() <= MAX_USERNAME_LENGTH;
    }

    /**
     * Checks if the password meets complexity requirements: 8-32 characters, with at least one capital letter,
     * one digit, and one special character.
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    private boolean checkPasswordComplexity(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        final int MIN_PASSWORD_LENGTH = 8;
        final int MAX_PASSWORD_LENGTH = 32;
        boolean passwordLengthValid = password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH;

        boolean passwordHasCapital = false;
        boolean passwordHasDigit = false;
        boolean passwordHasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                passwordHasCapital = true;
            } else if (Character.isDigit(c)) {
                passwordHasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                passwordHasSpecialChar = true;
            }
        }

        return passwordLengthValid && passwordHasCapital && passwordHasDigit && passwordHasSpecialChar;
    }

    /**
     * Checks if the cellphone number starts with "+27" followed by exactly 9 digits.
     *
     * @param cellPhoneNumber the cellphone number to check
     * @return true if the cellphone number is valid, false otherwise
     */
    private boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null || cellPhoneNumber.isEmpty()) {
            return false;
        }
        String regexChecker = "^\\+27[0-9]{9}$";
        return Pattern.matches(regexChecker, cellPhoneNumber);
    }
}
