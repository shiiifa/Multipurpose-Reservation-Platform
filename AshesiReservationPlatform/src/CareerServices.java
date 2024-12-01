public class CareerServices extends MeetUp {
    private String facultyName;
    private int yearGroup;

    public CareerServices (String userName, int userID, String userEmail, String password,
                           String sessionName, String guest, String facultyName, int yearGroup){
        super(userName, userID, userEmail, password);
        this.facultyName=facultyName;
        this.yearGroup=yearGroup;
    }

    public String getStaffName() {
        return facultyName;
    }

    public int getYearGroup() {
        return yearGroup;
    }
}