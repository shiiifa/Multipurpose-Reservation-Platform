public class CareerServices extends MeetUp {
    private String facultyName;
    private int yearGroup;

    public CareerServices (String sessionName, String guest, String facultyName, int yearGroup){
        super(sessionName, guest);
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
