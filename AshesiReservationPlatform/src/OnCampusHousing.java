import java.util.ArrayList;
import java.util.List;

class OnCampusHousing extends RemoteReservation {
    private String roomType;
    private List<String> currentOccupants;
    private int currentOccupancy;

    public OnCampusHousing(String roomName, String location, String url,
                           String roomType, List<String> currentOccupants) {
        super(roomName, location, url);
        this.roomType = roomType;
        this.currentOccupants = new ArrayList<>(currentOccupants);
        this.currentOccupancy = currentOccupants.size();
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public String getRoomType() {
        return roomType;
    }

    public List<String> displayCurrentOccupants() {
        return new ArrayList<>(currentOccupants);
    }

    public void addOccupant(String occupant) {
        currentOccupants.add(occupant);
        currentOccupancy = currentOccupants.size();
    }

    public void removeOccupant(String occupant) {
        currentOccupants.remove(occupant);
        currentOccupancy = currentOccupants.size();
    }
}