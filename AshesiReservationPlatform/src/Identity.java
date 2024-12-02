public class Identity {
    private String userName;
    private int userID;
    private String userEmail;
    private String password;

    public Identity (String userName, int userID, String userEmail, String password){
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.password=password;
    }
    // Identity methods
    public void setIdentity(String userName, int userID, String userEmail, String password) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
