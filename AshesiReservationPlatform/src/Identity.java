class Identity implements Authenticator {
    private String userName;
    private int userID;
    private String userEmail;

    public Identity(String userName, int userID, String userEmail) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
    }

    public String getName() {
        return userName;
    }

    public String getID() {
        return String.valueOf(userID);
    }

    public String getEmail() {
        return userEmail;
    }

    public void acceptUserData(String userName, int userID, String userEmail) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
    }

    @Override
    public void signIn() {

    }

    @Override
    public void signUp() {

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