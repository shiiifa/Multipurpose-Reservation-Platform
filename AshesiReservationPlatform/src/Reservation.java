public abstract class Reservation {
    public static final String URL = "https://calendly.com";

    // Constructor, no need to initialize time or date
    public Reservation() {
    }

    // Getter for the shared URL (constant URL)
    public String getUrl() {
        return URL;
    }
}
