import java.sql.Time;
import java.util.Date;

public class PeerTutoring extends MeetUp {
    private String courseName;
    private String studentName;

    public PeerTutoring(String userName, int userID, String userEmail, String sessionName, String guest, Time time, Date date, String url, String courseName, String studentName){
        super(userName, userID, userEmail, sessionName, guest, time, date, url);
        this.courseName=courseName;
        this.studentName=studentName;

    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentName() {
        return studentName;
    }
}
