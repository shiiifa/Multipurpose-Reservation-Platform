public class MeetUp extends Reservation {
    private String sessionName;
    private String guest;

    public String getSessionTime() {
        return getTime();
    }

    public String getGuest() {
        return guest;
    }

    @Override
    public String getDetails() {
        return "Session: " + sessionName + ", Guest: " + guest;
    }
}
