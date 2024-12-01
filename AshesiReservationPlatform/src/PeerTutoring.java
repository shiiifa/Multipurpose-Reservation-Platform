import java.sql.Time;
import java.util.Date;

public class PeerTutoring extends MeetUp {
    private String courseName;
    private String studentName;

    // Constructor to initialize PeerTutoring object
    public PeerTutoring(String userName, int userID, String userEmail, String password, String sessionName, String guest, Time time, Date date, String url, String courseName, String studentName) {
        // Passing the sessionName, guest, time, date, and url to the superclass MeetUp
        super(userName, userID, userEmail, password);
        this.courseName = courseName;
        this.studentName = studentName;
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Getter for studentName
    public String getStudentName() {
        return studentName;
    }

    // Override toString to include details about PeerTutoring
    @Override
    public String toString() {
        return super.toString() +
                "\nCourse Name: " + courseName +
                "\nStudent Name: " + studentName;
    }
}
