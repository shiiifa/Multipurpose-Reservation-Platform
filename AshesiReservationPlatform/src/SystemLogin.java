import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;

public class SystemLogin extends JFrame {
    private JTextField usernameField, userIDField, emailField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private Identity currentUser;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public SystemLogin() {
        // Frame setup
        this.setTitle("Ashesi Multipurpose Reservation System");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Custom Font
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

        gbc.gridx = 0; gbc.gridy = 0; panel.add(headerPanel, gbc);

        // Username Field
        usernameField = createTextField("Username");
        gbc.gridx = 0; gbc.gridy = 1; panel.add(usernameField, gbc);

        // User ID Field
        userIDField = createTextField("User ID");
        gbc.gridy = 2; panel.add(userIDField, gbc);

        // Email Field
        emailField = createTextField("Email");
        gbc.gridy = 3; panel.add(emailField, gbc);

        // Password Field
        passwordField = createPasswordField("Password");
        gbc.gridy = 4; panel.add(passwordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        gbc.gridy = 5; panel.add(showPasswordCheckBox, gbc);

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
        gbc.gridy = 6; panel.add(loginButton, gbc);

        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            userIDField.setText("");
        });
        gbc.gridy = 7; panel.add(resetButton, gbc);

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
        gbc.gridx = 0; gbc.gridy = 0; panel.add(titleLabel, gbc);

        String[] reservationTypes = {"Human Reservations", "Remote Reservations"};
        JComboBox<String> reservationTypeDropdown = new JComboBox<>(reservationTypes);
        reservationTypeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; panel.add(reservationTypeDropdown, gbc);

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
        gbc.gridy = 2; panel.add(nextButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "login"));
        gbc.gridy = 3; panel.add(backButton, gbc);

        return panel;
    }

    private void showHumanReservationOptions() {
        JPanel humanReservationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        humanReservationPanel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Human Reservation Options", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; humanReservationPanel.add(titleLabel, gbc);

        String[] humanReservations = {
                "Career Services",
                "Peer Tutoring",
                "Counselling Services",
                "Office Hours"
        };
        JComboBox<String> humanReservationDropdown = new JComboBox<>(humanReservations);
        humanReservationDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; humanReservationPanel.add(humanReservationDropdown, gbc);

        JButton nextButton = new JButton("Go");
        nextButton.addActionListener(e -> {
            String selectedOption = (String) humanReservationDropdown.getSelectedItem();
            handleHumanReservationSelection(selectedOption);
        });
        gbc.gridy = 2; humanReservationPanel.add(nextButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "bookingSelection"));
        gbc.gridy = 3; humanReservationPanel.add(backButton, gbc);

        cardPanel.add(humanReservationPanel, "humanReservationOptions");
        cardLayout.show(cardPanel, "humanReservationOptions");
    }

    private void handleHumanReservationSelection(String selectedOption) {
        String url = "";
        switch (selectedOption) {
            case "Career Services":
            case "Office Hours":
                url = "https://calendly.com";
                break;
            case "Peer Tutoring":
                url = "https://bookingsite-28132.web.app/";
                break;
            case "Counselling Services":
                url = "https://ashesicounsellingunit.simplybook.me/";
                break;
        }
        openURL(url);
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening URL: " + e.getMessage());
        }
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
        String url = "";
        switch (selectedOption) {
            case "Housing Selection":
                url = "https://www.ahs.mgmhubs.com/";
                break;
            case "Classroom Booking":
                // Handle Classroom Booking (additional logic if needed)
                url = "https://example.com"; // Placeholder URL for now
                break;
            case "Seminar Room Booking":
                String reservationPurpose = JOptionPane.showInputDialog(this, "Enter Reservation Purpose:");
                if (reservationPurpose != null && !reservationPurpose.isEmpty()) {
                    url = "https://warrenlibraryseminarroom.simplybook.me/v2/";
                }
                break;
        }
        if (!url.isEmpty()) openURL(url);
    }
}
