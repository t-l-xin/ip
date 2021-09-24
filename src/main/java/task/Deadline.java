package task;

/**
 * Represents a Deadline Task. Contains the details and behaviour of the deadline task.
 */

import manager.ParseManager;

public class Deadline extends Task {
    protected String by;

    /**
     * Initialises Deadline Task object using task name, and set the deadline.
     *
     * @param taskName The task name of Deadline task.
     * @param date     The last date to complete Deadline task.
     */
    public Deadline(String taskName, String date) {
        super(taskName);
        this.by = date;
    }

    public String getBy() {
        return by;
    }

    /**
     * @return A formatted string that can be saved to a file.
     */
    public String getPrintBy() {
        return ParseManager.parseDateTimeStringForOutput(by);
    }

    @Override
    public String saveToFileStringFormat() {
        return String.format("D|%s|%s|%s", isDone ? "1" : "0", this.getTaskName(), this.getBy());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), super.getTaskName(), this.getPrintBy());
    }
}
