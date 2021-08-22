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

    public static void echoCommand(){
        String inputLine;
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        do{
            System.out.print("Type your command: \n");
            inputLine = in.nextLine();
            isBye = checkBye(inputLine);
            if(!isBye){
                System.out.println(separator + "echo " + inputLine + separator);
            }
        }while (!isBye);
        exitProgram();
    }

    public static boolean checkBye(String command){
        String byeString = "bye";
        if(command.equals(byeString)){
            return true;
        }
        return false;
    }

    public static void exitProgram(){
        String exitGreeting = "Well done for today! Remember to have 8 hrs of sleep!";
        System.out.println(separator + exitGreeting + separator);
    }

    public static void main(String[] args) {
        greetUser();
        echoCommand();
    }
}
