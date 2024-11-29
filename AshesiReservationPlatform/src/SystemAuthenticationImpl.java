import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class SystemAuthenticationImpl implements SystemAuthenticationTools {
    private HashMap<String, String> users;
    private String feedback;
    private String receipt;
    private String name;
    private int id;
    private String email;
    private String roomName;
    private List<String> currentOccupants;

    // Constructor for initializing the system
    public SystemAuthenticationImpl() {
        this.users = new HashMap<>();
        this.currentOccupants = new ArrayList<>();
    }

    // Authentication methods
    public void addUser(String username, String password) {
        users.put(username, password);
    }

    public void signIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Sign-in successful!");
        }

        else {
            System.out.println("Authentication failed.");
        }
    }

    //----------------------------------------------------------------------------------------------------
    // Feedback methods
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedBack() {
        return feedback;
    }

    public void generateReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getFeedback() {
        return "";
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
