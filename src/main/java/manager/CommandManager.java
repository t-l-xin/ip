package manager;

import exception.DukeException;

import java.util.Scanner;

import static task.TaskType.ADD;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;
import static task.TaskType.TODO;

/**
 * Represents CommandManager. Manages data and operations involving commands during program execution.
 */
public class CommandManager {
    public static final String SPACE_CHARACTER = " ";
    public static final int MAX_SPLIT_LIMIT = 2;
    private final int MAX_COMMANDS_LIMIT = 100;
    private final String COMMAND_FORMAT = "cmd [args] /[delimiter] [additional args]";
    private final String[] COMMANDS_AVAILABLE = {
            "help - Displays help information such as commands available\n",
            "list - Displays list of tasks\n",
            "add [TASK_DESCRIPTION] - Add a normal task\n",
            "todo [TASK_DESCRIPTION] - Add a Todo task\n",
            "deadline [TASK_DESCRIPTION] /by [DATE_TO_BE_COMPLETED] - Add a Deadline task\nInput DateTime format: DD/MM/YYYY HHMM\n",
            "event [TASK_DESCRIPTION] /at [DATE_OF_EVENT] - Add a Event task\nInput DateTime format: DD/MM/YYYY HHMM\n",
            "done [TASK_NUMBER] - Mark a task as done\n",
            "delete [TASK_NUMBER] - Delete a task from the task list\n",
            "find [TASK_KEYWORD] - Find a task by a keyword\n",
            "hist - Displays list of previous user input commands\n",
            "bye - Exit Duke program"
    };

    private static final String HELP_STRING = "help";
    private static final String LIST_STRING = "list";
    private static final String HIST_STRING = "hist";
    private static final String DONE_STRING = "done";
    private static final String DELETE_STRING = "delete";
    private static final String BYE_STRING = "bye";
    private static final String FIND_STRING = "find";
    private static final String ADD_STRING = "add";
    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";
    private static final String EMPTY_STRING = "";
    private String[] commandList = new String[MAX_COMMANDS_LIMIT];
    private int commandCount = 0;
    private static boolean isBye = false;
    private static boolean requireFileSaveOperation;

    /**
     * Adds the command input by the user to a string array.
     *
     * @param cmd The command issued by the user.
     */
    public void addCommand(String cmd) {
        commandList[commandCount] = cmd;
        commandCount++;
    }

    /**
     * Prints the status of adding of the task.
     *
     * @param details The details of the add task added.
     */
    public static void printAddStatus(String details) {
        PrintManager.printBotStatusMessage(String.format("adding: %s", details));
    }

    /**
     * Prints the history of user command inputs, if there are previous commands.
     */
    public void showHistory() {
        if (commandCount > 0) {
            PrintManager.printStringListMessage(commandList, commandCount);
        } else {
            PrintManager.printBotStatusMessage("No previous commands");
        }
    }

    /**
     * Unused function. Check if the input command match the program command.
     *
     * @param inputCommand   The user input command.
     * @param programCommand The program command.
     * @return A boolean to which the command matches the string.
     */
    public boolean checkCommand(String inputCommand, String programCommand) {
        if (inputCommand.equals(programCommand)) {
            return true;
        }
        return false;
    }

    /**
     * Prints a list of usable program commands information for the user.
     */
    public void showHelp() {
        PrintManager.printNormalMessage(
                String.format("How to use this bot:\nType ur command in the following format\n%s", COMMAND_FORMAT));
        PrintManager.printStringListMessage(COMMANDS_AVAILABLE, COMMANDS_AVAILABLE.length);
    }

    public String[] readCommand(Scanner in) {
        PrintManager.promptUserForCommand();
        String inputLine;
        inputLine = in.nextLine().trim();
        String[] commandArray = inputLine.split(SPACE_CHARACTER, MAX_SPLIT_LIMIT);
        addCommand(inputLine);
        return commandArray;
    }

    /**
     * Processes the commands and capture any exceptions.
     *
     * @param commandDetailsArray The array that contains the details of the command.
     * @param tm                  TaskManager object.
     * @return A boolean to the program whether to terminate.
     */
    public boolean processCommand(String[] commandDetailsArray, TaskManager tm) {
        requireFileSaveOperation = false;
        try {
            filterAndExecuteCommand(commandDetailsArray, tm);
        } catch (ArrayIndexOutOfBoundsException ae) {
            PrintManager.printBotExceptionMessage(commandDetailsArray[0] + " task needs details");
        } catch (DukeException de) {
            PrintManager.printBotExceptionMessage(de.getMessage());
        }
        return isBye;
    }

    /**
     * Filter the command by the first word in the command details array and executes the command.
     *
     * @param commandDetailsArray The array that contains the details of the command.
     * @param tm TaskManager object.
     * @throws DukeException If there are any input format violations.
     */
    private void filterAndExecuteCommand(String[] commandDetailsArray, TaskManager tm) throws DukeException {
        switch (commandDetailsArray[0]) {
        case HELP_STRING:
            showHelp();
            break;
        case LIST_STRING:
            tm.listTasks();
            break;
        case HIST_STRING:
            showHistory();
            break;
        case FIND_STRING:
            tm.findTask(commandDetailsArray[1]);
            break;
        case DONE_STRING:
            tm.markAsDone(commandDetailsArray[1]);
            requireFileSaveOperation = true;
            break;
        case DELETE_STRING:
            tm.deleteTask(commandDetailsArray[1]);
            requireFileSaveOperation = true;
            break;
        case BYE_STRING:
            isBye = true;
            break;
        case ADD_STRING:
            tm.addTask(commandDetailsArray[1], ADD);
            requireFileSaveOperation = true;
            break;
        case TODO_STRING:
            tm.addTask(commandDetailsArray[1], TODO);
            requireFileSaveOperation = true;
            break;
        case DEADLINE_STRING:
            tm.addTask(commandDetailsArray[1], DEADLINE);
            requireFileSaveOperation = true;
            break;
        case EVENT_STRING:
            tm.addTask(commandDetailsArray[1], EVENT);
            requireFileSaveOperation = true;
            break;
        case EMPTY_STRING:
            PrintManager.printBotStatusMessage("no command detected, please try again");
            break;
        default:
            PrintManager.printBotStatusMessage("invalid command, please try again");
            break;
        }
    }

    public static boolean checkNeedSaveOperation(){
        return requireFileSaveOperation;
    }

}
