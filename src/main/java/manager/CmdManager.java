package manager;

public class CmdManager {
    public final int MAX_COMMANDS_LIMIT = 100;
    public final String COMMAND_FORMAT = "cmd [args] /[options] [additional args]";
    public final String[] COMMANDS_AVAILABLE = {
            "help, list, hist - no additional arguments required",
            "done [task no]",
            "delete [task no]",
            "add [task description]",
            "todo [task description]",
            "deadline [task description] /by YYYY/MM/DD hh:mm",
            "event [task description] /at YYYY/MM/DD hh:mm"
    };

    private String[] commandList = new String[MAX_COMMANDS_LIMIT];
    private int commandCount = 0;

    public void addCommand(String cmd) {
        commandList[commandCount] = cmd;
        commandCount++;
    }

    public static void printAddStatus(String details) {
        PrintManager.printBotStatusMessage(String.format("adding: %s", details));
    }

    public void showHistory() {
        if (commandCount > 0) {
            PrintManager.printStringListMessage(commandList, commandCount);
        } else {
            PrintManager.printBotStatusMessage("No previous commands");
        }
    }

    public boolean checkCommand(String inputCommand, String standardCommand) {
        if (inputCommand.equals(standardCommand)) {
            return true;
        }
        return false;
    }

    public void showHelp() {
        PrintManager.printNormalMessage(
                String.format("How to use this bot:\nType ur command in the following format\n%s", COMMAND_FORMAT));
        PrintManager.printStringListMessage(COMMANDS_AVAILABLE, COMMANDS_AVAILABLE.length);
    }
}
