import java.util.ArrayList;
import java.util.List;

abstract class SystemAuthenticationImpl extends Identity implements SystemAuthenticationTools {
    private List<Identity> users;
    private String feedback;

    public SystemAuthenticationImpl(String userName, int userID, String userEmail, String password, List users, String feedback) {
        super(userName, userID, userEmail, password);
        this.users = new ArrayList<>();
    }

    public void addUser(String userName, int userID, String userEmail, String password) {
        Identity newUser = new Identity(userName, userID, userEmail, password);
        users.add(newUser);
    }

    public boolean validateInput(String userName, int userID, String userEmail, String password) {
        if (!userName.matches("[a-zA-Z]+")) {
            feedback = "Username must contain only letters.";
            return false;
        }
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!userEmail.matches(emailPattern)) {
            feedback = "Email must be in a valid format.";
            return false;
        }
        if (password.length() < 8) {
            feedback = "Password must be at least 8 characters.";
            return false;
        }
        feedback = "Valid input.";
        return true;
    }

    public String getFeedback() {
        return feedback;
    }
}