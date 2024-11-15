import java.time.LocalDate;
import java.time.LocalTime;

class Counselling extends MeetUp {
    private String counsellorName;

    public Counselling(String sessionName, String guest, LocalTime time, LocalDate date,
                       String url, String counsellorName) {
        super(sessionName, guest, time, date, url);
        this.counsellorName = counsellorName;
    }

    public String getCounsellorName() {
        return counsellorName;
    }
}