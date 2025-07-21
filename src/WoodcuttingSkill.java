import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * Represents the Woodcutting minigame screen.
 * This panel is self-contained and manages its own GameClock.
 * It implements the Minigame interface to allow the Main class to start and stop it.
 */
public class WoodcuttingSkill extends JPanel implements Minigame {

    private GameClock gameClock;

    public WoodcuttingSkill() {
        // Set up the UI for the woodcutting minigame panel
        setBackground(new Color(34, 139, 34)); // A forest-green color
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Woodcutting Minigame", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.CENTER);
    }

    @Override
    public void start() {
        System.out.println("Woodcutting minigame started!");
        this.gameClock = new GameClock();

        // Schedule a task specific to the woodcutting game
        this.gameClock.scheduleTask(2, () -> {
            System.out.println("Debug every 2 ticks");
        });

        this.gameClock.start();
    }

    @Override
    public void stop() {
        if (this.gameClock != null) {
            this.gameClock.stop();
            System.out.println("Woodcutting minigame stopped and clock is halted.");
        }
    }
}
