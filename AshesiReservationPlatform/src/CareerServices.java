import java.time.LocalDate;
import java.time.LocalTime;

class CareerServices extends MeetUp {
    private String facultyName;
    private String yearGroup;

    public CareerServices(String sessionName, String guest, LocalTime time, LocalDate date,
                          String url, String facultyName, String yearGroup) {
        super(sessionName, guest, time, date, url);
        this.facultyName = facultyName;
        this.yearGroup = yearGroup;
    }

    public String getStaffName() {
        return facultyName;
    }

    public int getYearGroup() {
        return Integer.parseInt(yearGroup);
    }
}
