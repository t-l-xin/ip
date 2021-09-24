package task;


/**
 * Represents a general Task. Contains the details and behaviour of the Task.
 */
public class Task {

    protected String taskName;
    protected boolean isDone;

    /**
     * Initialises Task object using task name, and set status to not done by default.
     *
     * @param taskName The task name of Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * @return A formatted string that can be saved to a file.
     */
    public String saveToFileStringFormat() {
        return String.format("A|%s|%s", isDone ? "1" : "0", this.getTaskName());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s ", this.getStatusIcon(), this.getTaskName());
    }
}
