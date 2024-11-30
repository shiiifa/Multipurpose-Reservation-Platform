import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class SystemAuthenticationImpl extends Identity implements SystemAuthenticationTools {
    private List<Identity> users; // List to store user identities
    private String feedback;
    private String receipt;
    private String roomName;
    private List<String> currentOccupants;

    // Constructor for initializing the system
    public SystemAuthenticationImpl(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
        this.users = new ArrayList<>();
        this.currentOccupants = new ArrayList<>();
    }

    // Add a new user
    public void addUser(String userName, int userID, String userEmail, String password) {
        Identity newUser = new Identity(userName, userID, userEmail, password);
        users.add(newUser);
    }

    // Sign-in method
    public void signIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String inputUserName = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        boolean authenticated = false;

        for (Identity user : users) {
            if (user.getUserName().equals(inputUserName) && user.getPassword().equals(inputPassword)) {
                authenticated = true;
                break;
            }
        }

        if (authenticated) {
            System.out.println("Sign-in successful!");
        } else {
            System.out.println("Authentication failed.");
        }
    }

    // Feedback methods
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void generateReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String displayReceipt() {
        return receipt;
    }

    public String message() {
        return "";
    }

    public String getNotification() {
        return "";
    }

    // Reservation methods
    public void setRoomReservation(String roomName, List<String> currentOccupants) {
        this.roomName = roomName;
        this.currentOccupants = currentOccupants;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean checkAvailability() {
        return currentOccupants.size() < 10; // Example threshold for room capacity
    }

    public int getCurrentOccupancy() {
        return currentOccupants.size();
    }
}
