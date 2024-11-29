public class Identity {
    private String userName;
    private int userID;
    private String userEmail;

    public Identity (String userName, int userID, String userEmail){
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
    }
    // Identity methods
    public void setIdentity(String userName, int userID, String userEmail) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
    }

    public void acceptUserData() {
        //Logic to accept user data
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
}
