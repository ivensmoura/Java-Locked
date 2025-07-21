/**
 * An interface for minigame panels.
 * It ensures that any minigame has a defined start and stop method,
 * which allows the main application to manage its lifecycle when switching views.
 */
public interface Minigame {
    /**
     * Contains the logic to start the minigame (e.g., start the clock, initialize resources).
     */
    void start();

    /**
     * Contains the logic to stop the minigame (e.g., stop the clock, save state).
     */
    void stop();
}
