import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class SystemLogin extends JFrame {
    private JTextField usernameField;
    private JTextField studentIDField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JCheckBox showPasswordCheckBox;
    private JComboBox<String> serviceComboBox;
    private JPanel servicePanel;
    private JTextArea feedbackArea;

    // Backend attributes
    private User currentUser;
    private BookingSystem bookingSystem;
    private HashMap<String, User> userDatabase;

    public SystemLogin() {
        // Initialize the backend system
        bookingSystem = new BookingSystem();
        userDatabase = loadUserDatabase();

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

        // Student ID Field
        studentIDField = createTextField("ID");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(studentIDField, gbc);

        // Email Field
        emailField = createTextField("Email");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(emailField, gbc);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(customFont);
        passwordField.setPreferredSize(new Dimension(180, 30));
        passwordField.setEchoChar('•');
        setPasswordFieldPlaceholder(passwordField, "Password");
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
            if (username.isEmpty() || password.isEmpty() || emailField.getText().isEmpty() || studentIDField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Oops", JOptionPane.ERROR_MESSAGE);
            } else {
                // Check if user exists
                currentUser = bookingSystem.authenticateUser(username, password);
                if (currentUser != null) {
                    // User exists, load details
                    System.out.println("Welcome back, " + username);
                    showServiceSelection();
                } else {
                    // New user, register and save their details
                    currentUser = new User(username, password, emailField.getText(), studentIDField.getText());
                    userDatabase.put(username, currentUser);
                    saveUserDatabase();
                    System.out.println("User registered: " + username);
                    showServiceSelection();
                }
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
            studentIDField.setText("");
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

    // Method to show service selection dropdown
    private void showServiceSelection() {
        String[] services = {"Human Reservation", "Remote Reservation"};
        serviceComboBox = new JComboBox<>(services);
        serviceComboBox.setFont(new Font("Arial", Font.BOLD, 14));
        serviceComboBox.setBackground(Color.decode("#731e26"));
        serviceComboBox.setForeground(Color.WHITE);
        serviceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedService = (String) serviceComboBox.getSelectedItem();
                if (selectedService.equals("Human Reservation")) {
                    showHumanReservationOptions();
                } else if (selectedService.equals("Remote Reservation")) {
                    showRemoteReservationOptions();
                }
            }
        });

        // Add service combo box to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(serviceComboBox, gbc);
        this.revalidate();
        this.repaint();
    }

    // Method to show human reservation options
    private void showHumanReservationOptions() {
        String[] humanServices = {
                "Peer Tutoring Services",
                "Counselling Services",
                "Office Hours",
                "Career Services"
        };
        JComboBox<String> humanServiceComboBox = new JComboBox<>(humanServices);
        humanServiceComboBox.setFont(new Font("Arial", Font.BOLD, 14));
        humanServiceComboBox.setBackground(Color.decode("#731e26"));
        humanServiceComboBox.setForeground(Color.WHITE);
        humanServiceComboBox.addActionListener(e -> {
            String selectedOption = (String) humanServiceComboBox.getSelectedItem();
            handleHumanServiceSelection(selectedOption);
        });

        // Add human service combo box to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(humanServiceComboBox, gbc);
        this.revalidate();
        this.repaint();
    }

    // Method to handle selection of human services
    private void handleHumanServiceSelection(String selectedOption) {
        if (selectedOption.equals("Peer Tutoring Services")) {
            String courseName = JOptionPane.showInputDialog("Enter course name:");
            String studentName = JOptionPane.showInputDialog("Enter student name:");
            // Store peer tutoring reservation info (not implemented in this code)
        } else if (selectedOption.equals("Counselling Services")) {
            String counselorName = JOptionPane.showInputDialog("Enter counselor name:");
            // Store counselling reservation info (not implemented in this code)
        } else if (selectedOption.equals("Office Hours")) {
            String facultyName = JOptionPane.showInputDialog("Enter faculty name:");
            // Store office hours reservation info (not implemented in this code)
        } else if (selectedOption.equals("Career Services")) {
            String serviceDetail = JOptionPane.showInputDialog("Enter service detail:");
            // Store career service reservation info (not implemented in this code)
        }
    }

    // Method to show remote reservation options
    private void showRemoteReservationOptions() {
        // This will be a simple selection for remote services if needed
        String[] remoteServices = {
                "Online Study Groups",
                "Virtual Workshops",
                "Remote Office Hours"
        };
        JComboBox<String> remoteServiceComboBox = new JComboBox<>(remoteServices);
        remoteServiceComboBox.setFont(new Font("Arial", Font.BOLD, 14));
        remoteServiceComboBox.setBackground(Color.decode("#731e26"));
        remoteServiceComboBox.setForeground(Color.WHITE);
        remoteServiceComboBox.addActionListener(e -> {
            String selectedOption = (String) remoteServiceComboBox.getSelectedItem();
            handleRemoteServiceSelection(selectedOption);
        });

        // Add remote service combo box to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(remoteServiceComboBox, gbc);
        this.revalidate();
        this.repaint();
    }

    // Method to handle selection of remote services
    private void handleRemoteServiceSelection(String selectedOption) {
        if (selectedOption.equals("Online Study Groups")) {
            // Store online study group reservation info (not implemented)
        } else if (selectedOption.equals("Virtual Workshops")) {
            // Store virtual workshop reservation info (not implemented)
        } else if (selectedOption.equals("Remote Office Hours")) {
            // Store remote office hours reservation info (not implemented)
        }
    }

    // Utility method to create text fields with placeholders
    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
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

    // Utility method to set password field placeholder
    private void setPasswordFieldPlaceholder(JPasswordField passwordField, String placeholder) {
        passwordField.setText(placeholder);
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
    }

    // Utility method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.decode("#731e26"));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    // Method to load user database (from file or other storage)
    private HashMap<String, User> loadUserDatabase() {
        HashMap<String, User> database = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userDatabase.ser"))) {
            database = (HashMap<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions gracefully (no database yet)
        }
        return database;
    }

    // Method to save user database (to file or other storage)
    private void saveUserDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userDatabase.ser"))) {
            oos.writeObject(userDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String studentID;

    public User(String username, String password, String email, String studentID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.studentID = studentID;
    }

    // Getters and setters...
}

class BookingSystem {
    public User authenticateUser(String username, String password) {
        // This would normally involve checking the database for matching username and password
        return null;  // Returning null for simplicity
    }
}
