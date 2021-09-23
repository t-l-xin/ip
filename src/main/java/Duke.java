import manager.CommandManager;
import manager.FileManager;
import manager.PrintManager;
import manager.TaskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String HELP_STRING = "help";
    public static final String LIST_STRING = "list";
    public static final String HIST_STRING = "hist";
    public static final String DONE = "done";
    public static final String DELETE_STRING = "delete";
    public static final String BYE_STRING = "bye";
    public static final String FIND_STRING = "find";

    /**
     * Starts the program until termination
     * Reads user command and executes it, until user issues bye command
     */
    private static void startProgram() {
        String inputLine;
        Scanner in = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        CommandManager cm = new CommandManager();
        PrintManager pm = new PrintManager();
        FileManager fm = new FileManager();
        cm.showHelp();

        boolean isBye = false;

        tryLoadSavedFile(tm, fm);

        do {
            pm.promptUserForCommand();
            inputLine = in.nextLine().trim();
            String[] cmdArray = inputLine.split(" ", 2);

            cm.addCommand(inputLine);

            isBye = cm.checkCommand(inputLine, BYE_STRING);

            switch (cmdArray[0]) {
            case HELP_STRING:
                cm.showHelp();
                break;
            case LIST_STRING:
                tm.listTasks();
                break;
            case HIST_STRING:
                cm.showHistory();
                break;
            case FIND_STRING:
                tm.findTask(cmdArray[1]);
                break;
            case DONE:
                try {
                    tm.markAsDone(cmdArray[1]);
                } catch (NullPointerException ne) {
                    PrintManager.printBotExceptionMessage(
                            "NullPointerException: please key in a valid index");
                } catch (ArrayIndexOutOfBoundsException ae) {
                    PrintManager.printBotExceptionMessage(
                            " ArrayIndexOutOfBoundsException: please key in a valid index");
                } catch (IndexOutOfBoundsException ie) {
                    PrintManager.printBotExceptionMessage(
                            "IndexOutOfBoundsException: index start from 1");
                }
                fm.saveTasksToFile(tm.getTaskList(), tm.getTaskCount());
                break;
            case DELETE_STRING:
                try {
                    tm.deleteTask(cmdArray[1]);
                } catch (NullPointerException ne) {
                    PrintManager.printBotExceptionMessage(
                            " NullPointerException: please key in a valid index");
                } catch (ArrayIndexOutOfBoundsException ae) {
                    PrintManager.printBotExceptionMessage(
                            " ArrayIndexOutOfBoundsException: please key in a valid index");
                } catch (IndexOutOfBoundsException ie) {
                    PrintManager.printBotExceptionMessage(
                            " IndexOutOfBoundsException: index start from 1");
                }
                fm.saveTasksToFile(tm.getTaskList(), tm.getTaskCount());
                break;
            case BYE_STRING:
                continue;
            default:
                tm.filterTasks(cmdArray, fm);
                break;
            }

        } while (!isBye);
    }


    /**
     * Try to load saved file from the previous session
     *
     * @param tm TaskManager object
     * @param fm FileManager object
     */
    private static void tryLoadSavedFile(TaskManager tm, FileManager fm) {
        try {
            PrintManager.printNormalMessage("loading saved files...");
            fm.loadTasksFromSavedFile(tm);
        } catch (FileNotFoundException e) {
            PrintManager.printBotStatusMessage("no pre-existing data files yet");
        } catch (IOException ie) {
            PrintManager.printBotStatusMessage("error reading file");
        }
    }

    /**
     * Runs program until termination
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        PrintManager.printWelcomeUser();
        startProgram();
        PrintManager.printByeUser();
    }
}
