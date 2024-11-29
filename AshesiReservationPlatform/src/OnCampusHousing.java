import java.sql.Time;
import java.util.List;
import java.util.Date;

public class OnCampusHousing extends RemoteReservation {
    private int currentOccupancy;
    private String roomType;
    private List<String> currentOccupants;

    public OnCampusHousing (Time time, Date date, String url, String roomName, String location, int currentOccupancy, String roomType, List<String> currentOccupants){
        super(time, date, url, roomName, location);
        this.currentOccupancy=currentOccupancy;
        this.roomType=roomType;
        this.currentOccupants=currentOccupants;

    }
    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public String getRoomType() {
        return roomType;
    }

    public List<String> displayCurrentOccupants() {
        return currentOccupants;
    }
}
