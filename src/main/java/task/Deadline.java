package task;

import manager.ParseManager;

public class Deadline extends Task {
    protected String by;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.by = date;
    }

    public String getBy() {
        return by;
    }

    public String getPrintBy() {
        return ParseManager.parseDateTimeStringForOutput(by);
    }

    @Override
    public String saveToFileStringFormat(){
        return String.format("D|%s|%s|%s", isDone ? "1" : "0", this.getTaskName(), this.getBy());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), super.getTaskName(), this.getPrintBy());
    }
}
