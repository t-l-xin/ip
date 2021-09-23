package task;

/**
 * Represents Event Task. Contains the details and behaviour of the event task.
 */
import manager.ParseManager;

public class Event extends Task {
    protected String at;

    /**
     * Initialises Event Task object using task name, and set the date of the event.
     *
     * @param taskName The task name of Event task.
     * @param date The date of Event task.
     */
    public Event(String taskName, String date) {
        super(taskName);
        this.at = date;
    }

    public String getAt() {
        return at;
    }

    /**
     * @return A formatted string that can be saved to a file.
     */
    public String getPrintAt() {
        return ParseManager.parseDateTimeStringForOutput(at);
    }

    @Override
    public String saveToFileStringFormat(){
        return String.format("E|%s|%s|%s", isDone ? "1" : "0", this.getTaskName(), this.getAt());
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", super.getStatusIcon(), super.getTaskName(), this.getPrintAt());
    }
}
