public class CareerServices extends MeetUp {
    private String facultyName;
    private int yearGroup;

    public CareerServices(String userName, int userID, String userEmail, String password,
                          String sessionName, String guest, String facultyName, int yearGroup) {
        super(userName, userID, userEmail, password);
        this.facultyName = facultyName;
        this.yearGroup = yearGroup;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public int getYearGroup() {
        return yearGroup;
    }

    // Get the faculty based on the selected year group
    public static String getFacultyForYearGroup(int yearGroup) {
        if (yearGroup == 2028) {
            return "Nana Afua Ladje Anoff";
        } else if (yearGroup == 2027 || yearGroup == 2026 || yearGroup == 2025) {
            return "Alberta Awura Adjoa Asiamah";
        } else {
            return "No assigned faculty";
        }
    }
}
