public class RemoteReservation extends Reservation {
    private String roomName;
    private String location;

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
