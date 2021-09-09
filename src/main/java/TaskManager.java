public class TaskManager {
    public static final int MAX_TASKS_LIMIT = 100;
    public static final String BY_STRING = " /by ";
    public static final String AT_STRING = " /at ";

    private Task[] taskList = new Task[MAX_TASKS_LIMIT];
    private int taskCount = 0;

    public Task getTaskType(String newTask, TaskType type) {
        switch (type) {
        case ADD:
            return new Task(newTask);
        case TODO:
            return new Todo(newTask);
        case DEADLINE:
            String[] taskDetailsArray = getTaskNameAndTime(newTask, BY_STRING);
            return new Deadline(taskDetailsArray[0], taskDetailsArray[1]);
        case EVENT:
            String[] taskDetailsArray2 = getTaskNameAndTime(newTask, AT_STRING);
            return new Event(taskDetailsArray2[0], taskDetailsArray2[1]);
        }
        return null;
    }

    public String[] getTaskNameAndTime(String taskDetail, String delimiter) {
        String[] taskDetailArr = taskDetail.split(delimiter);
        return taskDetailArr;
    }

    public void addTask(String newTask, TaskType type) {
        Task typ = getTaskType(newTask, type);
        taskList[taskCount] = typ;
        taskCount++;
    }

    public void listTasks() {
        if (taskCount > 0) {
            PrintManager.printTaskListMessage(taskList, taskCount);
        } else {
            PrintManager.printEmptyTaskListMessage();
        }
    }

    public boolean isValidIndex(int userInputIndex) {
        return userInputIndex < taskCount && userInputIndex >= 0;
    }

    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        if (isValidIndex(taskIndex)) {
            taskList[taskIndex].setDone();
            PrintManager.printBotStatusMessage(
                    String.format("Good Job, u have completed\ntask: %s", taskList[taskIndex].getTaskName()));
        } else {
            PrintManager.printBotStatusMessage(
                    String.format("invalid task num, please key in a valid task num from 1 - %d", taskCount));
        }
    }
}
