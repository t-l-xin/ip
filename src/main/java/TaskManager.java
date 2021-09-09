public class TaskManager {
    public static final int MAX_TASKS_LIMIT = 100;
    private Task[] taskList = new Task[MAX_TASKS_LIMIT];
    private int taskCount = 0;
    protected String byString = " /by ";
    protected String atString = " /at ";

    public Task getTaskType(String newTask, TaskType type) {
        switch (type) {
        case ADD:
            return new Task(newTask);
        case TODO:
            return new Todo(newTask);
        case DEADLINE:
            String[] taskDetailsArr = getTaskNameAndTime(newTask, byString);
            return new Deadline(taskDetailsArr[0], taskDetailsArr[1]);
        case EVENT:
            String[] taskDetailsArr2 = getTaskNameAndTime(newTask, atString);
            return new Event(taskDetailsArr2[0], taskDetailsArr2[1]);
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
            for (int i = 0; i < taskCount; i++) {
                int taskNum = i + 1;
                System.out.printf("\n%d. %s", taskNum, taskList[i].toString());
            }
        } else {
            System.out.println("No tasks, start by adding a task!");
        }
        System.out.printf("\nTotal Tasks: %s\n", taskCount);
    }

    public boolean isValidIndex(int userInputIndex) {
        return userInputIndex < taskCount && userInputIndex >= 0;
    }

    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        if (isValidIndex(taskIndex)) {
            taskList[taskIndex].setDone();
            System.out.printf("Good Job, u have completed\ntask: %s\n", taskList[taskIndex].getTaskName());
        } else {
            System.out.printf("invalid task num, please key in a valid task num from 1 - %d", taskCount);
        }
    }
}
