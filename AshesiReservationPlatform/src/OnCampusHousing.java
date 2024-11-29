import java.util.List;

public class OnCampusHousing extends RemoteReservation {
    private int currentOccupancy;
    private String roomType;
    private List<String> currentOccupants;

    public OnCampusHousing (String roomName, String location, int currentOccupancy, String roomType, List<String> currentOccupants){
        super(roomName, location);

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
