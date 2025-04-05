package st10101766.core;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, Registration> users = new HashMap<>();

    public String[] registerUser(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        if (users.containsKey(username)) {
            return new String[]{"Username already exists"};
        }
        Registration reg = new Registration();
        String[] result = reg.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        if (result[0].equals("You have been successfully registered")) {
            users.put(username, reg);
        }
        return result;
    }

    public Registration getUser(String username) {
        return users.get(username);
    }
}