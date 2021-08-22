import java.util.Scanner;

public class Duke {
    public static String logo =
            " ______________                                   \n" +
            "|            @ | #####  ####        #####   ####  \n" +
            "|         @@   |   #   #    #       #    # #    # \n" +
            "|       @@     |   #   #    # ##### #    # #    # \n" +
            "|  @@  @@      |   #   #    #       #    # #    # \n" +
            "|   @@@        |   #   #    #       #    # #    # \n" +
            "|    @         |   #    ####        #####   ####  \n" +
            " --------------";

    public static String separator = "\n" + "_".repeat(60) + "\n";

    public static void greetUser(){
        String helloGreeting = "Hi! I'm LingLing! " +
                "\nYour personal nanny to make sure you do work. \n"
                + "Have you started ur tasks? Start by typing \"help\"";
        System.out.println(logo + separator + helloGreeting + separator);
    }

    public static void startProgram(){
        String inputLine;
        Scanner in = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        CmdManager cm = new CmdManager();

        boolean isBye = false;
        boolean isList;

        do{
            System.out.print("\nType your command: \n");
            inputLine = in.nextLine();
            cm.addCmd(inputLine);

            isBye = cm.checkCmd(inputLine, "bye");

            if(cm.checkCmd(inputLine, "list")){
                tm.listTask();
            }else if(cm.checkCmd(inputLine, "hist")){
                cm.showHistory();
            }else if(!isBye){
                System.out.println(separator + "adding: " + inputLine + separator);
                tm.addTask(inputLine);
            }else {
                continue;
            }

        }while (!isBye);
    }

    public static void exitProgram(){
        String exitGreeting = "Well done for today! Remember to have 8 hrs of sleep!";
        System.out.println(separator + exitGreeting + separator);
    }

    public static void main(String[] args) {
        greetUser();
        startProgram();
        exitProgram();
    }
}
