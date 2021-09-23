package task;

/**
 * Represents a Todo task. Contains the details and behaviour of the Todo task.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo task Object
     *
     * @param taskName the task name from parsed input
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * @return a formatted string different from toString() that can be saved to a file
     */
    @Override
    public String saveToFileStringFormat(){
        return String.format("T|%s|%s", isDone ? "1" : "0", this.getTaskName());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), super.getTaskName());
    }
}
