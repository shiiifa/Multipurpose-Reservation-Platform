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

    public OfficeHours(String userName, int userID, String userEmail, String password, String courseName, String facultyName) {
        super(userName, userID, userEmail, password);
        this.courseName = courseName;
        this.facultyName = facultyName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    // Method to retrieve the map of courses and faculty members
    public static Map<String, List<String>> getCourseFaculty() {
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
                "Kofi Adu-Labi"
        )));
        COURSE_FACULTY.put("Electromagnetism", new ArrayList<>());
    }

    // Method to get faculty members for a specific course
    public static List<String> getFacultyForCourse(String courseName) {
        return COURSE_FACULTY.getOrDefault(courseName, new ArrayList<>());
    }
}
