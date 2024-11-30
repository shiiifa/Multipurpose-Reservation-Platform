import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

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

    // Persistent user data storage
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

        Font customFont = new Font("Arial", Font.BOLD, 14);

        JLabel logoLabel = new JLabel();
        ImageIcon logo = new ImageIcon("ashesi.png");

        Image img = logo.getImage();
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logo = new ImageIcon(scaledImg);

        logoLabel.setIcon(logo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(logoLabel, gbc);

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
        loginButton.setPreferredSize(new Dimension(120, 40)); // Increase button size
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
                    showBookingSystem();
                } else {
                    // New user, register and save their details
                    currentUser = new User(username, password, emailField.getText(), studentIDField.getText());
                    userDatabase.put(username, currentUser);
                    saveUserDatabase();
                    System.out.println("User registered: " + username);
                    showBookingSystem();
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
        resetButton.setPreferredSize(new Dimension(120, 40));
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

    // Method to set password field placeholder
    private void setPasswordFieldPlaceholder(JPasswordField passwordField, String placeholder) {
        passwordField.setEchoChar((char) 0);
        passwordField.setText(placeholder);
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
    }

    // Method to display the service booking form and feedback
    private void showBookingSystem() {
        String[] services = {"Human Reservations", "Remote Reservations"};
        serviceComboBox = new JComboBox<>(services);

        servicePanel = new JPanel();
        servicePanel.setLayout(new BorderLayout());
        servicePanel.add(serviceComboBox, BorderLayout.NORTH);

        serviceComboBox.addActionListener(e -> showRelevantBookingForm((String) serviceComboBox.getSelectedItem()));

        JButton submitButton = new JButton("Next");
        submitButton.addActionListener(e -> processBooking());
        servicePanel.add(submitButton, BorderLayout.SOUTH);

        this.getContentPane().removeAll();
        this.getContentPane().add(servicePanel);
        this.revalidate();
        this.repaint();
    }

    // Method to show relevant booking form based on selected service
    private void showRelevantBookingForm(String selectedService) {
        JPanel bookingFormPanel = new JPanel();
        bookingFormPanel.setLayout(new BoxLayout(bookingFormPanel, BoxLayout.Y_AXIS));

        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);
        bookingFormPanel.add(new JScrollPane(feedbackArea));

        if (selectedService.equals("Human Reservations")) {
            feedbackArea.setText("Human reservation details: ...");
        } else {
            feedbackArea.setText("Remote reservation details: ...");
        }

        servicePanel.add(bookingFormPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    // Method to process booking
    private void processBooking() {
        feedbackArea.append("\nBooking completed successfully!");
    }

    // Method to load the user database from file
    private HashMap<String, User> loadUserDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            return (HashMap<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }

    // Method to save the user database to file
    private void saveUserDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(userDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Backend BookingSystem to handle authentication
    class BookingSystem {
        public User authenticateUser(String username, String password) {
            return userDatabase.get(username);
        }
    }

    // Backend User class
    class User implements Serializable {
        private String username;
        private String password;
        private String email;
        private String id;

        public User(String username, String password, String email, String id) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getId() {
            return id;
        }
    }
}
