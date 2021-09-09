public class PrintManager {
    public final static String ZERO_CONSTANT_STRING = "0";
    private final static String SEPARATOR = "\n" + "_".repeat(60) + "\n";
    private final static String ERROR_SEPARATOR = "\n" + "*".repeat(60) + "\n";

    private final static String LOGO =
            " ______________\n" +
            "|            @ | #####  ####        #####   ####\n" +
            "|         @@   |   #   #    #       #    # #    #\n" +
            "|       @@     |   #   #    # ##### #    # #    #\n" +
            "|  @@  @@      |   #   #    #       #    # #    #\n" +
            "|   @@@        |   #   #    #       #    # #    #\n" +
            "|    @         |   #    ####        #####   ####\n" +
            " --------------";
    private final static String HELLO_GREETING = "Hi! I'm LingLing!\n" +
            "Your personal nanny to make sure you do work.\n"
            + "Have you started ur tasks? Start by typing \"help\"";

    private final static String EXIT_GREETING = "Well done for today! Remember to have 8 hrs of sleep!";
    private final static String TOTAL_TASKS_STRING_FORMAT = "\nTotal Tasks: %s";
    private final static String NO_TASKS_MESSAGE = "No tasks, start by adding a task!";

    public static void printWelcomeUser() {
        System.out.println(LOGO + SEPARATOR + HELLO_GREETING + SEPARATOR);
    }

    public static void printByeUser() {
        System.out.println(SEPARATOR + EXIT_GREETING + SEPARATOR);
    }

    public static void promptUserForCommand() {
        System.out.println("\nType your command:\n");
    }

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void printTaskListMessage(Task[] taskList, int taskCount) {
        printSeparator();
        for (int i = 0; i < taskCount; i++) {
            int taskNum = i + 1;
            System.out.printf("%d. %s\n", taskNum, taskList[i].toString());
        }
        System.out.printf(TOTAL_TASKS_STRING_FORMAT, taskCount);
        printSeparator();
    }

    public static void printEmptyTaskListMessage() {
        printBotStatusMessage(
                String.format(NO_TASKS_MESSAGE + TOTAL_TASKS_STRING_FORMAT, ZERO_CONSTANT_STRING));
    }

    public static void printStringListMessage(String[] stringList, int stringCount) {
        printSeparator();
        for (int i = 0; i < stringCount; i++) {
            int stringIndex = i + 1;
            System.out.printf("%d. %s\n", stringIndex, stringList[i]);
        }
        printSeparator();
    }

    public static void printBotStatusMessage(String line) {
        System.out.println(SEPARATOR + line + SEPARATOR);
    }

    public static void printNormalMessage(String line) {
        System.out.println(line);
    }

    public static void printDebugMessage(String line) {
        System.out.printf("Debug message:%s\n", line);
    }

    public static void printBotExceptionMessage(String line) {
        System.out.printf(ERROR_SEPARATOR + "Exception message:%s\n" + ERROR_SEPARATOR, line);
    }
}
