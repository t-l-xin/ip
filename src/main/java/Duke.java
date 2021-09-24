import exception.DukeException;
import manager.CommandManager;
import manager.FileManager;
import manager.PrintManager;
import manager.TaskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static boolean isBye = false;

    /**
     * Starts the program until termination
     * Reads user command and executes it, until user issues bye command
     */
    private static void startProgram() {
        Scanner in = new Scanner(System.in);
        CommandManager cm = new CommandManager();
        TaskManager tm = new TaskManager();
        FileManager fm = new FileManager();

        cm.showHelp();
        tryLoadSavedFile(tm, fm);

        while (!isBye) {
            try {
                String[] commandDetailsArray = cm.readCommand(in);
                isBye = cm.processCommand(commandDetailsArray, tm);
                if (cm.checkNeedSaveOperation()) {
                    fm.saveTasksToFile(tm.getTaskList(), tm.getTaskCount());
                }
            } catch (DukeException e) {
                PrintManager.printBotExceptionMessage(e.getMessage());
            }
        }
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
