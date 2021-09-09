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

        do {
            pm.promptUserForCommand();
            inputLine = in.nextLine().trim();
            String[] cmdArray = inputLine.split(" ", 2);

            cm.addCmd(inputLine);

            isBye = cm.checkCmd(inputLine, "bye");

            if (cm.checkCmd(cmdArray[0], "help")) {
                cm.showHelp();
            } else if (cm.checkCmd(cmdArray[0], "list")) {
                tm.listTasks();
            } else if (cm.checkCmd(cmdArray[0], "hist")) {
                cm.showHistory();
            } else if (cm.checkCmd(cmdArray[0], "done")) {
                tm.markAsDone(cmdArray[1]);
            } else if (cm.checkCmd(cmdArray[0], "add")) {
                cm.printAddStatus(cmdArray[1]);
                tm.addTask(cmdArray[1], TaskType.ADD);
            } else if (cm.checkCmd(cmdArray[0], "todo")) {
                try{
                    cm.printAddStatus(cmdArray[1]);
                    tm.addTask(cmdArray[1], TaskType.TODO);
                }catch (ArrayIndexOutOfBoundsException a){
                    pm.printBotStatusMessage("!!!details of todo cannot be empty");
                }
            } else if (cm.checkCmd(cmdArray[0], "deadline")) {
                try{
                    cm.printAddStatus(cmdArray[1]);
                    tm.addTask(cmdArray[1], TaskType.DEADLINE);
                }catch (ArrayIndexOutOfBoundsException a){
                    pm.printBotStatusMessage("!!!details of deadline cannot be empty");
                }
            } else if (cm.checkCmd(cmdArray[0], "event")) {
                try{
                    cm.printAddStatus(cmdArray[1]);
                    tm.addTask(cmdArray[1], TaskType.EVENT);
                }catch (ArrayIndexOutOfBoundsException a){
                    pm.printBotStatusMessage("!!!details of event cannot be empty");
                }
            } else if (cm.checkCmd(cmdArray[0], "bye")) {
                continue;
            } else {
                pm.printBotStatusMessage("invalid command, please try again");
            }
        } while (!isBye);
    }

    public static void main(String[] args) {
        PrintManager.printWelcomeUser();
        startProgram();
        PrintManager.printByeUser();
    }
}
