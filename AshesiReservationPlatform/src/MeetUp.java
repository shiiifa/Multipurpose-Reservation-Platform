import java.time.LocalDate;
import java.time.LocalTime;

class MeetUp implements Calendar {
    private String sessionName;
    private String guest;
    private LocalTime time;
    private LocalDate date;
    private String url;

    public MeetUp(String sessionName, String guest, LocalTime time, LocalDate date, String url) {
        this.sessionName = sessionName;
        this.guest = guest;
        this.time = time;
        this.date = date;
        this.url = url;
    }

    public String getSessionTime() {
        return time.toString();
    }

    public String getGuest() {
        return guest;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getUrl() {
        return url;
    }
}