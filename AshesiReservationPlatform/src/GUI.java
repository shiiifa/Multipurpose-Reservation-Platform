import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {
        this.setTitle("Ashesi Student Information System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750, 750);
        this.setResizable(false);
        this.setVisible(true);

        this.getContentPane().setBackground(Color.pink);

        ImageIcon logo = new ImageIcon("logic.jpg");

        if (logo.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
            System.out.println("Image could not be loaded");
        }

        Image image = logo.getImage();
        Image scaledImage = image.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon currentLogo = new ImageIcon(scaledImage);

        JLabel label = new JLabel();
        label.setText("Ashesi Student Information System");
        label.setIcon(currentLogo);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(Color.white);
        label.setFont(new Font("Georgia bold", Font.PLAIN, 18));

        this.add(label);
        this.setLayout(new FlowLayout());
        this.revalidate();
        this.repaint();
    }
}

