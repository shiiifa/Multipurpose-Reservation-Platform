import java.sql.Time;
import java.util.Date;

public class SeminarRoom extends RemoteReservation {
    private String reservationPurpose;

    public SeminarRoom(Time time, Date date, String url, String roomName, String location, String reservationPurpose){
        super(time, date, url, roomName, location);
        this.reservationPurpose=reservationPurpose;
    }

    public String getReservationPurpose() {
        return reservationPurpose;
    }
}
