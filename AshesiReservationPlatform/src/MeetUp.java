import java.sql.Time;
import java.util.Date;

public class MeetUp extends Identity {
    private String sessionName;
    private String guest;
    private Time time; 
    private Date date;
    private String url;
    
    
    
    public MeetUp(String userName, int userID, String userEmail, String sessionName, String guest, Time time, Date date, String url){
        super(userName, userID, userEmail);
        this.sessionName = sessionName;
        this.guest = guest;
        this.time = time;
        this.date = date;
        this.url = url;
    }

    public String getSessionTime() {
        return getSessionTime();
    }

    public String getGuest() {
        return guest;
    }

    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }
}
