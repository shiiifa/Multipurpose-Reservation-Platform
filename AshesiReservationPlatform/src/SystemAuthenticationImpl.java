import java.util.*;
import java.util.regex.*;

public abstract class SystemAuthenticationImpl extends Identity implements SystemAuthenticationTools {
    private List<Identity> users;
    private String feedback;
    private String receipt;
    private String roomName;
    private List<String> currentOccupants;

    public SystemAuthenticationImpl(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
        this.users = new ArrayList<>();
        this.currentOccupants = new ArrayList<>();
    }

    public void addUser(String userName, int userID, String userEmail, String password) {
        Identity newUser = new Identity(userName, userID, userEmail, password);
        users.add(newUser);
    }

    // Validation for username, email, and password
    public boolean validateInput(String userName, int userID, String userEmail, String password) {
        // Validate username (letters a-z, case insensitive)
        if (!userName.matches("[a-zA-Z]+")) {
            feedback = "Username must contain only letters.";
            return false;
        }

        // Validate email (basic format check)
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!userEmail.matches(emailPattern)) {
            feedback = "Email must contain '@' and be in a valid format.";
            return false;
        }

        // Validate password (at least 8 characters)
        if (password.length() < 8) {
            feedback = "Password must be at least 8 characters long.";
            return false;
        }

        feedback = "Valid input.";
        return true;
    }

    public void signIn(String userName, String password) {
        Scanner scanner = new Scanner(System.in);
        boolean authenticated = false;

        // Authenticate user credentials
        for (Identity user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                authenticated = true;
                break;
            }
        }

        if (authenticated) {
            System.out.println("Sign-in successful!");
        } else {
            System.out.println("Authentication failed.");
        }
    }
}
