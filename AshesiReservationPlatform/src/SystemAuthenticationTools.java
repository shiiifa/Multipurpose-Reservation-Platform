public interface SystemAuthenticationTools {
    String getFeedback();        // Get feedback messages

    void signIn(String userName, String password);   // Sign in a user with credentials

    void signUp(String userName, int userID, String userEmail, String password);   // Sign up a new user
}
