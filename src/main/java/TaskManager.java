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
                int j = i + 1;
                System.out.printf("\n%d. %s ", j, taskName);
            }
        }else{
            System.out.println("No tasks, start by adding a task!");
        }

        System.out.println("\n");
    }
}
