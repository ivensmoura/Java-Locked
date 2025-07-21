import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    private static JFrame frame;
    private static JPanel currentPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Java Skills - Main Menu");
            // We handle the close operation manually to decide whether to exit or go to the menu.
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(800, 600);

            // Add a custom window listener to intercept the close event
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    boolean isMinigameActive = false;
                    // Check if the current panel is a container for a minigame
                    if (currentPanel != null) {
                        for (Component comp : currentPanel.getComponents()) {
                            if (comp instanceof Minigame) {
                                isMinigameActive = true;
                                break;
                            }
                        }
                    }

                    if (isMinigameActive) {
                        // If a minigame is active, closing the window returns to the main menu.
                        showMainMenu();
                    } else {
                        // If on the main menu, closing the window exits the application.
                        frame.dispose();
                        System.exit(0);
                    }
                }
            });

            // Start by showing the main menu
            showMainMenu();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    /**
     * Switches the content of the main frame to a new panel.
     * It intelligently finds and stops any running Minigame in the old panel
     * before starting a new one in the new panel.
     *
     * @param newPanel The new JPanel to display.
     */
    private static void switchPanel(JPanel newPanel) {
        // Stop the minigame in the current panel if it exists
        if (currentPanel != null) {
            // The minigame is a component of the currentPanel
            for (Component comp : currentPanel.getComponents()) {
                if (comp instanceof Minigame) {
                    ((Minigame) comp).stop();
                }
            }
        }

        // Swap the panels
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        currentPanel = newPanel;

        // Start the minigame in the new panel if it exists.
        // The minigame is a component of the newPanel
        for (Component comp : newPanel.getComponents()) {
            if (comp instanceof Minigame) {
                ((Minigame) comp).start();
            }
        }
    }

    /**
     * Creates and displays the main menu panel.
     */
    public static void showMainMenu() {
        frame.setTitle("Java Skills - Main Menu");

        // Main menu panel setup
        JPanel mainMenuPanel = new JPanel(new GridBagLayout());
        mainMenuPanel.setBackground(new Color(240, 240, 240));

        // "Woodcutting" button
        JButton woodcuttingButton = new JButton("Woodcutting Minigame");
        woodcuttingButton.setFont(new Font("Arial", Font.BOLD, 18));
        woodcuttingButton.addActionListener(e -> showSkillScreen(new WoodcuttingSkill()));

        mainMenuPanel.add(woodcuttingButton);
        switchPanel(mainMenuPanel);
    }

    /**
     * Creates a container for a skill minigame, adding a "Back" button,
     * and then switches to this new view.
     *
     * @param skillPanel The minigame panel (e.g., an instance of WoodcuttingSkill).
     */
    public static void showSkillScreen(JPanel skillPanel) {
        frame.setTitle("Java Skills - " + skillPanel.getClass().getSimpleName());

        // Create a container panel that holds the skill panel and a back button
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(skillPanel, BorderLayout.CENTER);

        switchPanel(containerPanel);
    }
}
