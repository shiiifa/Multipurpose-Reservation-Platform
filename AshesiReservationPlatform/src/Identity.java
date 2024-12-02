public class Identity {
    private String userName;
    private int userID;
    private String userEmail;
    private String password;

    public Identity(String userName, int userID, String userEmail, String password) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
