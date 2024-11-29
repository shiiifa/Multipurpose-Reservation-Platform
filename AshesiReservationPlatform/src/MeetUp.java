import java.sql.Time;
import java.util.Date;

public class MeetUp extends Identity {
    private String sessionName;
    private String guest;
    private Time time; 
    private Date date;
    private String url;

    
    
    
    public MeetUp(String userName, int userID, String userEmail){
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

    public Time getTime(){
        return time;
    }

    public Date getDate(){
        return date;
    }

    public String getUrl(){
        return url;
    }

    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }
}
