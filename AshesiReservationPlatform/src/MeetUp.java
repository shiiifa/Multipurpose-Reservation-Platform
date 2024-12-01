public class MeetUp extends Identity {
    private String sessionName;
    private String guest;

    // Constructor for initializing the MeetUp
    public MeetUp(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
        this.sessionName = sessionName;
        this.guest = guest;
    }

    // Getters for each field
    public String getGuest() {
        return guest;
    }

    // Method to get session details
    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }

    // Override toString method to provide a detailed representation of the MeetUp
    @Override
    public String toString() {
        return super.toString() +
                "\nSession Name: " + sessionName +
                "\nGuest: " + guest;
    }
}
