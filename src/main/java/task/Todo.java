package task;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String saveToFileStringFormat(){
        return String.format("T|%s|%s", isDone ? "1" : "0", this.getTaskName());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), super.getTaskName());
    }
}
