public class PeerTutoring extends MeetUp {
    private String courseName;
    private String studentName;

    public PeerTutoring(String courseName, String studentName){
        super(sessionName, guest);
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
