class SeminarRoom extends RemoteReservation {
    private String reservationPurpose;

    public SeminarRoom(String roomName, String location, String url, String reservationPurpose) {
        super(roomName, location, url);
        this.reservationPurpose = reservationPurpose;
    }

    public String getReservationPurpose() {
        return reservationPurpose;
    }
}