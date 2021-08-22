public class TaskManager {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;

    public void addTask(String newTask){
        taskList[taskCount] = new Task(newTask);
        taskCount++;
    }

    public void listTask(){
        if(taskCount>0){
            for(int i = 0; i < taskCount; i++) {
                String taskName = taskList[i].getTaskName();
                String taskStatus = taskList[i].getStatusIcon();
                int j = i + 1;
                System.out.printf("\n%d. [%s] %s ", j, taskStatus, taskName);
            }
        }else{
            System.out.println("No tasks, start by adding a task!");
        }

        System.out.println("\n");
    }

    public void markAsDone(String taskNo){
        int taskIndex = Integer.parseInt(taskNo)-1;
        if(taskIndex < taskCount && taskIndex >= 0){
            taskList[taskIndex].setDone();
            System.out.printf("Good Job, u have completed \ntask: %s \n", taskList[taskIndex].getTaskName());
        }else{
            System.out.printf("invalid task no, please key in a valid task no from 1 - %d", taskCount);
        }
    }
}
