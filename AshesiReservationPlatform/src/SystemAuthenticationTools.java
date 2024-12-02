public interface SystemAuthenticationTools {
    String getFeedback();

    void signIn(String userName, String password);

    void signUp(String userName, int userID, String userEmail, String password);
}
