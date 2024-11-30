import javax.swing.*;

public class AshesiReservationDriver {
    private String headerText;
    private String welcomeMessage;

    public AshesiReservationDriver() {
        this.headerText = "Welcome to Ashesi Reservation System";
        this.welcomeMessage = "Please select from the available options";
    }

    public void run() {
        new SystemLogin();
    }

    // Main method to run the driver
    public static void main(String[] args) {
        AshesiReservationDriver driver = new AshesiReservationDriver();
        driver.run();  // Run only the GUI with header and welcome messages
    }

    // Getter methods
    public String getHeaderText() {
        return headerText;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
