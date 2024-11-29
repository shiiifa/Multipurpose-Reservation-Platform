import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class AshesiReservationDriver {
    private String headerText;
    private String welcomeMessage;

    public AshesiReservationDriver() {
        this.headerText = "Welcome to Ashes Reservation System";

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
        AuthenticatorImpl auth = new AuthenticatorImpl();
        auth.addUser("student1", "password123");

        // Initialize feedback system
        FeedbackSystem feedback = new FeedbackSystem();

        try {
            // Demonstrate user authentication
            System.out.println("\n1. Testing Authentication System:");
            auth.signIn();

            // Create a user identity
            Identity studentIdentity = new Identity("John Doe", 12345, "john.doe@university.edu");
            System.out.println("User created: " + studentIdentity.getName());

            // Demonstrate meeting bookings
            System.out.println("\n2. Testing Meeting Bookings:");

            // Create a peer tutoring session
            PeerTutoring tutoring = new PeerTutoring(
                    "Math Tutoring",
                    "Tom Smith",
                    LocalTime.of(14, 30),
                    LocalDate.now().plusDays(1),
                    "meet.university.edu/math",
                    "Calculus 101",
                    "John Doe"
            );
            System.out.println("Tutoring session created for: " + tutoring.getCourseName());
            System.out.println("Time: " + tutoring.getSessionTime());

            // Demonstrate room reservations
            System.out.println("\n3. Testing Room Reservations:");

            // Book a classroom
            ClassroomBooking classroom = new ClassroomBooking(
                    "Room 101",
                    "Science Building",
                    "booking.university.edu/room101",
                    "Lecture Hall"
            );
            System.out.println("Classroom booked: " + classroom.getRoomName());
            System.out.println("Availability: " + classroom.checkAvailability());

            // Demonstrate housing system
            System.out.println("\n4. Testing Housing System:");

            // Create housing reservation
            OnCampusHousing housing = new OnCampusHousing(
                    "Dorm 205",
                    "West Campus",
                    "housing.university.edu/dorm205",
                    "Double Room",
                    Arrays.asList("John Doe")
            );
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