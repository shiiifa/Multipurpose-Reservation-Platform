import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeHours extends MeetUp {
    private String courseName;
    private String facultyName;

    // Static map to store courses and their faculty members
    private static final Map<String, List<String>> COURSE_FACULTY = new HashMap<>();

    // Static block to initialize courses and their faculty members
    static {
        initializeCourseFaculty();
    }

    // Constructor to initialize the OfficeHours object
    public OfficeHours(String userName, int userID, String userEmail, String password) {
        super(userName, userID, userEmail, password);
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Getter for facultyName
    public String getFacultyName() {
        return facultyName;
    }

    // Static method to retrieve the map of courses and faculty members
    public static Map<String, List<String>> getCourseFacultyMap() {
        return COURSE_FACULTY;
    }

    // Helper method to initialize courses and their faculty members
    private static void initializeCourseFaculty() {
        COURSE_FACULTY.put("Object-Oriented Programming", new ArrayList<>(List.of(
                "Sussan Einakian", "Ayorkor Korsah", "Ebo Adjepon-Yamoah"
        )));
        COURSE_FACULTY.put("Statistics", new ArrayList<>(List.of(
                "Elena V. Rosca"
        )));
        COURSE_FACULTY.put("Discrete Structures and Theory", new ArrayList<>(List.of(
                "Ayawoa Sitsope Dagbovie", "Patrick Dwomfour"
        )));
        COURSE_FACULTY.put("Pre-Calculus I", new ArrayList<>(List.of(
                "Joseph Mensah"
        )));
        COURSE_FACULTY.put("Electromagnetism", new ArrayList<>(List.of(
                "Kofi Adu-Labi"
        )));
    }
}
