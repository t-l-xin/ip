package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskType;
import task.Todo;

import static task.TaskType.ADD;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;
import static task.TaskType.TODO;

/**
 * Represents the FileManager. Contains methods to save tasks to file and load tasks from file.
 */
public class FileManager {
    private static final String PIPE_CHARACTER = "\\|";
    private static final String TASK_COMPLETED_STATUS_STRING = "1";
    private static final char ADD_CHARACTER = 'A';
    private static final char DEADLINE_CHARACTER = 'D';
    private static final char EVENT_CHARACTER = 'E';
    private static final char TODO_CHARACTER = 'T';
    private static final int CHARACTER_AT_INDEX_ZERO = 0;
    private static final int CHARACTER_AT_INDEX_TWO = 2;
    private static final int INTEGER_ONE = 1;
    private static final int INTEGER_TWO = 2;
    private static final int INTEGER_ZERO = 0;

    private static final String currentUsersWorkingDir = System.getProperty("user.dir");
    private static final String DATA_FOLDER_STRING = "data";
    private static final String DUKE_TXT_FILE_STRING = "duke.txt";
    private static final File dataDirectoryName = new File(DATA_FOLDER_STRING);
    private static final File savedFileName = new File(
            currentUsersWorkingDir, DATA_FOLDER_STRING + File.separator + DUKE_TXT_FILE_STRING);

    private static final String WARNING_MESSAGE =
            "\n\nIf you choose to continue, duke.txt will be overwritten with a new set of data."
            + "\n\nElse, if you still wish to use the saved data,"
            + "\n1. Exit program by typing \"bye\"."
            + "\n2. Edit duke.txt to follow the task format for saved files."
            + "\n3. Restart program again.";
    private static final String UNABLE_TO_LOAD_DATA_CORRECTLY_MESSAGE_FORMAT = "Unable to load all data from file"
            + "\n\nPlease check task \"%s\" is saved in correct format." + WARNING_MESSAGE;
    private static final String DOES_NOT_MATCH_TASK_MESSAGE_FORMAT =
            "\"%s\" does not match any tasks initials\nLine: \"%s\" will be ignored";
    public static final String REMOVE_EMPTY_LINE_MESSAGE =
            "Line empty\nPlease remove empty lines in duke.txt" + WARNING_MESSAGE;

    private int taskCountFromFile;
    private static ArrayList<Task> taskListFromFile = new ArrayList<Task>();

    /**
     * Loads tasks from /data/duke.txt.
     * After reading the tasks from the file, Task Manager gets the task list and task count.
     * And prints the task list.
     *
     * @param tm The Task Manager object.
     * @throws IOException           If there is error reading the file.
     * @throws FileNotFoundException If the file does not exist.
     * @throws DukeException         If the line read from file does not follow saved file format.
     */
    public void loadTasksFromSavedFile(TaskManager tm) throws IOException, FileNotFoundException, DukeException {
        FileReader fr = new FileReader(savedFileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            initialiseTaskFromSavedFile(line.trim());
        }
        fr.close();
        tm.getTaskListFromFile(taskListFromFile);
        tm.getTaskCountFromFile(taskCountFromFile);
        tm.listTasks();
    }

    /**
     * Initialises the task from the file based on the first character in the line read.
     * The first character determines the task type.
     * Add the details of the task to the arraylist.
     *
     * @param line Each line read from file.
     * @throws DukeException If line read from file does not follow correct format.
     */
    private void initialiseTaskFromSavedFile(String line) throws DukeException {
        char firstCharacterOfCommandLine = getFirstCharacterOfCommandLine(line);
        String[] taskDetailsArray;

        switch (firstCharacterOfCommandLine) {
        case ADD_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(line, taskDetailsArray, ADD);
            break;
        case DEADLINE_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(line, taskDetailsArray, DEADLINE);
            break;
        case EVENT_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(line, taskDetailsArray, EVENT);
            break;
        case TODO_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(line, taskDetailsArray, TODO);
            break;
        default:
            PrintManager.printNormalMessage(
                    String.format(DOES_NOT_MATCH_TASK_MESSAGE_FORMAT, firstCharacterOfCommandLine, line));
            break;
        }
    }

    /**
     * Gets the first character from each line read from file duke.txt.
     *
     * @param line Each line read from duke.txt.
     * @return The first character in the line.
     * @throws DukeException If Line is empty.
     */
    private char getFirstCharacterOfCommandLine(String line) throws DukeException {
        char firstCharacter;
        try {
            firstCharacter = line.charAt(CHARACTER_AT_INDEX_ZERO);
            return firstCharacter;
        } catch (StringIndexOutOfBoundsException se) {
            throw new DukeException(REMOVE_EMPTY_LINE_MESSAGE);
        }
    }

