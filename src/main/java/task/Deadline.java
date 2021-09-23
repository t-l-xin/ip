package task;

/**
 * Represents a Deadline Task. Contains the details and behaviour of the deadline task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Initialises Deadline Task object using task name, and set the deadline.
     *
     * @param taskName task name of Deadline task.
     * @param date last date to complete Deadline task.
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
    @Override
    public String saveToFileStringFormat(){
        return String.format("D|%s|%s|%s", isDone ? "1" : "0", this.getTaskName(), this.getBy());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), super.getTaskName(), this.getBy());
    }
}
