import java.util.Date;

public abstract class Reservation {
    private String time;
    private Date date;
    private String unit;

    public String getTime() {
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
