package manager;

/**
 * Represents CommandManager. Manages data and operations involving commands during program execution.
 */
public class CommandManager {
    public final int MAX_COMMANDS_LIMIT = 100;
    public final String COMMAND_FORMAT = "cmd [args] /[options] [additional args]";
    public final String[] COMMANDS_AVAILABLE = {
            "help, list, hist - no additional arguments required",
            "done [task no]",
            "delete [task no]",
            "add [task description]",
            "todo [task description]",
            "deadline [task description] /by YYYY/MM/DD HHMM",
            "event [task description] /at YYYY/MM/DD HHMM",
            "bye"
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
}
