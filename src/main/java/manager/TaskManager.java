package manager;

import exception.DukeException;
import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;

import static task.TaskType.*;

public class TaskManager {
    public static final int MAX_TASKS_LIMIT = 100;
    public static final String BY_STRING = "/by";
    public static final String AT_STRING = "/at";

    ArrayList<Task> taskList = new ArrayList<Task>();
    //private Task[] taskList = new Task[MAX_TASKS_LIMIT];
    private int taskCount = 0;

    public void filterTasks(String[] cmdArray) {
        PrintManager pm = new PrintManager();
        switch (cmdArray[0]) {
        case "add":
            try {
                addTask(cmdArray[1], ADD);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            break;
        case "todo":
            try {
                addTask(cmdArray[1], TODO);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage(" todo task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            break;
        case "deadline":
            try {
                addTask(cmdArray[1], DEADLINE);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("deadline task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            break;
        case "event":
            try {
                addTask(cmdArray[1], EVENT);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("event task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            break;
        case "":
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
            String[] taskDetailsArray = getTaskNameAndTime(newTask, BY_STRING);
            return new Deadline(taskDetailsArray[0], taskDetailsArray[1]);
        case EVENT:
            String[] taskDetailsArray2 = getTaskNameAndTime(newTask, AT_STRING);
            return new Event(taskDetailsArray2[0], taskDetailsArray2[1]);
        }
        return null;
    }

    public String[] getTaskNameAndTime(String taskDetail, String delimiter)
            throws DukeException, StringIndexOutOfBoundsException {
        if (!taskDetail.contains(delimiter)) {
            throw new DukeException(String.format("\nDuke: can't find %s", delimiter));
        }
        int indexOfDelimiter = taskDetail.indexOf(delimiter);
        if (indexOfDelimiter == 0) {
            throw new DukeException("\nDuke: can't find details");
        }
        //PrintManager.printNormalMessage("index of delimiter: " + indexOfDelimiter);
        String detail = taskDetail.substring(0, indexOfDelimiter - 1).trim();
        String datetime = taskDetail.substring(indexOfDelimiter + delimiter.length()).trim();
        if (datetime.length() == 0) {
            throw new DukeException("\nDuke: can't find datetime");
        }
        String[] taskDetailsArray = {detail, datetime};
        return taskDetailsArray;
    }

    public void addTask(String newTask, TaskType type) throws DukeException {
        Task typ = getTaskType(newTask, type);
        CmdManager.printAddStatus(newTask);
        taskList.add(typ);
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
        taskList.get(taskIndex).setDone();
        PrintManager.printBotStatusMessage(
                String.format("Good Job, u have completed\ntask: %s", taskList.get(taskIndex).getTaskName()));
    }

    public void deleteTask(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        PrintManager.printBotStatusMessage(
                String.format("Removed task:\n%s", taskList.get(taskIndex)));
        taskList.remove(taskIndex);
        taskCount--;
    }
}
