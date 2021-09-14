package task;

public class Event extends Task {
    protected String at;

    public Event(String taskName, String date) {
        super(taskName);
        this.at = date;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String saveToFileStringFormat(){
        return String.format("E|%s|%s|%s", isDone ? "1" : "0", this.getTaskName(), this.getAt());
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", super.getStatusIcon(), super.getTaskName(), this.getAt());
    }
}
