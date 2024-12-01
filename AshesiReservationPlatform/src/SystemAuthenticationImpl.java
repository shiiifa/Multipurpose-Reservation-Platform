import java.util.*;

public class SystemAuthenticationImpl extends Identity implements SystemAuthenticationTools {
    private List<Identity> users; // List to store user identities
    private String feedback;      // Stores user feedback
    private String receipt;       // Stores booking receipt details
    private String roomName;      // Name of the reserved room
    private List<String> currentOccupants; // List of current occupants for the reservation

    // Constructor for initializing the system
    public SystemAuthenticationImpl(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
        this.users = new ArrayList<>();
        this.currentOccupants = new ArrayList<>();
    }

    // Add a new user to the system
    public void addUser(String userName, int userID, String userEmail, String password) {
        Identity newUser = new Identity(userName, userID, userEmail, password);
        users.add(newUser);
    }

    // User sign-in method
    public void signIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String inputUserName = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        boolean authenticated = false;

        // Authenticate user credentials
        for (Identity user : users) {
            if (user.getUserName().equals(inputUserName) && user.getPassword().equals(inputPassword)) {
                authenticated = true;
                break;
            }
        }

        // Provide feedback based on authentication status
        if (authenticated) {
            System.out.println("Sign-in successful!");
        } else {
            System.out.println("Authentication failed.");
        }
    }

    // Method to accept and set user feedback
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // Method to retrieve user feedback
    public String getFeedback() {
        return feedback;
    }

    // Generate booking receipt
    public void generateReceipt(String receipt) {
        this.receipt = receipt;
    }

    // Display booking receipt
    public String displayReceipt() {
        return receipt;
    }

    // Placeholder methods for notifications and alerts
    public String message() {
        return "No new messages.";
    }

    public String getNotification() {
        return "No new notifications.";
    }

    // Room reservation functionality
    public void setRoomReservation(String roomName, List<String> currentOccupants) {
        this.roomName = roomName;
        this.currentOccupants = new ArrayList<>(currentOccupants); // Clone the list
    }

    // Retrieve reserved room name
    public String getRoomName() {
        return roomName;
    }

    // Check if the room has available capacity
    public boolean checkAvailability() {
        return currentOccupants.size() < 10; // Example threshold for room capacity
    }

    // Get the current occupancy of the room
    public int getCurrentOccupancy() {
        return currentOccupants.size();
    }
}
