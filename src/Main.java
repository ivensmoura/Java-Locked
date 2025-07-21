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
            mainPanel.setBackground(new Color(0, 0, 0)); // A light-gray background
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

            // --- Clock Integration ---
            // 1. Create an instance of the GameClock.
            GameClock clock = new GameClock();

            // 2. Schedule a task. This is an example of an action that runs every 2 ticks.
            //    We are using a lambda expression for the Runnable action.
            clock.scheduleTask(2, () -> {
                System.out.println("This message appears every 2 ticks!");
            });

            // 3. Schedule another task, for example, every 5 ticks.
            clock.scheduleTask(5, () -> {
                System.out.println("---------- This is a 5-tick event! ----------");
            });

            // 4. Start the clock. It will now begin ticking.
            clock.start();
        });
    }
}
