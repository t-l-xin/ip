package manager;

import exception.DukeException;
import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static task.TaskType.ADD;
import static task.TaskType.TODO;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;

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

    public void getTaskListFromFile(ArrayList<Task> taskListFromFile) {
        taskList.addAll(taskListFromFile);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void getTaskCountFromFile(int taskCountFromFile) {
        taskCount = taskCountFromFile;
        PrintManager.printTaskListMessage(taskList, taskCount);
    }

    public void filterTasks(String[] cmdArray, FileManager fm) {
        PrintManager pm = new PrintManager();
        switch (cmdArray[INTEGER_ZERO]) {
        case ADD_STRING:
            try {
                addTask(cmdArray[INTEGER_ONE], ADD);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case TODO_STRING:
            try {
                addTask(cmdArray[INTEGER_ONE], TODO);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage(" todo task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case DEADLINE_STRING:
            try {
                addTask(cmdArray[INTEGER_ONE], DEADLINE);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("deadline task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case EVENT_STRING:
            try {
                addTask(cmdArray[INTEGER_ONE], EVENT);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("event task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            fm.saveTasksToFile(taskList, taskCount);
            break;
        case EMPTY_STRING:
            pm.printBotStatusMessage("no command detected, please try again");
            break;
        default:
            pm.printBotStatusMessage("invalid command, please try again");
            break;
        }
    }

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

    public String[] getTaskNameAndTime(String taskDetail, String delimiter)
            throws DukeException, StringIndexOutOfBoundsException {
        checkForDelimiter(taskDetail, delimiter);
        int indexOfDelimiter = taskDetail.indexOf(delimiter);

        checkEmptyDetails(indexOfDelimiter);
        String detail = taskDetail.substring(INTEGER_ZERO, indexOfDelimiter - INTEGER_ONE).trim();
        String datetime = taskDetail.substring(indexOfDelimiter + delimiter.length()).trim();

        checkEmptyDateTime(datetime);
        String[] taskDetailsArray = {detail, datetime};
        return taskDetailsArray;
    }

    private void checkEmptyDetails(int indexOfDelimiter) throws DukeException {
        if (indexOfDelimiter == INTEGER_ZERO) {
            throw new DukeException("\nDuke: can't find details");
        }
    }

    private void checkEmptyDateTime(String datetime) throws DukeException {
        if (datetime.length() == INTEGER_ZERO) {
            throw new DukeException("\nDuke: can't find datetime");
        }
    }

    private void checkForDelimiter(String taskDetail, String delimiter) throws DukeException {
        if (!taskDetail.contains(delimiter)) {
            throw new DukeException(String.format("\nDuke: can't find %s", delimiter));
        }
    }

    public void addTask(String newTask, TaskType newType) throws DukeException {
        Task type = getTaskType(newTask, newType);
        CmdManager.printAddStatus(newTask);
        taskList.add(type);
        taskCount++;
    }

    public void listTasks() {
        if (taskCount > INTEGER_ZERO) {
            PrintManager.printTaskListMessage(taskList, taskCount);
        } else {
            PrintManager.printEmptyTaskListMessage();
        }
    }

    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - INTEGER_ONE;
        taskList.get(taskIndex).setDone();
        PrintManager.printBotStatusMessage(
                String.format("Good Job, u have completed\ntask: %s", taskList.get(taskIndex).getTaskName()));
    }

    public void deleteTask(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - INTEGER_ONE;
        PrintManager.printBotStatusMessage(
                String.format("Removed task:\n%s", taskList.get(taskIndex)));
        taskList.remove(taskIndex);
        taskCount--;
    }

    public void findTask(String stringToFind) {
        ArrayList<Task> filteredList = (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getTaskName().contains(stringToFind))
                .collect(Collectors.toList());
        PrintManager.printTaskListMessage(filteredList, filteredList.size());
    }
}
