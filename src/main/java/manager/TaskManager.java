package manager;

import exception.DukeException;
import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents TaskManager. Contains data and operations involving tasks during the execution of the program.
 */
public class TaskManager {
    private static final String BY_STRING = "/by";
    private static final String AT_STRING = "/at";
    private static final int INTEGER_ZERO = 0;
    private static final int INTEGER_ONE = 1;

    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int taskCount = INTEGER_ZERO;

    /**
     * Initialises the task list in Task Manager object, after loading from file duke.txt.
     *
     * @param taskListFromFile The arraylist of tasks from the loaded file duke.txt.
     */
    public void getTaskListFromFile(ArrayList<Task> taskListFromFile) {
        taskList.addAll(taskListFromFile);
    }

    /**
     * Gets task count from the loaded file duke.txt.
     *
     * @param taskCountFromFile The task count from the loaded filed.
     */
    public void getTaskCountFromFile(int taskCountFromFile) {
        taskCount = taskCountFromFile;
    }

    /**
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * @return The task count.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Gets the type of Task object to be added.
     *
     * @param newTask The new task object to be added.
     * @param type    The type of task to be added.
     * @return A Task object to be added in the subsequent functions.
     * @throws DukeException If user input does not follow the program input format.
     */
    public Task getTaskType(String newTask, TaskType type) throws DukeException {
        switch (type) {
        case ADD:
            return new Task(newTask);
        case TODO:
            return new Todo(newTask);
        case DEADLINE:
            String[] taskDeadlineDetailsArray = getTaskNameAndTime(newTask, BY_STRING);
            return new Deadline(taskDeadlineDetailsArray[INTEGER_ZERO], taskDeadlineDetailsArray[INTEGER_ONE]);
        case EVENT:
            String[] taskEventDetailsArray = getTaskNameAndTime(newTask, AT_STRING);
            return new Event(taskEventDetailsArray[INTEGER_ZERO], taskEventDetailsArray[INTEGER_ONE]);
        }
        return null;
    }

    /**
     * Gets the task name and time by parsing the task string, checks if it follows the input format.
     *
     * @param taskDetail The string that contains task details.
     * @param delimiter  The delimiter depending on the task type.
     * @return A string array that have split the task name and task detail.
     * @throws DukeException                   If there input does not follow program input format.
     * @throws StringIndexOutOfBoundsException If the task details string array is missing some information.
     */
    public String[] getTaskNameAndTime(String taskDetail, String delimiter)
            throws DukeException, StringIndexOutOfBoundsException {
        ParseManager.checkForDelimiter(taskDetail, delimiter);
        int indexOfDelimiter = taskDetail.indexOf(delimiter);

        ParseManager.checkEmptyDetails(indexOfDelimiter);
        String detail = taskDetail.substring(INTEGER_ZERO, indexOfDelimiter - INTEGER_ONE).trim();

        String datetime = taskDetail.substring(indexOfDelimiter + delimiter.length()).trim();
        ParseManager.checkEmptyDateTime(datetime);
        ParseManager.checkValidDateTimeFormat(datetime);

        String[] taskDetailsArray = {detail, datetime};
        return taskDetailsArray;
    }



    /**
     * Adds task to task arraylist and increments task count by 1.
     *
     * @param newTask The new task object which can be of any type.
     * @param newType The new task type.
     * @throws DukeException If there are any inputs that do not follow input format.
     */
    public void addTask(String newTask, TaskType newType) throws DukeException {
        Task resultNewTask = getTaskType(newTask, newType);
        CommandManager.printAddStatus(newTask);
        taskList.add(resultNewTask);
        taskCount++;
    }

    /**
     * If task count more than zero, print all tasks.
     * Else, print empty task list.
     */
    public void listTasks() {
        if (taskCount > INTEGER_ZERO) {
            PrintManager.printTaskListMessage(taskList, taskCount);
        } else {
            PrintManager.printEmptyTaskListMessage();
        }
    }

    /**
     * Finds the correct task index, marks task as done and prints the done status.
     *
     * @param taskNo The task number to be marked as done.
     */
    public void markAsDone(String taskNo) throws DukeException {
        int taskIndex = Integer.parseInt(taskNo) - INTEGER_ONE;
        try {
            taskList.get(taskIndex).setDone();
            PrintManager.printBotStatusMessage(
                    String.format("Good Job, u have completed\ntask: %s", taskList.get(taskIndex).getTaskName()));
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("IndexOutOfBounds: index start from 1 to " + taskCount);
        }

    }

    /**
     * Finds correct task index, deletes a task from the task list and prints deleted status.
     * Decrement task count by 1.
     *
     * @param taskNo The task number to be found for deletion.
     */
    public void deleteTask(String taskNo) throws DukeException {
        int taskIndex = Integer.parseInt(taskNo) - INTEGER_ONE;
        try {
            PrintManager.printBotStatusMessage(
                    String.format("Removed task:\n%s", taskList.get(taskIndex)));
            taskList.remove(taskIndex);
            taskCount--;
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("IndexOutOfBounds: index start from 1 to " + taskCount);
        }
    }

    /**
     * Finds the user input string by filtering through the arraylist.
     *
     * @param stringToFind User input string to find.
     */
    public void findTask(String stringToFind) {
        ArrayList<Task> filteredList = (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getTaskName().contains(stringToFind))
                .collect(Collectors.toList());
        PrintManager.printTaskListMessage(filteredList, filteredList.size());
    }
}
