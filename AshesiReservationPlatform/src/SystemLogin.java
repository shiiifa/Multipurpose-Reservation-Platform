import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemLogin extends JFrame {
    private JTextField usernameField;
    private JTextField userIDField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JCheckBox showPasswordCheckBox;
    private Identity currentUser;  // Identity object to store user details

    public SystemLogin() {
        // Frame setup
        this.setTitle("Ashesi Multipurpose Reservation System");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.getContentPane().setBackground(Color.decode("#ad3537"));

        // Custom Font
        Font customFont = new Font("Arial", Font.BOLD, 14);

        // Header Section with Logo and Title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#ad3537"));
        headerPanel.setLayout(new BorderLayout());

        // Ashesi Logo - Resized to half
        ImageIcon logo = new ImageIcon("ashesi.png");
        Image img = logo.getImage();
        Image scaledImg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH); // Reduced size to 75x75
        logo = new ImageIcon(scaledImg);
        JLabel logoLabel = new JLabel(logo);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Header Title in all caps
        JLabel headerLabel = new JLabel("ASHESI MULTIPURPOSE RESERVATION SYSTEM", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(headerPanel, gbc);

        // Username Field
        usernameField = createTextField("Username");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(usernameField, gbc);

        // User ID Field
        userIDField = createTextField("User ID");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(userIDField, gbc);

        // Email Field
        emailField = createTextField("Email");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(emailField, gbc);

        // Password Field
        passwordField = createPasswordField("Password");
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(passwordField, gbc);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(customFont);
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.setBackground(Color.decode("#731e26"));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(showPasswordCheckBox, gbc);

        // Login Button
        loginButton = new JButton("Login/Sign Up");
        styleButton(loginButton); // Style button
        loginButton.setPreferredSize(new Dimension(140, 40)); // Increase button size
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty() || emailField.getText().isEmpty() || userIDField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Oops", JOptionPane.ERROR_MESSAGE);
            } else {
                // Create Identity object with user data
                int userID = Integer.parseInt(userIDField.getText());
                currentUser = new Identity(username, userID, emailField.getText(), password);
                // Call method for handling login/signup (backend)
                handleUserAuthentication(currentUser);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(loginButton, gbc);

        // Reset Button
        resetButton = new JButton("Reset");
        styleButton(resetButton); // Style button
        resetButton.setPreferredSize(new Dimension(140, 40));
        resetButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            userIDField.setText("");
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(resetButton, gbc);

        // Footer Section
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.decode("#ad3537"));
        JLabel footerLabel = new JLabel("©2024 Bradley | Edinam | Shifa for Ashesi University", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));  // Smaller font, normal weight
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        this.add(footerPanel, gbc);

        // Frame visibility
        this.setVisible(true);
    }

    // Helper method for creating text fields with placeholders
    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        textField.setPreferredSize(new Dimension(180, 30));
        textField.setText(placeholder);
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

    // Method to create password field with placeholder
    private JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField.setPreferredSize(new Dimension(180, 30));
        passwordField.setText(placeholder);  // Set placeholder text
        passwordField.setForeground(Color.GRAY);  // Placeholder text color

        // Add focus listener to handle placeholder behavior
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                // If the placeholder text is shown, clear the field
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);  // Text color when user starts typing
                }
            }

            public void focusLost(FocusEvent e) {
                // If the field is empty, show the placeholder text again
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);  // Placeholder text color
                }
            }
        });

        return passwordField;
    }

    // Helper method to style buttons
    private void styleButton(JButton button) {
        Font customFont = new Font("Arial", Font.BOLD, 14);
        button.setFont(customFont);
        button.setBackground(Color.decode("#731e26"));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
    }

    // Backend method to handle login/signup process
    private void handleUserAuthentication(Identity user) {
        // Here, you would integrate the actual backend authentication logic
        // For now, let's print the user's data for testing
        System.out.println("User authenticated: " + user.getUserName());

        // Proceed to next stage: select booking type
        proceedToBookingSelection();
    }

    // Method to move to the next stage (booking selection)
    private void proceedToBookingSelection() {
        this.getContentPane().removeAll();  // Clear the current content
        this.setLayout(new GridBagLayout());  // Reset layout
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Section
        JLabel titleLabel = new JLabel("Select Reservation Type", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(titleLabel, gbc);

        // Reservation Type Dropdown
        String[] reservationTypes = {"Human Reservation", "Remote Reservation"};
        JComboBox<String> reservationTypeDropdown = new JComboBox<>(reservationTypes);
        reservationTypeDropdown.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(reservationTypeDropdown, gbc);

        // Next Button for selection
        JButton nextButton = new JButton("Next");
        styleButton(nextButton);
        nextButton.addActionListener(e -> {
            String selectedType = (String) reservationTypeDropdown.getSelectedItem();
            if (selectedType.equals("Human Reservation")) {
                showHumanReservationOptions();
            } else if (selectedType.equals("Remote Reservation")) {
                showRemoteReservationOptions();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(nextButton, gbc);

        this.revalidate();
        this.repaint();
    }

    // Method to display Human Reservation options
    private void showHumanReservationOptions() {
        this.getContentPane().removeAll();  // Clear current content
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Section
        JLabel titleLabel = new JLabel("Select Human Reservation", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);

        // Human Reservation Dropdown
        String[] humanOptions = {"Peer Tutoring Services", "Counselling Services", "Office Hours", "Career Services"};
        JComboBox<String> humanDropdown = new JComboBox<>(humanOptions);
        humanDropdown.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(humanDropdown, gbc);

        JButton nextButton = new JButton("Next");
        styleButton(nextButton);
        nextButton.addActionListener(e -> {
            String selectedOption = (String) humanDropdown.getSelectedItem();
            JOptionPane.showMessageDialog(this, "You selected: " + selectedOption);
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(nextButton, gbc);

        this.revalidate();
        this.repaint();
    }

    // Method to display Remote Reservation options
    private void showRemoteReservationOptions() {
        this.getContentPane().removeAll();  // Clear current content
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Section
        JLabel titleLabel = new JLabel("Select Remote Reservation", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);

        // Remote Reservation Dropdown
        String[] remoteOptions = {"Seminar Room Booking", "Classroom Booking", "Housing Selection"};
        JComboBox<String> remoteDropdown = new JComboBox<>(remoteOptions);
        remoteDropdown.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(remoteDropdown, gbc);

        JButton nextButton = new JButton("Next");
        styleButton(nextButton);
        nextButton.addActionListener(e -> {
            String selectedOption = (String) remoteDropdown.getSelectedItem();
            JOptionPane.showMessageDialog(this, "You selected: " + selectedOption);
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(nextButton, gbc);

        this.revalidate();
        this.repaint();
    }
}
