import java.sql.Time;
import java.util.Date;

public class MeetUp extends Reservation {
    private String sessionName;
    private String guest;

    public MeetUp(Time time, Date date, String unit, String sessionName, String guest){
        super(time, date, unit);
        this.sessionName=sessionName;
        this.guest=guest;
    }

    public String getSessionName() {
        return sessionName();
    }

    public String getSessionTime(){
        return getSessionTime();
    }

    public String getGuest() {
        return guest;
    }

    @Override
    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }
}
