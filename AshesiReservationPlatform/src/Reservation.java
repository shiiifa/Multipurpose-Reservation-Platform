import java.sql.Time;
import java.util.Date;

public abstract class Reservation {
    private Time time;
    private Date date;
    private String unit;

    public Reservation(Time time, Date date, String unit){
        this.time=time;
        this.date=date;
        this.unit=unit;
    }

    public Time getTime() {
        return time;
    }

    public String getDate() {
        return date.toString();
    }

    public String getUnit() {
        return unit;
    }

    public abstract String getDetails();
}
