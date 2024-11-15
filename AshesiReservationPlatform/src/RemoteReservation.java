class RemoteReservation {
    private String roomName;
    private String location;
    private String url;

    public RemoteReservation(String roomName, String location, String url) {
        this.roomName = roomName;
        this.location = location;
        this.url = url;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean checkAvailability() {
        // Implementation would typically check against a database or reservation system
        return true;
    }

    public String getLocation() {
        return location;
    }
}
