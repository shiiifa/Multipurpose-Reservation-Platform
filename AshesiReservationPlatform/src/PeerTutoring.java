public class PeerTutoring extends MeetUp {
    private String courseName;
    private String studentName;

    public PeerTutoring(String sessionName, String guest, Time time, Date date, String url, String courseName, String studentName){
        super(sessionName, guest, time, date, url);
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
