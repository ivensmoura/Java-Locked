import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A tick-based clock that can schedule and execute tasks at specified intervals.
 * The clock runs on a Swing Timer, making it safe to interact with Swing components.
 */
public class GameClock {

    private static final int TICK_DURATION_MS = 600; // 0.6 seconds per tick

    private final Timer timer;
    private final AtomicLong currentTick;
    private final List<ScheduledTask> scheduledTasks;

    /**
     * Represents a task scheduled to run at a specific tick interval.
     * This is a private inner class as it's only used by the GameClock.
     */
    private static class ScheduledTask {
        private final int interval; // How often the task should run (e.g., every 2 ticks)
        private final Runnable action; // The code to execute

        public ScheduledTask(int interval, Runnable action) {
            if (interval <= 0) {
                throw new IllegalArgumentException("Interval must be positive.");
            }
            this.interval = interval;
            this.action = action;
        }

        public int getInterval() {
            return interval;
        }

        public Runnable getAction() {
            return action;
        }
    }

    /**
     * Constructs a new GameClock.
     * The clock is initially stopped and has no scheduled tasks.
     */
    public GameClock() {
        this.currentTick = new AtomicLong(0);
        this.scheduledTasks = new ArrayList<>();

        // The main ActionListener for the timer. This is the heart of the clock.
        // It's called every time a "tick" occurs.
        ActionListener tickListener = e -> {
            long tick = currentTick.incrementAndGet();
            executeTasks(tick);
        };

        // The Swing Timer will fire an event every TICK_DURATION_MS milliseconds.
        this.timer = new Timer(TICK_DURATION_MS, tickListener);
    }

    /**
     * Schedules a new action to be executed at a regular interval.
     *
     * @param interval The tick interval for this action (e.g., 2 for every 2 ticks).
     * @param action   The action to be performed, provided as a Runnable.
     */
    public void scheduleTask(int interval, Runnable action) {
        this.scheduledTasks.add(new ScheduledTask(interval, action));
    }

    /**
     * Starts the clock. Ticks will begin, and scheduled tasks will be executed.
     */
    public void start() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    /**
     * Stops the clock. Ticks will cease.
     */
    public void stop() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    /**
     * Gets the current tick count of the clock.
     *
     * @return The number of ticks that have passed since the clock started.
     */
    public long getCurrentTick() {
        return currentTick.get();
    }

    /**
     * Executes all tasks whose interval aligns with the current tick.
     *
     * @param tick The current tick number.
     */
    private void executeTasks(long tick) {
        System.out.println("Tick: " + tick); // Log every tick for debugging
        for (ScheduledTask task : scheduledTasks) {
            if (tick % task.getInterval() == 0) {
                // The task's interval is a divisor of the current tick, so we run the action.
                try {
                    task.getAction().run();
                } catch (Exception e) {
                    System.err.println("Error executing scheduled task for tick " + tick);
                    e.printStackTrace();
                }
            }
        }
    }
}
