import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

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

        // First screen (login form)
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
        gbc.gridy = 20; // Position it at the bottom
        panel.add(footerLabel, gbc);

        // Login Button
        JButton loginButton = new JButton("Login/Sign Up");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty() || emailField.getText().isEmpty() || userIDField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Oops", JOptionPane.ERROR_MESSAGE);
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

        String[] reservationTypes = {"Human Reservations", "Remote Reservations"};
        JComboBox<String> reservationTypeDropdown = new JComboBox<>(reservationTypes);
        reservationTypeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panel.add(reservationTypeDropdown, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            String selectedType = (String) reservationTypeDropdown.getSelectedItem();
            if (selectedType.equals("Human Reservations")) {
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
    }

    private void showHumanReservationOptions() {
        JPanel humanReservationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        humanReservationPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Human Reservation Options", JLabel.CENTER);
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
                showCareerServicesOptions();
                break;
            case "Peer Tutoring":
                openURL("https://bookingsite-28132.web.app/");
                break;
            case "Counselling Services":
                openURL("https://ashesicounsellingunit.simplybook.me/");
                break;
            case "Office Hours":
                showOfficeHoursOptions();
                break;
        }
    }

    private void showOfficeHoursOptions() {
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

        // Dropdown for selecting faculty (initially empty)
        JComboBox<String> facultyDropdown = new JComboBox<>();
        facultyDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        careerServicesPanel.add(facultyDropdown, gbc);

        // Listener for year group dropdown selection
        yearGroupDropdown.addActionListener(e -> {
            String selectedYearGroup = (String) yearGroupDropdown.getSelectedItem();
            updateFacultyDropdown(Integer.parseInt(selectedYearGroup), facultyDropdown);
        });

        // Submit button
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

        // Redirect to the selected URL
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

        // Title Label for the Feedback Section
        JLabel feedbackTitleLabel = new JLabel("We value your feedback", JLabel.CENTER);
        feedbackTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
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

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            String feedback = feedbackTextArea.getText().trim();

            if (feedback.isEmpty()) {
                // If no feedback provided, simply show the final message
                showThankYouMessage();
            } else {
                // Process feedback (e.g., save or send feedback)
                System.out.println("Feedback: " + feedback);
                showThankYouMessage();
            }
        });
        gbc.gridy = 2;
        feedbackPanel.add(nextButton, gbc);

        return feedbackPanel;
    }

    private void showThankYouMessage() {
        JPanel thankYouPanel = new JPanel(new BorderLayout());
        thankYouPanel.setBackground(Color.decode("#ad3537"));

        JLabel thankYouLabel = new JLabel("Thank you for using our service!", JLabel.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 20));
        thankYouLabel.setForeground(Color.WHITE);
        thankYouPanel.add(thankYouLabel, BorderLayout.CENTER);

        // Add the Thank You Panel to the card panel and display it
        cardPanel.add(thankYouPanel, "thankYou");
        cardLayout.show(cardPanel, "thankYou");
    }
}