import manager.CmdManager;
import manager.PrintManager;
import manager.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void startProgram() {
        String inputLine;
        Scanner in = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        CmdManager cm = new CmdManager();
        PrintManager pm = new PrintManager();
        cm.showHelp();

        boolean isBye = false;

        try {
            tm.loadTasksFromSavedFile();
        } catch (IOException e) {
            PrintManager.printBotStatusMessage("no pre-existing files yet\n");
        }

        do {
            pm.promptUserForCommand();
            inputLine = in.nextLine().trim();
            String[] cmdArray = inputLine.split(" ", 2);

            cm.addCmd(inputLine);

            isBye = cm.checkCmd(inputLine, "bye");

            switch (cmdArray[0]){
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
                try {
                    tm.markAsDone(cmdArray[1]);
                }catch (NullPointerException ne ){
                    PrintManager.printBotExceptionMessage(
                            "NullPointerException: please key in a valid index");
                }catch (IndexOutOfBoundsException ie ){
                    PrintManager.printBotExceptionMessage(
                            "IndexOutOfBoundsException: please key in a valid index");
                }
                break;
            case "delete":
                try {
                    tm.deleteTask(cmdArray[1]);
                }catch (NullPointerException ne ){
                    PrintManager.printBotExceptionMessage(
                            "NullPointerException: please key in a valid index");
                }catch (IndexOutOfBoundsException ae){
                    PrintManager.printBotExceptionMessage(
                            "ArrayIndexOutOfBoundsException: index start from 1");
                }
                break;
            case "bye":
                tm.saveTasksToFile();
                break;
            default:
                tm.filterTasks(cmdArray);
                break;
            }

        } while (!isBye);
    }

    public static void main(String[] args) {
        PrintManager.printWelcomeUser();
        startProgram();
        PrintManager.printByeUser();
    }
}
