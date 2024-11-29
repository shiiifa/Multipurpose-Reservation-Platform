public class OfficeHours extends MeetUp {
    private String courseName;
    private String facultyName;

    public OfficeHours(String userName, int userID, String userEmail, String courseName, String facultyName){
        super(userName, userID, userEmail);
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
