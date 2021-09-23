package manager;

import task.Task;

import java.util.ArrayList;

/**
 * Represents PrintManager. Handles any command line output for user to view.
 */
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

    /**
     * Prints a message to welcome the user.
     */
    public static void printWelcomeUser() {
        System.out.println(LOGO + SEPARATOR + HELLO_GREETING + SEPARATOR);
    }

    /**
     * Prints a message to say bye to user.
     */
    public static void printByeUser() {
        System.out.println(SEPARATOR + EXIT_GREETING + SEPARATOR);
    }

    /**
     * Prints a message to prompt for user input.
     */
    public static void promptUserForCommand() {
        System.out.println("\nType your command:\n");
    }

    /**
     * Prints a line separator.
     */
    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the entire task list and total count of tasks.
     *
     * @param taskList The task list.
     * @param taskCount The task count.
     */
    public static void printTaskListMessage(ArrayList<Task> taskList, int taskCount) {
        printSeparator();
        for (Task task : taskList) {
            System.out.printf("%d. %s\n", taskList.indexOf(task) + 1, task);
        }
        printTaskCountLeft(taskCount);
        printSeparator();
    }

    /**
     * Prints the task count remaining in the task list.
     *
     * @param taskCount The task count remaining.
     */
    public static void printTaskCountLeft(int taskCount) {
        System.out.printf(TOTAL_TASKS_STRING_FORMAT, taskCount);
    }

    /**
     * Prints empty task list message.
     */
    public static void printEmptyTaskListMessage() {
        printBotStatusMessage(
                String.format(NO_TASKS_MESSAGE + TOTAL_TASKS_STRING_FORMAT, ZERO_CONSTANT_STRING));
    }

    /**
     * Prints the strings in the string array.
     *
     * @param stringList The array of string to be printed.
     * @param stringCount The number of strings in the array.
     */
    public static void printStringListMessage(String[] stringList, int stringCount) {
        printSeparator();
        for (int i = 0; i < stringCount; i++) {
            int stringIndex = i + 1;
            System.out.printf("%d. %s\n", stringIndex, stringList[i]);
        }
        printSeparator();
    }

    /**
     * Prints bot status message line, to show the status of the program any point during execution.
     *
     * @param line The bot status message line.
     */
    public static void printBotStatusMessage(String line) {
        System.out.println(SEPARATOR + line + SEPARATOR);
    }

    /**
     * Prints any normal program string message.
     *
     * @param line The line for any program string message.
     */
    public static void printNormalMessage(String line) {
        System.out.println(line);
    }

    /**
     * Unused function.
     * Prints Debugging messages.
     *
     * @param line The debug message line.
     */
    public static void printDebugMessage(String line) {
        System.out.printf("Debug message:%s\n", line);
    }

    /**
     * Prints Exception message.
     *
     * @param line The exception message line.
     */
    public static void printBotExceptionMessage(String line) {
        System.out.printf(ERROR_SEPARATOR + "Exception message:%s\n" + ERROR_SEPARATOR, line);
    }
}
