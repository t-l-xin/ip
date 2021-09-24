package manager;

/**
 * Represents CommandManager. Manages data and operations involving commands during program execution.
 */
public class CommandManager {
    private final int MAX_COMMANDS_LIMIT = 100;
    private final String COMMAND_FORMAT = "cmd [args] /[delimiter] [additional args]";
    private final String[] COMMANDS_AVAILABLE = {
            "help - Displays help information such as commands available\n",
            "list - Displays list of tasks\n",
            "add [TASK_DESCRIPTION] - Add a normal task\n",
            "todo [TASK_DESCRIPTION] - Add a Todo task\n",
            "deadline [TASK_DESCRIPTION] /by [DATE_TO_BE_COMPLETED] - Add a Deadline task \nInput DateTime format: DD/MM/YYYY HHMM\n",
            "event [TASK_DESCRIPTION] /at [DATE_OF_EVENT] - Add a Event task \nInput DateTime format: DD/MM/YYYY HHMM\n",
            "done [TASK_NUMBER] - Mark a task as done\n",
            "delete [TASK_NUMBER] - Delete a task from the task list\n",
            "find [TASK_KEYWORD] - Find a task by a keyword\n",
            "hist - Displays list of previous user input commands\n",
            "bye - Exit Duke program"
    };
    
    private String[] commandList = new String[MAX_COMMANDS_LIMIT];
    private int commandCount = 0;

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
     * Check if the input command match the program command.
     *
     * @param inputCommand The user input command.
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

    /**
    public String[] processCommand(){
        String[] commandDetailsArray;

        return commandDetailsArray;
    }
     */
}
