import javax.swing.*;
import java.awt.*;

public class IdentityPanel extends JFrame {
    public IdentityPanel(String username) {
        // Frame setup
        this.setTitle("Identity Panel");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome, " + username, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(welcomeLabel, BorderLayout.NORTH);

        // Identity Attribute Input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Student ID:"));
        JTextField studentIdField = new JTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        inputPanel.add(phoneField);

        this.add(inputPanel, BorderLayout.CENTER);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            if (studentId.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Student ID: " + studentId); // Replace with proper storage logic
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                JOptionPane.showMessageDialog(null, "Identity Attributes Submitted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.add(submitButton, BorderLayout.SOUTH);

        // Frame visibility
        this.setVisible(true);
    }
}
