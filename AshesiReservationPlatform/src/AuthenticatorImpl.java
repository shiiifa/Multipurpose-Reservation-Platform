import java.util.HashMap;
import java.util.Map;

class AuthenticatorImpl implements Authenticator {
    private Map<String, String> userCredentials;

    public AuthenticatorImpl() {
        this.userCredentials = new HashMap<>();
    }

    @Override
    public void signIn() {
        // Implementation would typically validate credentials
        System.out.println("Sign in process initiated");
    }

    @Override
    public void signUp() {
        // Implementation would typically create new user account
        System.out.println("Sign up process initiated");
    }

    public void addUser(String username, String password) {
        userCredentials.put(username, password);
    }

    public boolean validateUser(String username, String password) {
        return password.equals(userCredentials.get(username));
    }

    @Override
    public String getFeedBack() {
        return "";
    }

    @Override
    public String displayReceipt() {
        return "";
    }

    @Override
    public String message() {
        return "";
    }

    @Override
    public String getNotification() {
        return "";
    }
}