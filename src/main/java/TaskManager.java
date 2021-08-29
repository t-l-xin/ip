public class TaskManager {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;
    protected String byString = " /by ";
    protected String atString = " /at ";

    public Task getTaskType(String newTask, String type){
        switch (type){
            case "add":
                return new Task(newTask);
            case "todo":
                return new Todo(newTask);
            case "deadline":
                String[] taskDetailsArr = getTaskNameAndTime(newTask, byString);
                return new Deadline(taskDetailsArr[0], taskDetailsArr[1]);
            case "event":
                String[] taskDetailsArr2 = getTaskNameAndTime(newTask, atString);
                return new Event(taskDetailsArr2[0], taskDetailsArr2[1]);
        }
        return null;
    }
    public String[] getTaskNameAndTime(String taskDetail, String delimiter){
        String[] taskDetailArr = taskDetail.split(delimiter);
        return taskDetailArr;
    }

    public void addTask(String newTask, String type) {
        Task typ = getTaskType(newTask, type);
        taskList[taskCount] = typ;
        taskCount++;
    }

    public void listTask() {
        if (taskCount > 0) {
            for (int i = 0; i < taskCount; i++) {
                int j = i + 1;
                System.out.printf("\n%d. %s ", j, taskList[i].toString());
            }
        } else {
            System.out.println("No tasks, start by adding a task!");
        }
        System.out.printf("\nTotal Tasks: %s\n", taskCount );
    }

    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        if (taskIndex < taskCount && taskIndex >= 0) {
            taskList[taskIndex].setDone();
            System.out.printf("Good Job, u have completed \ntask: %s \n", taskList[taskIndex].getTaskName());
        } else {
            System.out.printf("invalid task no, please key in a valid task no from 1 - %d", taskCount);
        }
    }
}
