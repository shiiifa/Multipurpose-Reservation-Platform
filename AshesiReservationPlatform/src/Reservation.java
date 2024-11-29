import java.sql.Time;
import java.util.Date;

public abstract class Reservation {
    private Time time;
    private Date date;
    private String url;

    public Reservation(Time time, Date date, String url){
        this.time=time;
        this.date=date;
        this.url=url;
    }

    public Time getTime() {
        return time;
    }

    public String getDate() {
        return date.toString();
    }

    public String getUrl() {
        return url;
    }

    public abstract String getDetails();
}
