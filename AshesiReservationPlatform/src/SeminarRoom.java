public class SeminarRoom extends RemoteReservation {
    private String reservationPurpose;

    public SeminarRoom(String roomName, String location, String reservationPurpose){
        super(roomName, location);
        this.reservationPurpose=reservationPurpose;
    }
    public String getReservationPurpose() {
        return reservationPurpose;
    }
}
