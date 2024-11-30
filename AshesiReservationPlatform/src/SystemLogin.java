import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemLogin extends JFrame {
    private JTextField usernameField;
    private JTextField studentIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JCheckBox showPasswordCheckBox;

    public SystemLogin() {
        // Frame setup
        this.setTitle("Ashesi Multipurpose Reservation System");
        this.setSize(400, 350);  // Increased height to accommodate logo
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Center the window on the screen

        // Use GridBagLayout for centering components
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.getContentPane().setBackground(Color.decode("#ad3537")); // Background color

        // Font for labels and buttons
        Font customFont = new Font("Arial", Font.BOLD, 14);

        // Add Logo at the top
        JLabel logoLabel = new JLabel();
        ImageIcon logo = new ImageIcon("ashesi.png"); // Replace with the correct path to your logo

        // Scale the logo to a smaller size
        Image img = logo.getImage(); // Transform ImageIcon to Image
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Resize image
        logo = new ImageIcon(scaledImg); // Convert back to ImageIcon

        logoLabel.setIcon(logo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across 2 columns
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing around the components
        gbc.anchor = GridBagConstraints.CENTER; // Center the logo
        this.add(logoLabel, gbc);

        // Username Field
        usernameField = createTextField("Username");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing around the components
        gbc.anchor = GridBagConstraints.WEST;
        this.add(usernameField, gbc);

        // Student ID Field
        studentIDField = createTextField("ID");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(studentIDField, gbc);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(customFont);
        passwordField.setPreferredSize(new Dimension(180, 30)); // Increased width and height
        passwordField.setEchoChar('•'); // Hide password initially
        setPasswordFieldPlaceholder(passwordField, "Password");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(passwordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(customFont);
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.setBackground(Color.decode("#731e26")); // Match background
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('•'); // Hide password
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(showPasswordCheckBox, gbc);

        // Login Button
        loginButton = new JButton("Login/Sign Up");
        styleButton(loginButton); // Style button
        loginButton.setPreferredSize(new Dimension(120, 40)); // Increase button size
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Username: " + username); // Replace with proper validation logic
                System.out.println("Password: " + password);
                new IdentityPanel(username); // Pass username to the next panel
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Center the button across both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        this.add(loginButton, gbc);

        // Reset Button
        resetButton = new JButton("Reset");
        styleButton(resetButton); // Style button
        resetButton.setPreferredSize(new Dimension(120, 40)); // Increase button size
        resetButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Center the reset button across both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the reset button
        this.add(resetButton, gbc);

        // Frame visibility
        this.setVisible(true);
    }

    // Method to create text fields with placeholder behavior
    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        textField.setPreferredSize(new Dimension(180, 30)); // Adjust width for better appearance

        // Set placeholder text
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY); // Placeholder color
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Set text color to black when typing
                }
            }
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY); // Restore placeholder text color
                }
            }
        });
        return textField;
    }

    // Method to set password field placeholder
    private void setPasswordFieldPlaceholder(JPasswordField passwordField, String placeholder) {
        passwordField.setEchoChar((char) 0); // No masking initially for the placeholder
        passwordField.setText(placeholder);
        passwordField.setForeground(Color.GRAY); // Placeholder color
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK); // Set text color to black when typing
                    passwordField.setEchoChar('•'); // Mask password
                }
            }
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY); // Restore placeholder text color
                    passwordField.setEchoChar((char) 0); // Unmask placeholder
                }
            }
        });
    }

    // Round edges with white font for buttons
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.decode("#731e26")); // Match theme
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }
}
