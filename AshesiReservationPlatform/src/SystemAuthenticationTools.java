import java.util.List;

public interface SystemAuthenticationTools {
    // Methods related to feedback
    void setFeedback(String feedback);  // Set feedback from the user
    List<String> getFeedbacks();        // Get all feedbacks
    String message();
    String getNotification();

    // Authentication methods
    void signIn();
    void signUp();
}
