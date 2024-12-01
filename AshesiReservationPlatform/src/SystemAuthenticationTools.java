import java.util.List;

public interface SystemAuthenticationTools {
    // Methods related to feedback
    void setFeedback(String feedback);  // Set feedback from the user
    List<String> getFeedbacks();        // Get all feedbacks

    // Methods for room reservation
    void setRoomReservation(String roomName, List<String> currentOccupants);  // Set room reservation
    boolean checkAvailability();        // Check room availability
    int getCurrentOccupancy();          // Get current occupancy for the room
    String getRoomName();               // Get reserved room name

    // Authentication methods
    void signIn();                      // Sign-in method

    // Placeholder methods for notifications
    String message();                   // Get message
    String getNotification();           // Get notification
}
