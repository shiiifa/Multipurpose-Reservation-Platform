import java.sql.Time;
import java.util.Date;

public abstract class Reservation {
    // Static constant for the URL that all subclasses will share
    public static final String URL = "https://calendly.com";

    private Time time;
    private Date date;

    // Constructor for initializing time and date
    public Reservation(Time time, Date date, String url) {
        this.time = time;
        this.date = date;
    }

    // Getter for time
    public Time getTime() {
        return time;
    }

    // Getter for date as a String
    public String getDate() {
        return date.toString();
    }

    // Getter for the shared URL (constant URL)
    public String getUrl() {
        return URL;
    }

    // Abstract method for details that must be implemented in subclasses
    public abstract String getDetails();
}
