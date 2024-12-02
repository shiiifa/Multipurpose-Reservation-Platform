import java.sql.Time;
import java.util.Date;

public class OnCampusHousing extends RemoteReservation {
    public static final String HOUSING_URL = "https://www.ahs.mgmhubs.com/";

    public OnCampusHousing(Time time, Date date, String url, String roomName) {
        super(time, date, url, roomName);
    }

    @Override
    public String toString() {
        return "On-Campus Housing Service URL: " + HOUSING_URL;
    }
}
