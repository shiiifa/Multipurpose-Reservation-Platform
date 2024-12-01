import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        String[] reservationTypes = {"Human Reservation", "Remote Reservation"};
        JComboBox<String> reservationTypeDropdown = new JComboBox<>(reservationTypes);
        reservationTypeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; panel.add(reservationTypeDropdown, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            String selectedType = (String) reservationTypeDropdown.getSelectedItem();
            if (selectedType.equals("Human Reservation")) {
                showHumanReservationOptions();
            } else if (selectedType.equals("Remote Reservation")) {
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
        JPanel humanReservationPanel = createHumanReservationPanel();
        cardPanel.add(humanReservationPanel, "humanReservation");
        cardLayout.show(cardPanel, "humanReservation");
    }

    private JPanel createHumanReservationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Select Human Reservation Option", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(titleLabel, gbc);

        String[] humanReservationOptions = {"Peer Tutoring", "Counseling", "Office Hours", "Career Services"};
        JComboBox<String> humanReservationDropdown = new JComboBox<>(humanReservationOptions);
        humanReservationDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; panel.add(humanReservationDropdown, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> proceedToBookingSummary((String) humanReservationDropdown.getSelectedItem(), "person"));
        gbc.gridy = 2; panel.add(nextButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "bookingSelection"));
        gbc.gridy = 3; panel.add(backButton, gbc);

        return panel;
    }

    private void showRemoteReservationOptions() {
        JPanel remoteReservationPanel = createRemoteReservationPanel();
        cardPanel.add(remoteReservationPanel, "remoteReservation");
        cardLayout.show(cardPanel, "remoteReservation");
    }

    private JPanel createRemoteReservationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Select Remote Reservation Option", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(titleLabel, gbc);

        String[] remoteReservationOptions = {"Remote Tutoring", "Remote Counseling", "Virtual Office Hours"};
        JComboBox<String> remoteReservationDropdown = new JComboBox<>(remoteReservationOptions);
        remoteReservationDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1; panel.add(remoteReservationDropdown, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> proceedToBookingSummary((String) remoteReservationDropdown.getSelectedItem(), null));
        gbc.gridy = 2; panel.add(nextButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "bookingSelection"));
        gbc.gridy = 3; panel.add(backButton, gbc);

        return panel;
    }

    private void proceedToBookingSummary(String selectedOption, String person) {
        JPanel bookingSummaryPanel = createBookingSummaryPanel(selectedOption, person);
        cardPanel.add(bookingSummaryPanel, "bookingSummary");
        cardLayout.show(cardPanel, "bookingSummary");
    }

    private JPanel createBookingSummaryPanel(String selectedOption, String person) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.decode("#ad3537"));

        JLabel titleLabel = new JLabel("Booking Summary", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(titleLabel, gbc);

        String summary = "You have selected: " + selectedOption;
        if (person != null) {
            summary += "\nPerson: " + person;
        }
        JLabel summaryLabel = new JLabel(summary, JLabel.CENTER);
        summaryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        summaryLabel.setForeground(Color.WHITE);
        gbc.gridy = 1; panel.add(summaryLabel, gbc);

        // Confirm Booking Button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Booking Confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE));
        gbc.gridy = 2; panel.add(confirmButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            if (person == null) {
                cardLayout.show(cardPanel, "bookingSelection");
            } else {
                cardLayout.show(cardPanel, "humanReservation");
            }
        });
        gbc.gridy = 3; panel.add(backButton, gbc);

        return panel;
    }
}
