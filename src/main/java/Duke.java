import java.util.Scanner;

public class Duke {
    public static String logo =
            " ______________\n" +
            "|            @ | #####  ####        #####   ####\n" +
            "|         @@   |   #   #    #       #    # #    #\n" +
            "|       @@     |   #   #    # ##### #    # #    #\n" +
            "|  @@  @@      |   #   #    #       #    # #    #\n" +
            "|   @@@        |   #   #    #       #    # #    #\n" +
            "|    @         |   #    ####        #####   ####\n" +
            " --------------";

    public static String separator = "\n" + "_".repeat(60) + "\n";

    public static void greetUser() {
        String helloGreeting = "Hi! I'm LingLing!\n" +
                "Your personal nanny to make sure you do work.\n"
                + "Have you started ur tasks? Start by typing \"help\"";
        System.out.println(logo + separator + helloGreeting + separator);
    }

    public static void startProgram() {
        String inputLine;
        Scanner in = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        CmdManager cm = new CmdManager();
        cm.showHelp();

        boolean isBye = false;

        do {
            System.out.print("\nType your command:\n");
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
                    System.out.printf("details of todo cannot be empty");
                }
            } else if (cm.checkCmd(cmdArray[0], "deadline")) {
                try{
                    cm.printAddStatus(cmdArray[1]);
                    tm.addTask(cmdArray[1], TaskType.DEADLINE);
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.printf("details of deadline cannot be empty");
                }
            } else if (cm.checkCmd(cmdArray[0], "event")) {
                try{
                    cm.printAddStatus(cmdArray[1]);
                    tm.addTask(cmdArray[1], TaskType.EVENT);
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.printf("details of event cannot be empty");
                }

            } else if (cm.checkCmd(cmdArray[0], "bye")) {
                continue;
            } else {
                System.out.println("invalid command, please try again");
            }
        } while (!isBye);
    }

    public static void exitProgram() {
        String exitGreeting = "Well done for today! Remember to have 8 hrs of sleep!";
        System.out.println(separator + exitGreeting + separator);
    }

    public static void main(String[] args) {
        greetUser();
        startProgram();
        exitProgram();
    }
}
