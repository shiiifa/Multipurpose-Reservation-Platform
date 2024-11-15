import java.time.LocalDate;
import java.time.LocalTime;

class OfficeHours extends MeetUp {
    private String courseName;
    private String facultyName;

    public OfficeHours(String sessionName, String guest, LocalTime time, LocalDate date,
                       String url, String courseName, String facultyName) {
        super(sessionName, guest, time, date, url);
        this.courseName = courseName;
        this.facultyName = facultyName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }
}