    /**
     * Separates the line read by pipe character.
     *
     * @param line Each line read from file.
     * @return taskDetailsArray An array containing the task details.
     */
    public String[] separateByPipeCharacter(String line) {
        String[] taskDetailsArray = line.split(PIPE_CHARACTER);
        return taskDetailsArray;
    }

    /**
     * Creates new Task object based on the task type.
     * Then adds the new task to the task arraylist and sets the done status of the task.
     * Then, increment the task count by 1.
     *
     * @param taskDetailsArray A filtered array that contains details of any task.
     * @param taskType         The type of task.
     * @throws DukeException If the line read from file does not follow any task file saved format.
     */
    public void addTaskFromSavedFile(String line, String[] taskDetailsArray, TaskType taskType) throws DukeException {
        Task newTask = null;

        try {
            switch (taskType) {
            case ADD:
                newTask = new Task(taskDetailsArray[INTEGER_ONE]);
                break;
            case TODO:
                newTask = new Todo(taskDetailsArray[INTEGER_ONE]);
                break;
            case DEADLINE:
                newTask = new Deadline(taskDetailsArray[INTEGER_ONE], taskDetailsArray[INTEGER_TWO]);
                break;
            case EVENT:
                newTask = new Event(taskDetailsArray[INTEGER_ONE], taskDetailsArray[INTEGER_TWO]);
                break;
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeException(String.format(UNABLE_TO_LOAD_DATA_CORRECTLY_MESSAGE_FORMAT, line));
        }

        taskListFromFile.add(newTask);
        initialiseTaskStatusFromSavedFile(taskDetailsArray[INTEGER_ZERO]);
        taskCountFromFile++;
    }

    /**
     * Initialises task done status based on number.
     * If file status equals 1, task is done, update the task list.
     * Else, task is not done.
     *
     * @param fileDetailStatus The number indicating the done status of the task.
     */
    public void initialiseTaskStatusFromSavedFile(String fileDetailStatus) {
        if (fileDetailStatus.equals(TASK_COMPLETED_STATUS_STRING)) {
            taskListFromFile.get(taskCountFromFile).setDone();
        }
    }

    /**
     * Checks if the directory /data exists.
     * If directory does not exist, create the directory.
     * Else, print directory exists.
     */
    private void checkDirectoryExist() {
        if (!dataDirectoryName.exists()) {
            dataDirectoryName.mkdir();
            PrintManager.printNormalMessage("/data directory created");
        } else {
            PrintManager.printNormalMessage("/data directory exists");
        }
    }

    /**
     * Check if duke.txt exists by trying to create a new duke.txt file.
     * If exists, print file exists.
     * Else, print file created.
     *
     * @throws DukeException If error creating file.
     */
    private void checkSavedFileExist() throws DukeException {
        try {
            if (!savedFileName.createNewFile()) {
                PrintManager.printNormalMessage("duke.txt exists");
            } else {
                PrintManager.printNormalMessage("duke.txt created");
            }
        } catch (IOException e) {
            throw new DukeException(" error creating duke.txt");
        }
    }

    /**
     * Save the tasks to file.
     * Check if both directory and file exists.
     * Try to write to file and catch exceptions.
     *
     * @param taskList  The task list.
     * @param taskCount The task count.
     * @throws DukeException If error writing to duke.txt.
     */
    public void saveTasksToFile(ArrayList<Task> taskList, int taskCount) throws DukeException {
        checkDirectoryExist();
        checkSavedFileExist();
        try {
            writeToSavedFile(taskList, taskCount);
        } catch (FileNotFoundException fe) {
            throw new DukeException(" no data files yet");
        } catch (IOException e) {
            throw new DukeException(" error writing to file");
        }
    }

    /**
     * Writes the task list to duke.txt.
     * Iterates through the task list, get the string format to be saved to file, and write to file.
     *
     * @param taskList  The task list.
     * @param taskCount The task count.
     * @throws IOException           If error writing to file.
     * @throws FileNotFoundException If duke.txt do not exists.
     */
    private void writeToSavedFile(ArrayList<Task> taskList, int taskCount)
            throws IOException, FileNotFoundException {
        FileWriter myWriter = new FileWriter(savedFileName);
        for (int i = INTEGER_ZERO; i < taskCount; i++) {
            myWriter.write(String.format("%s\n", taskList.get(i).saveToFileStringFormat()));
        }
        myWriter.close();
        PrintManager.printNormalMessage("Successfully wrote to the file.");
    }

}
