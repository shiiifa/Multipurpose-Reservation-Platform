import java.sql.Time;
import java.util.Date;

public class RemoteReservation extends Reservation {
    private String roomName;
    private String location;

    public RemoteReservation(Time time, Date date, String url, String roomName, String location) {
        super(time, date, url);
        this.roomName=roomName;
        this.location=location;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean checkAvailability() {
        // Logic to check availability
        return true;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String getDetails() {
        return "Room Name: " + roomName + ", Location: " + location;
    }
}
