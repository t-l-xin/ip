package manager;

import exception.DukeException;
import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static task.TaskType.ADD;
import static task.TaskType.TODO;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;

/**
 * Represents TaskManager. Contains data and operations involving tasks during the execution of the program.
 */
public class TaskManager {
    public static final String BY_STRING = "/by";
    public static final String AT_STRING = "/at";
    public static final String ADD_STRING = "add";
    public static final String TODO_STRING = "todo";
    public static final String DEADLINE_STRING = "deadline";
    public static final String EVENT_STRING = "event";
    public static final String EMPTY_STRING = "";
    public static final int INTEGER_ZERO = 0;
    public static final int INTEGER_ONE = 1;

    ArrayList<Task> taskList = new ArrayList<Task>();
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
        PrintManager.printTaskListMessage(taskList, taskCount);
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
     * Filters the tasks by task type and executes the adding of tasks to task list.
     * After task added to task list, the task list is saved to file, in case of program pre-mature exit.
     *
     * @param commandArray The user command array.
     * @param fm The FileManager object.
     */
    public void filterTasks(String[] commandArray, FileManager fm) {
        switch (commandArray[INTEGER_ZERO]) {
        case ADD_STRING:
            try {
                addTask(commandArray[INTEGER_ONE], ADD);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case TODO_STRING:
            try {
                addTask(commandArray[INTEGER_ONE], TODO);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage(" todo task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case DEADLINE_STRING:
            try {
                addTask(commandArray[INTEGER_ONE], DEADLINE);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("deadline task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case EVENT_STRING:
            try {
                addTask(commandArray[INTEGER_ONE], EVENT);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("event task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case EMPTY_STRING:
            PrintManager.printBotStatusMessage("no command detected, please try again");
            break;
        default:
            PrintManager.printBotStatusMessage("invalid command, please try again");
            break;
        }
    }

    /**
     * Gets the type of Task object to be added.
     *
     * @param newTask The new task object to be added.
     * @param type The type of task to be added.
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
     * @param delimiter The delimiter depending on the task type.
     * @return A string array that have split the task name and task detail.
     * @throws DukeException If there input does not follow program input format.
     * @throws StringIndexOutOfBoundsException If the task details string array is missing some information.
     */
    public String[] getTaskNameAndTime(String taskDetail, String delimiter)
            throws DukeException, StringIndexOutOfBoundsException {
        checkForDelimiter(taskDetail, delimiter);
        int indexOfDelimiter = taskDetail.indexOf(delimiter);

        checkEmptyDetails(indexOfDelimiter);
        String detail = taskDetail.substring(INTEGER_ZERO, indexOfDelimiter - INTEGER_ONE).trim();

        String datetime = taskDetail.substring(indexOfDelimiter + delimiter.length()).trim();
        checkEmptyDateTime(datetime);
        ParseManager.checkValidDateTimeFormat(datetime);

        String[] taskDetailsArray = {detail, datetime};
        return taskDetailsArray;
    }

    /**
     * Checks for empty details by the index of the delimiter.
     *
     * @param indexOfDelimiter The index of the delimiter.
     * @throws DukeException If the index of the delimiter is 0, which means details field is empty.
     */

    private void checkEmptyDetails(int indexOfDelimiter) throws DukeException {
        if (indexOfDelimiter == INTEGER_ZERO) {
            throw new DukeException("\nDuke: can't find details");
        }
    }

    /**
     * Checks if the date and time field is empty.
     *
     * @param datetime The string that contains date and time of the task.
     * @throws DukeException If the date time field is empty.
     */
    private void checkEmptyDateTime(String datetime) throws DukeException {
        if (datetime.length() == INTEGER_ZERO) {
            throw new DukeException("\nDuke: can't find datetime");
        }
    }

    /**
     * @param taskDetail The string that contains task details.
     * @param delimiter The delimiter for the task type.
     * @throws DukeException If delimiter for the task type is not found.
     */
    private void checkForDelimiter(String taskDetail, String delimiter) throws DukeException {
        if (!taskDetail.contains(delimiter)) {
            throw new DukeException(String.format("\nDuke: can't find %s", delimiter));
        }
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
    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - INTEGER_ONE;
        taskList.get(taskIndex).setDone();
        PrintManager.printBotStatusMessage(
                String.format("Good Job, u have completed\ntask: %s", taskList.get(taskIndex).getTaskName()));
    }

    /**
     * Finds correct task index, deletes a task from the task list and prints deleted status.
     * Decrement task count by 1.
     *
     * @param taskNo The task number to be found for deletion.
     */
    public void deleteTask(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - INTEGER_ONE;
        PrintManager.printBotStatusMessage(
                String.format("Removed task:\n%s", taskList.get(taskIndex)));
        taskList.remove(taskIndex);
        taskCount--;
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
