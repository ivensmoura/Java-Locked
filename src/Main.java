import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a new instance of our application's main frame.
            JFrame frame = new JFrame("Java Locked Main Window");

            // Set the default close operation.
            // EXIT_ON_CLOSE ensures that the application terminates when the window is closed.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JPanel to act as the main content pane.
            // This is where we will add other components later.
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(new Color(240, 240, 240)); // A light-gray background
            mainPanel.setPreferredSize(new Dimension(800, 600)); // Set a preferred size

            // Set the panel as the content pane of the frame.
            frame.setContentPane(mainPanel);

            // pack() causes the window to be sized to fit the preferred size
            // and layouts of its subcomponents.
            frame.pack();

            // Center the frame on the screen.
            frame.setLocationRelativeTo(null);

            // Make the frame visible to the user.
            frame.setVisible(true);
        });
    }
}