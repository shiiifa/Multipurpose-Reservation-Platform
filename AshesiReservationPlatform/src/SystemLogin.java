import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SystemLogin extends JFrame {
    private JTextField usernameField, userIDField, emailField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private Identity currentUser;
    private CardLayout cardLayout;
    private JPanel cardPanel;


    public SystemLogin() {
        this.setTitle("Ashesi Multipurpose Reservation System");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.decode("#ad3537"));

        // CardLayout for dynamic screens
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // First screen (login)
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "login");

        // Add cardPanel to frame
        this.add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.decode("#ad3537"));
        Font customFont = new Font("Arial", Font.BOLD, 14);

        // Header Section with Logo and Title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#ad3537"));
        headerPanel.setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon("ashesi.png");
        Image img = logo.getImage();
        Image scaledImg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH); // Reduced size to 75x75
        logo = new ImageIcon(scaledImg);
        JLabel logoLabel = new JLabel(logo);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Ashesi Multipurpose Reservation System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(headerPanel, gbc);

        // Username Field
        usernameField = createTextField("Username");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        // User ID Field
        userIDField = createTextField("User ID");
        gbc.gridy = 2;
        panel.add(userIDField, gbc);

        // Email Field
        emailField = createTextField("Email");
        gbc.gridy = 3;
        panel.add(emailField, gbc);

        // Password Field
        passwordField = createPasswordField("Password");
        gbc.gridy = 4;
        panel.add(passwordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        gbc.gridy = 5;
        panel.add(showPasswordCheckBox, gbc);

        JLabel footerLabel = new JLabel("© 2024 Shifa | Bradley | Edinam for Ashesi University", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 10)); // Smaller font size
        footerLabel.setForeground(Color.WHITE);
        gbc.gridy = 20;
        panel.add(footerLabel, gbc);


        JButton loginButton = new JButton("Login/Sign Up");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty() || emailField.getText().isEmpty() || userIDField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Oops", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidEmail(emailField.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid email format!", "Oops", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidUsername(username)) {
                JOptionPane.showMessageDialog(null, "Invalid username. Only letters allowed.", "Oops", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters.", "Oops", JOptionPane.ERROR_MESSAGE);
            } else {
                int userID = Integer.parseInt(userIDField.getText());
                currentUser = new Identity(username, userID, emailField.getText(), password);
                handleUserAuthentication(currentUser);
            }
        });
        gbc.gridy = 6;
        panel.add(loginButton, gbc);

        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            userIDField.setText("");
        });
        gbc.gridy = 7;
        panel.add(resetButton, gbc);

        return panel;
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        textField.setPreferredSize(new Dimension(180, 30));
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(placeholder);
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField.setPreferredSize(new Dimension(180, 30));
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        return passwordField;
    }

    private void handleUserAuthentication(Identity user) {
        System.out.println("User authenticated: " + user.getUserName());
        System.out.println("User ID: " + user.getUserID());
        System.out.println("User Email address: " + user.getUserEmail());
        proceedToBookingSelection();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z ]+");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }


    private void proceedToBookingSelection() {
        JPanel bookingSelectionPanel = createBookingSelectionPanel();
        cardPanel.add(bookingSelectionPanel, "bookingSelection");
        cardLayout.show(cardPanel, "bookingSelection");
    }

    private JPanel createBookingSelectionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Select Reservation Type", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        String[] reservationTypes = {"Human-Based Reservations", "Remote Reservations"};
        JComboBox<String> reservationTypeDropdown = new JComboBox<>(reservationTypes);
        reservationTypeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panel.add(reservationTypeDropdown, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            String selectedType = (String) reservationTypeDropdown.getSelectedItem();
            if (selectedType.equals("Human-Based Reservations")) {
                showHumanReservationOptions();
            } else if (selectedType.equals("Remote Reservations")) {
                showRemoteReservationOptions();
            }
        });
        gbc.gridy = 2;
        panel.add(nextButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "login"));
        gbc.gridy = 3;
        panel.add(backButton, gbc);

        return panel;
    }

    private void showRemoteReservationOptions() {
        JPanel remoteReservationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        remoteReservationPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Remote Reservation Options", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; remoteReservationPanel.add(titleLabel, gbc);

        String[] remoteReservations = {
                "Housing Selection",
                "Classroom Booking",
                "Seminar Room Booking"
        };
        JComboBox<String> remoteReservationDropdown = new JComboBox<>(remoteReservations);
        remoteReservationDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; remoteReservationPanel.add(remoteReservationDropdown, gbc);

        JButton nextButton = new JButton("Go");
        nextButton.addActionListener(e -> {
            String selectedOption = (String) remoteReservationDropdown.getSelectedItem();
            handleRemoteReservationSelection(selectedOption);
        });
        gbc.gridy = 2; remoteReservationPanel.add(nextButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "bookingSelection"));
        gbc.gridy = 3; remoteReservationPanel.add(backButton, gbc);

        cardPanel.add(remoteReservationPanel, "remoteReservationOptions");
        cardLayout.show(cardPanel, "remoteReservationOptions");
    }

    private void handleRemoteReservationSelection(String selectedOption) {
        switch (selectedOption) {
            case "Housing Selection":
                openURL("https://www.ahs.mgmhubs.com/");
                break;
            case "Classroom Booking":
                showClassroomBookingOptions();
                break;
            case "Seminar Room Booking":
                openURL("https://warrenlibraryseminarroom.simplybook.me/v2/");
                break;
        }
    }

    private void showClassroomBookingOptions() {
        Map<String, String> classrooms = ClassroomBooking.getClassroomList();
        String[] classroomTypes = {"Lecture room", "Lecture hall"};

        JPanel classroomBookingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        classroomBookingPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Classroom Booking", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; classroomBookingPanel.add(titleLabel, gbc);

        // Dropdown for selecting classroom type
        JComboBox<String> classroomTypeDropdown = new JComboBox<>(classroomTypes);
        classroomTypeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; classroomBookingPanel.add(classroomTypeDropdown, gbc);

        // Dropdown for selecting classroom (this will be updated based on the classroom type)
        JComboBox<String> classroomDropdown = new JComboBox<>();
        classroomDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2; classroomBookingPanel.add(classroomDropdown, gbc);

        // Listener for classroom type selection to filter classrooms
        classroomTypeDropdown.addActionListener(e -> {
            String selectedType = (String) classroomTypeDropdown.getSelectedItem();
            updateClassroomDropdown(selectedType, classroomDropdown, classrooms);
        });

        // Dropdown for time selection
        String[] times = {
                "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM",
                "6:00 PM", "7:00 PM", "8:00 PM"
        };
        JComboBox<String> timeDropdown = new JComboBox<>(times);
        timeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3; classroomBookingPanel.add(timeDropdown, gbc);

        // Book Button
        JButton nextButton = new JButton("Book");
        nextButton.addActionListener(e -> {
            String selectedClassroom = (String) classroomDropdown.getSelectedItem();
            String selectedTime = (String) timeDropdown.getSelectedItem();
            handleClassroomBooking(selectedClassroom, selectedTime);
        });
        gbc.gridy = 4; classroomBookingPanel.add(nextButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "remoteReservationOptions"));
        gbc.gridy = 5; classroomBookingPanel.add(backButton, gbc);

        cardPanel.add(classroomBookingPanel, "classroomBookingOptions");
        cardLayout.show(cardPanel, "classroomBookingOptions");
        updateClassroomDropdown("Lecture room", classroomDropdown, classrooms);
    }

    private void handleClassroomBooking(String selectedClassroom, String selectedTime) {
        JOptionPane.showMessageDialog(null,
                "Booking Successful!\nClassroom: " + selectedClassroom + "\nTime: " + selectedTime,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        showFeedbackPanel();
        showThankYouMessage();
    }


    private void updateClassroomDropdown(String selectedType, JComboBox<String> classroomDropdown, Map<String, String> classrooms) {
        classroomDropdown.removeAllItems();
        for (Map.Entry<String, String> entry : classrooms.entrySet()) {
            if (entry.getValue().equals(selectedType)) {
                classroomDropdown.addItem(entry.getKey()); // Add classroom to dropdown
            }
        }
    }


    private void showHumanReservationOptions() {
        JPanel humanReservationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        humanReservationPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Human-Based Reservation Options", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        humanReservationPanel.add(titleLabel, gbc);

        String[] humanReservations = {
                "Career Services",
                "Peer Tutoring",
                "Counselling Services",
                "Office Hours"
        };

        JComboBox<String> humanReservationDropdown = new JComboBox<>(humanReservations);
        humanReservationDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        humanReservationPanel.add(humanReservationDropdown, gbc);

        JButton nextButton = new JButton("Go");
        nextButton.addActionListener(e -> {
            String selectedOption = (String) humanReservationDropdown.getSelectedItem();
            handleHumanReservationSelection(selectedOption);
        });
        gbc.gridy = 2;
        humanReservationPanel.add(nextButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "bookingSelection"));
        gbc.gridy = 3;
        humanReservationPanel.add(backButton, gbc);

        cardPanel.add(humanReservationPanel, "humanReservationOptions");
        cardLayout.show(cardPanel, "humanReservationOptions");
    }

    private void handleHumanReservationSelection(String selectedOption) {
        switch (selectedOption) {
            case "Career Services":
                showCareerServicesOptions(); // Opens Career Services options
                break;
            case "Peer Tutoring":
                openURL(PeerTutoring.PEER_TUTORING_URL); // Opens Peer Tutoring URL
                break;
            case "Counselling Services":
                openURL(Counselling.COUNSELLING_URL); // Opens Counselling Services URL
                break;
            case "Office Hours":
                showOfficeHoursOptions(); // Displays office hours options
                break;
        }
    }


    private void showOfficeHoursOptions() {
        JPanel officeHoursPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        officeHoursPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Office Hours Booking", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; officeHoursPanel.add(titleLabel, gbc);

        // Dropdown for courses
        String[] courses = OfficeHours.getCourseFacultyMap().keySet().toArray(new String[0]);
        JComboBox<String> courseDropdown = new JComboBox<>(courses);
        courseDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; officeHoursPanel.add(courseDropdown, gbc);

        // Dropdown for lecturers
        JComboBox<String> lecturerDropdown = new JComboBox<>();
        lecturerDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2; officeHoursPanel.add(lecturerDropdown, gbc);

        // Listener for course dropdown selection
        courseDropdown.addActionListener(e -> {
            String selectedCourse = (String) courseDropdown.getSelectedItem();
            updateLecturerDropdown(selectedCourse, lecturerDropdown);
        });

        // Book Button
        JButton bookButton = new JButton("Book");
        bookButton.addActionListener(e -> {
            String selectedCourse = (String) courseDropdown.getSelectedItem();
            String selectedLecturer = (String) lecturerDropdown.getSelectedItem();
            handleOfficeHoursBooking(selectedCourse, selectedLecturer);
        });
        gbc.gridy = 3; officeHoursPanel.add(bookButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "humanReservationOptions"));
        gbc.gridy = 4; officeHoursPanel.add(backButton, gbc);

        cardPanel.add(officeHoursPanel, "officeHoursBooking");
        cardLayout.show(cardPanel, "officeHoursBooking");
    }

    private void updateLecturerDropdown(String selectedCourse, JComboBox<String> lecturerDropdown) {
        lecturerDropdown.removeAllItems();


        List<String> facultyList = OfficeHours.getCourseFacultyMap().get(selectedCourse);

        //If there are faculty members for the selected course, add them to the dropdown
        if (facultyList != null && !facultyList.isEmpty()) {
            for (String faculty : facultyList) {
                lecturerDropdown.addItem(faculty);
            }
        } else {
            lecturerDropdown.addItem("No faculty assigned");
        }
    }


    private void handleOfficeHoursBooking(String selectedCourse, String selectedLecturer) {
        // Check if the selected lecturer matches a specific faculty member and redirect accordingly
        if ("Sussan Einakian".equals(selectedLecturer)) {
            openLink("https://calpoly.zoom.us/j/83854958595");
        } else if ("Elena V. Rosca".equals(selectedLecturer)) {
            openLink("https://outlook.office.com/bookwithme/user/a4ce7d45f25b426795db7b3401eed88d%40ashesi.edu.gh?anonymous");
        } else {
            openLink("https://calendly.com");
        }
    }

    // Helper method to open the given URL in the browser
    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url)); // Opens the URL in the default web browser
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void showCareerServicesOptions() {
        JPanel careerServicesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        careerServicesPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Career Services", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        careerServicesPanel.add(titleLabel, gbc);

        // Dropdown for selecting year group
        String[] yearGroups = {"2028", "2027", "2026", "2025"};
        JComboBox<String> yearGroupDropdown = new JComboBox<>(yearGroups);
        yearGroupDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        careerServicesPanel.add(yearGroupDropdown, gbc);

        // Dropdown for selecting faculty
        JComboBox<String> facultyDropdown = new JComboBox<>();
        facultyDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        careerServicesPanel.add(facultyDropdown, gbc);

        // Listener for year group dropdown selection
        yearGroupDropdown.addActionListener(e -> {
            String selectedYearGroup = (String) yearGroupDropdown.getSelectedItem();
            updateFacultyDropdown(Integer.parseInt(selectedYearGroup), facultyDropdown);
        });

        //Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String selectedFaculty = (String) facultyDropdown.getSelectedItem();
            handleFacultySelection(selectedFaculty);
        });
        gbc.gridy = 3;
        careerServicesPanel.add(submitButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "bookingSelection"));
        gbc.gridy = 4;
        careerServicesPanel.add(backButton, gbc);

        cardPanel.add(careerServicesPanel, "careerServicesOptions");
        cardLayout.show(cardPanel, "careerServicesOptions");
    }

    // Helper method to update the faculty dropdown based on the selected year group
    private void updateFacultyDropdown(int selectedYearGroup, JComboBox<String> facultyDropdown) {
        facultyDropdown.removeAllItems(); // Clear existing items
        String facultyName = CareerServices.getFacultyForYearGroup(selectedYearGroup);
        facultyDropdown.addItem(facultyName); // Add the faculty member to the dropdown
    }

    // In your faculty selection handler
    private void handleFacultySelection(String selectedFaculty) {
        String url = "";
        if (selectedFaculty.equals("Nana Afua Anoff")) {
            url = "https://calendly.com/nana-afua-anoff/30min";
        } else if (selectedFaculty.equals("Alberta Awura Adjoa Asiamah")) {
            url = "https://calendly.com/aamankwa-2026";
        }
        openURL(url);

        // Wait for a brief time before showing the feedback panel
        Timer timer = new Timer(3000, e -> showFeedbackPanel());
        timer.setRepeats(false);
        timer.start();
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFeedbackPanel() {
        JPanel feedbackPanel = createFeedbackPanel();
        cardPanel.add(feedbackPanel, "Summary and Feedback");
        cardLayout.show(cardPanel, "Summary and Feedback");
    }

    private JPanel createFeedbackPanel() {
        JPanel feedbackPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        feedbackPanel.setBackground(Color.decode("#ad3537"));

        //Title Label for the Feedback Section
        JLabel feedbackTitleLabel = new JLabel("We value your feedback. Let us know what worked and what didn't", JLabel.CENTER);
        feedbackTitleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        feedbackTitleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        feedbackPanel.add(feedbackTitleLabel, gbc);

        // Feedback Text Area
        JTextArea feedbackTextArea = new JTextArea(5, 30);
        feedbackTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackTextArea.setWrapStyleWord(true);
        feedbackTextArea.setLineWrap(true);
        feedbackTextArea.setForeground(Color.BLACK);
        feedbackTextArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
        gbc.gridy = 1;
        feedbackPanel.add(scrollPane, gbc);

        //Submit Button
        JButton nextButton = new JButton("Submit");
        nextButton.addActionListener(e -> {
            String feedback = feedbackTextArea.getText().trim();

            if (feedback.isEmpty()) {
                showThankYouMessageAfterRedirection();
            } else {
                System.out.println("Feedback: " + feedback);
                showThankYouMessageAfterRedirection();
            }
        });
        gbc.gridy = 2;
        feedbackPanel.add(nextButton, gbc);

        return feedbackPanel;
    }

    private void showThankYouMessageAfterRedirection() {
        // Redirect the user to the site (replace this with actual redirection logic)
        openLink("https://your-redirection-link.com");

        // After a small delay, show the "Thank You" message
        Timer timer = new Timer(2000, e -> showThankYouMessage()); // Delay 2 seconds before showing the message
        timer.setRepeats(false);
        timer.start();
    }

    private void showThankYouMessage() {
        JPanel thankYouPanel = new JPanel(new BorderLayout());
        thankYouPanel.setBackground(Color.decode("#ad3537"));

        JLabel thankYouLabel = new JLabel("Thank you for using our service!", JLabel.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 20));
        thankYouLabel.setForeground(Color.WHITE);
        thankYouPanel.add(thankYouLabel, BorderLayout.CENTER);
        cardPanel.add(thankYouPanel, "thankYou");
        cardLayout.show(cardPanel, "thankYou");
    }

}