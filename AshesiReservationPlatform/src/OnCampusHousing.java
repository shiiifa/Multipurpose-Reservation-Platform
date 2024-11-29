import java.util.List;

public class OnCampusHousing extends RemoteReservation {
    private int currentOccupancy;
    private String roomType;
    private List<String> currentOccupants;

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
