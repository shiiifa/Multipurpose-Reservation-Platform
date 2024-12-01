public class Identity {
    private String userName;
    private int userID;
    private String userEmail;
    private String password;

    // Constructor to initialize Identity object
    public Identity(String userName, int userID, String userEmail, String password) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    // Setter method to update the Identity details
    public void setIdentity(String userName, int userID, String userEmail, String password) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    // Method to accept user data (currently empty)
    public void acceptUserData() {
        // Add functionality if needed, like validating or processing user data
    }

    // Getter method for userName
    public String getUserName() {
        return userName;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    // Getter method for userID
    public int getUserID() {
        return userID;
    }

    // Getter method for userEmail
    public String getUserEmail() {
        return userEmail;
    }
}
