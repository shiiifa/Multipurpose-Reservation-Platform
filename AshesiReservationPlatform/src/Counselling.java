public class Counselling extends MeetUp {
    private String counsellorName;

    public Counselling (String sessionName, String guest, String counsellorName){
        super(sessionName, guest);
        this.counsellorName=counsellorName;
    }
    public String getCounsellorName() {
        return counsellorName;
    }
}
