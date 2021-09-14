package task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    // constructor
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    // getter and setter
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

    public String saveToFileStringFormat(){
        return String.format("A|%s|%s", isDone ? "1" : "0", this.getTaskName());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s ", this.getStatusIcon(), this.getTaskName());
    }
}
