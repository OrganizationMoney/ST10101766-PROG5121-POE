package za.edu.rcconnect.st10101766.prog5121.poe.consoleui;

import za.edu.rcconnect.st10101766.prog5121.poe.core.Registration;
import za.edu.rcconnect.st10101766.prog5121.poe.core.Login;
import java.util.Scanner;

public class QuickChatConsole {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Registration registration = new Registration();

        // Console title omitted (optional Windows-specific code below)
        /*
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "title QuickChat v.0.9p").inheritIO().start().waitFor();
            } catch (Exception e) { }
        }
        */
        System.out.println("Welcome to QuickChat");

        String registerAttempt;
        do {
            clearConsole();
            System.out.println("Please register an account by defining your credentials");

            String userName;
            do {
                clearConsole();
                System.out.println("Enter a username with an underscore that is no more than 5 characters long then press enter >>>");
                userName = scanner.nextLine();
                if (!registration.setUserName(userName)) {
                    System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
                }
            } while (!registration.setUserName(userName));

            String password;
            do {
                clearConsole();
                System.out.println("Enter a new password then press enter but make sure your password is at least 8 characters long, has a special character, capital letter, & number >>>");
                password = scanner.nextLine();
                if (!registration.setPassword(password)) {
                    System.out.println("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
                }
            } while (!registration.setPassword(password));

            String cellphone;
            do {
                clearConsole();
                System.out.println("Enter your South African cellphone number in this format (+27123456789) with no spaces then press enter >>>");
                cellphone = scanner.nextLine();
                if (!registration.setCellPhoneNumber(cellphone)) {
                    System.out.println("Cellphone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
                }
            } while (!registration.setCellPhoneNumber(cellphone));

            registerAttempt = registration.registerUser(userName, password, cellphone);
            System.out.println(registerAttempt);
        } while (!"You have been successfully registered".equals(registerAttempt));

        Login login = new Login(registration);
        boolean loginAttempt;
        do {
            System.out.println("Enter your username >>>");
            String name = scanner.nextLine();
            System.out.println("Enter your password >>>");
            String pass = scanner.nextLine();

            boolean loginSuccessful = login.loginUser(name, pass);
            System.out.println(login.returnLoginStatus(loginSuccessful));
            loginAttempt = loginSuccessful;
        } while (!loginAttempt);
    }

    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 25; i++) {
                System.out.println();
            }
        }
    }
}