public class Deadline extends Task {
    protected String by;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.by = date;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), super.getTaskName(), this.getBy());
    }
}
