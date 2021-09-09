package manager;

public class CmdManager {
    public final int MAX_COMMANDS_LIMIT = 100;
    public final String CMD_FORMAT = "cmd [args] /[options] [additional args]";
    public final String[] CMD_AVAILABLE = {
            "help, list, hist - no additional arguments required",
            "done [task no]",
            "add [task description]",
            "todo [task description]",
            "deadline [task description] /by [time/data]",
            "event [task description] /at [time/date]"
    };

    private String[] cmdList = new String[MAX_COMMANDS_LIMIT];
    private int cmdCount = 0;

    public void addCmd(String cmd) {
        cmdList[cmdCount] = cmd;
        cmdCount++;
    }

    public static void printAddStatus(String details) {
        PrintManager.printBotStatusMessage(String.format("adding: %s", details));
    }

    public void showHistory() {
        if (cmdCount > 0) {
            PrintManager.printStringListMessage(cmdList, cmdCount);
        } else {
            PrintManager.printBotStatusMessage("No previous commands");
        }
    }

    public boolean checkCmd(String inputCmd, String standardCmd) {
        if (inputCmd.equals(standardCmd)) {
            return true;
        }
        return false;
    }

    public void showHelp() {
        PrintManager.printNormalMessage(
                String.format("How to use this bot:\nType ur command in the following format\n%s", CMD_FORMAT));
        PrintManager.printStringListMessage(CMD_AVAILABLE, CMD_AVAILABLE.length);
    }
}
