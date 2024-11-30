public class OfficeHours extends MeetUp {
    private String courseName;
    private String facultyName;

    public OfficeHours(String userName, int userID, String userEmail, String password, String courseName, String facultyName){
        super(userName, userID, userEmail, password);
        this.courseName=courseName;
        this.facultyName=facultyName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }
}
