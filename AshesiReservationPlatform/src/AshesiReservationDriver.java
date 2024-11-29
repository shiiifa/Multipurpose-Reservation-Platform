import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class AshesiReservationDriver {
    private String headerText;
    private String welcomeMessage;

    public AshesiReservationDriver() {
        this.headerText = "Welcome to Ashesi Reservation System";

        this.welcomeMessage = "Please select from the available options";
    }

    public void displayHeader() {
        System.out.println("*************************************");
        System.out.println(headerText);
        System.out.println("*************************************");
        System.out.println(welcomeMessage);
        System.out.println("*************************************");
    }

    public void run() {
        // Initialize the system
        displayHeader();

        // Initialize authentication system
        SystemAuthenticationImpl auth = new SystemAuthenticationImpl();
        auth.addUser("student1", "password123");

        // Initialize feedback system
        SystemAuthenticationImpl feedback = new SystemAuthenticationImpl();

        try {
            // Demonstrate user authentication
            System.out.println("\n1. Testing Authentication System:");
            auth.signIn();

            // Create a user identity
            Identity studentIdentity = new Identity("John Doe", 12345, "john.doe@ashesi.edu.gh");
            System.out.println("User created: " + studentIdentity.getUserName());

            // Demonstrate meeting bookings
            System.out.println("\n2. Testing Meeting Bookings:");

            // Create a peer tutoring session
            PeerTutoring tutoring = new PeerTutoring(
                    "TomSmith",
                    48822028,
                    "tom.smith@ashesi.edu.gh",
                    "Lesson on Derivatives",
                    "ABCDEF",
                    Time.valueOf("15:30:00"),
                    Date.valueOf("2024-11-29"), "calendly.com",
                    "Engineering Calculus",
                    "Derrick Osei");


            System.out.println("Tutoring session created for: " + tutoring.getCourseName());
            System.out.println("Time: " + tutoring.getTime());

            // Demonstrate room reservations
            System.out.println("\n3. Testing Room Reservations:");

            // Book a classroom
            ClassroomBooking classroom = new ClassroomBooking(
                    Time.valueOf("15:30:00"),
                    Date.valueOf("2024-11-29"),
                    "calendly.com",
                    "RB100",
                    "Next to RB115",
                    "Lecture hall");
            System.out.println("Classroom booked: " + classroom.getRoomName());
            System.out.println("Availability: " + classroom.checkAvailability());


            // Demonstrate housing system
            System.out.println("\n4. Testing Housing System:");


            // Create housing reservation
            OnCampusHousing housing = new OnCampusHousing(
                    Time.valueOf("15:30:00"),
                    Date.valueOf("2024-11-29"),
                    "calendly.com",
                    "Double Room", "Kofi Tawiah",
                    1, "Two-in-a-rooom",
                    Arrays.asList("John Doe"));

            System.out.println("Housing assigned: " + housing.getRoomName());
            System.out.println("Current occupancy: " + housing.getCurrentOccupancy());

            // Demonstrate feedback system
            System.out.println("\n5. Testing Feedback System:");
            feedback.setFeedback("Great experience with the room booking system!");
            feedback.generateReceipt("Room 101 booking confirmation");
            System.out.println("Feedback received: " + feedback.getFeedBack());
            System.out.println("Receipt: " + feedback.displayReceipt());

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            System.out.println("\n*************************************");
            System.out.println("Thank you for using Ashes Reservation System");
            System.out.println("*************************************");
        }
    }

    // Main method to run the driver
    public static void main(String[] args) {
        AshesiReservationDriver driver = new AshesiReservationDriver();
        driver.run();
    }

    // Getter methods
    public String getHeaderText() {
        return headerText;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}