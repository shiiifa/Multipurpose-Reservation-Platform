import java.sql.Time;
import java.util.Date;

public class RemoteReservation extends Reservation {
    private String roomName;
    private String location;

    // Constructor for RemoteReservation
    public RemoteReservation(Time time, Date date, String url, String roomName) {
        super(time, date, url); // Calls the parent constructor in Reservation class
        this.roomName = roomName;
        this.location = location;
    }

    // Getter for roomName
    public String getRoomName() {
        return roomName;
    }

    // Override getDetails to include room and location details
    @Override
    public String getDetails() {
        return "\nRoom Name: " + roomName +
                "\nLocation: " + location;
    }
}
