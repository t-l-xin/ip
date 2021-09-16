import manager.CmdManager;
import manager.PrintManager;
import manager.TaskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static void startProgram() {
        String inputLine;
        Scanner in = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        CmdManager cm = new CmdManager();
        PrintManager pm = new PrintManager();
        cm.showHelp();

        boolean isBye = false;

        tryLoadSavedFile(tm);

        do {
            pm.promptUserForCommand();
            inputLine = in.nextLine().trim();
            String[] cmdArray = inputLine.split(" ", 2);

            cm.addCmd(inputLine);

            isBye = cm.checkCmd(inputLine, "bye");

            switch (cmdArray[0]) {
            case "help":
                cm.showHelp();
                break;
            case "list":
                tm.listTasks();
                break;
            case "hist":
                cm.showHistory();
                break;
            case "done":
                tm.doneOrDeleteTask(cmdArray[1], "done");
                break;
            case "delete":
                tm.doneOrDeleteTask(cmdArray[1], "delete");
                break;
            case "bye":
                continue;
            default:
                tm.filterTasks(cmdArray);
                break;
            }

        } while (!isBye);
    }

    private static void tryLoadSavedFile(TaskManager tm) {
        try {
            PrintManager.printNormalMessage("loading saved files...");
            tm.loadTasksFromSavedFile();
        } catch (FileNotFoundException e) {
            PrintManager.printBotStatusMessage("no pre-existing data files yet");
        } catch (IOException ie) {
            PrintManager.printBotStatusMessage("error reading file");
        }
    }

    public static void main(String[] args) {
        PrintManager.printWelcomeUser();
        startProgram();
        PrintManager.printByeUser();
    }
}
