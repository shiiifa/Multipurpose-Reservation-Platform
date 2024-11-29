public class Counselling extends MeetUp {
    private String counsellorName;

    public Counselling (String userName, int userID, String userEmail, String sessionName, String guest, String counsellorName){
        super(userName, userID, userEmail);
        this.counsellorName=counsellorName;
    }
    public String getCounsellorName() {
        return counsellorName;
    }
}
