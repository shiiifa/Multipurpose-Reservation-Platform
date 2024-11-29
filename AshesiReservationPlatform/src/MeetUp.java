import java.sql.Time;
import java.util.Date;

public class MeetUp extends Identity implements Calender {
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

    public String getSessionName() {
        return sessionName;
    }

    public String getGuest() {
        return guest();
    }
    public Time getTime() {
        return time;
    }

    public Date getDate(){
        return date;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }
}
