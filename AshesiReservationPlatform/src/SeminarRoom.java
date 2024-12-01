import java.sql.Time;
import java.util.Date;

public class SeminarRoom extends RemoteReservation {
    private String reservationPurpose;

    // Constructor to initialize SeminarRoom
    public SeminarRoom(Time time, Date date, String roomName, String location, String reservationPurpose) {
        super(time, date, roomName, location); // Pass only time, date, roomName, and location to RemoteReservation constructor
        this.reservationPurpose = reservationPurpose; // Initialize reservationPurpose specific to SeminarRoom
    }

    // Getter for reservationPurpose
    public String getReservationPurpose() {
        return reservationPurpose;
    }

    // Override toString() if needed to represent the SeminarRoom object in a readable format
    @Override
    public String toString() {
        return super.toString() + "\nReservation Purpose: " + reservationPurpose;
    }
}
