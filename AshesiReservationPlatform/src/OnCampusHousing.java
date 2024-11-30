import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnCampusHousing extends RemoteReservation {
    private int currentOccupancy;
    private String roomType;
    private List<String> currentOccupants;

    // Static map to store hostels and their rooms
    private static final Map<String, List<String>> HOSTEL_ROOMS = new HashMap<>();

    // Static block to initialize hostels and rooms
    static {
        initializeHostelsAndRooms();
    }

    public OnCampusHousing(Time time, Date date, String url, String roomName, String location, int currentOccupancy, String roomType, List<String> currentOccupants) {
        super(time, date, url, roomName, location);
        this.currentOccupancy = currentOccupancy;
        this.roomType = roomType;
        this.currentOccupants = currentOccupants;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public String getRoomType() {
        return roomType;
    }

    public List<String> displayCurrentOccupants() {
        return currentOccupants;
    }

    // Method to retrieve the map of hostels and their rooms
    public static Map<String, List<String>> getHostelRooms() {
        return HOSTEL_ROOMS;
    }

    // Method to initialize hostels and rooms
    private static void initializeHostelsAndRooms() {
        String[] hostels = {"Kofi Tawiah Hostel", "2C Hostel", "2D Hostel", "Oteng Korankye Hostel", "Wangari Maathai Hostel"};
        for (String hostel : hostels) {
            List<String> rooms = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                rooms.add("Room " + i);
            }
            HOSTEL_ROOMS.put(hostel, rooms);
        }
    }

    // Method to get rooms for a specific hostel
    public static List<String> getRoomsForHostel(String hostelName) {
        return HOSTEL_ROOMS.getOrDefault(hostelName, new ArrayList<>());
    }
}
