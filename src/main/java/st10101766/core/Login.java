package st10101766.core;

public class Login {
    private final Registration registration;
    private boolean accessGranted;
    
    public Login(Registration registration) {
        this.registration = registration;
    }
    
    public boolean loginUser(String userNameAttempt, String passwordAttempt) {
        String storedUserName = registration.getUserName();
        String storedPassword = registration.getPassword();
        
        accessGranted = (userNameAttempt != null && userNameAttempt.equals(storedUserName)) &&
                        (passwordAttempt != null && passwordAttempt.equals(storedPassword));
        
        return accessGranted;
    }

    public String returnLoginStatus() {
        if (accessGranted) {
            return String.format("Welcome %s %s,\nit is great to see you.", 
                registration.getFirstName(), registration.getLastName());
        } else {
            return "Username & Password do not match our records, please try again.";
        }
    }
}