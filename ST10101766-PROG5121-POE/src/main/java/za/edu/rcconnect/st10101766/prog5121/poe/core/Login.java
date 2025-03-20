/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.edu.rcconnect.st10101766.prog5121.poe.core;

public class Login {
    private final Registration registration;

    public Login(Registration storedRegistration) {
        this.registration = storedRegistration;
    }

    public boolean loginUser(String userNameAttempt, String passwordAttempt) {
        String storedUserName = registration.getUserName();
        String storedPassword = registration.getPassword();
        return (userNameAttempt != null && userNameAttempt.equals(storedUserName)) &&
               (passwordAttempt != null && passwordAttempt.equals(storedPassword));
    }

    public String returnLoginStatus(boolean accessGranted) {
        if (accessGranted) {
            return String.format("Welcome %s,\nit is great to see you.", registration.getUserName());
        } else {
            return "Username & Password do not match our records, please try again.";
        }
    }
}
