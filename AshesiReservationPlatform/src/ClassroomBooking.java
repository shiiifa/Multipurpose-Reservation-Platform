import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClassroomBooking extends RemoteReservation {
    private String classroomType;

    // Constructor to initialize the ClassroomBooking object
    public ClassroomBooking(String classroomType) {
        super(); // Calling the parent constructor in RemoteReservation
        this.classroomType = classroomType;
    }

    // Getter method for classroom type
    public String getClassroomType() {
        return classroomType;
    }

    // Method to return a list of classroom names and types
    public static Map<String, String> getClassroomList() {
        Map<String, String> classrooms = new HashMap<>();
        classrooms.put("RB100", "Lecture room");
        classrooms.put("RB115", "Lecture room");
        classrooms.put("Jackson Lab 221", "Lecture hall");
        classrooms.put("Jackson Hall 116", "Lecture hall");
        classrooms.put("Norton Motulsky 207A", "Lecture room");
        classrooms.put("Norton Motulsky 207B", "Lecture room");
        classrooms.put("Araba Apt 217", "Lecture hall");
        return classrooms;
    }

    // Overriding toString to include classroom type
    @Override
    public String toString() {
        return super.toString() + "\nClassroom Type: " + classroomType;
    }
}
