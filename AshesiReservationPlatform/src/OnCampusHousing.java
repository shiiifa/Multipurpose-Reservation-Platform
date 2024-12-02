import java.sql.Time;
import java.util.Date;

public class OnCampusHousing extends RemoteReservation {
    public static final String HOUSING_URL = "https://www.ahs.mgmhubs.com/";

    public OnCampusHousing() {
        super();
    }

    @Override
    public String toString() {
        return "On-Campus Housing Service URL: " + HOUSING_URL;
    }
}
