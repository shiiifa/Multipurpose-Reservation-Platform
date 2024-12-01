import java.sql.Time;
import java.util.Date;

public class MeetUp extends Identity {
    private String sessionName;
    private String guest;
    private Time time;
    private Date date;
    private String url;

    // Constructor for initializing the MeetUp
    public MeetUp(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
        // Default values to avoid null assignments
        this.sessionName = "Unknown Session";
        this.guest = "No Guest";
        this.time = new Time(0); // Midnight
        this.date = new Date(); // Current date
        this.url = "https://calendly.com"; // Default URL
    }

    // Getters for each field
    public String getGuest() {
        return guest;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }

    // Override toString method to provide a detailed representation of the MeetUp
    @Override
    public String toString() {
        return super.toString() +
                "\nSession Name: " + sessionName +
                "\nGuest: " + guest +
                "\nTime: " + time +
                "\nDate: " + date +
                "\nURL: " + url;
    }
}
