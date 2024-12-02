public class PeerTutoring extends MeetUp {
    public static final String PEER_TUTORING_URL = "https://bookingsite-28132.web.app/";

    public PeerTutoring(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
    }

    public static String getPeerTutoringURL() {
        return PEER_TUTORING_URL;
    }
}
