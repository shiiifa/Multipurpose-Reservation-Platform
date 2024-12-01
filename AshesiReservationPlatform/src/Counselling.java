public class Counselling extends MeetUp {
    private String counsellorName;

    // Constructor for initializing Counselling object
    public Counselling(String userName, int userID, String userEmail, String password, String sessionName, String guest, String counsellorName) {
        super(userName, userID, userEmail, password);
        this.counsellorName = "Salim Wangabi";
    }

    // Getter for counsellorName
    public String getCounsellorName() {
        return counsellorName;
    }

    // Override the toString method to provide a summary of the Counselling object
    @Override
    public String toString() {
        return super.toString() +
                "\nSession Type: Counselling" +
                "\nCounsellor Name: " + counsellorName;
    }
}
