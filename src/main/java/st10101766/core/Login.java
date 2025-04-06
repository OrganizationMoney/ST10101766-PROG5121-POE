package st10101766.core;

public class Login {
    private Registration registration;

    public Login(Registration registration) {
        this.registration = registration;
    }

    public boolean loginUser(String username, String password) {
        // Your existing logic
        return registration.getUserName().equals(username) && registration.getPassword().equals(password);
    }

    public String returnLoginStatus() {
        // Your existing logic
        return "Welcome back, " + registration.getUserName();
    }

    // Added for MessagePanel
    public String getUsername() {
        return registration.getUserName();
    }
}