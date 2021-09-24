package manager;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskType;
import task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static task.TaskType.ADD;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;
import static task.TaskType.TODO;

import java.io.FileWriter;

/**
 * Represents the FileManager. Contains methods to save tasks to file and load tasks from file.
 */
public class FileManager {
    public static final String PIPE_CHARACTER = "\\|";
    public static final String TASK_COMPLETED_STATUS_STRING = "1";

    public static final char ADD_CHARACTER = 'A';
    public static final char DEADLINE_CHARACTER = 'D';
    public static final char EVENT_CHARACTER = 'E';
    public static final char TODO_CHARACTER = 'T';
    public static final int CHARACTER_AT_INDEX_ZERO = 0;
    public static final int CHARACTER_AT_INDEX_TWO = 2;
    public static final int INTEGER_ONE = 1;
    public static final int INTEGER_TWO = 2;
    public static final int INTEGER_ZERO = 0;

    public static final String currentUsersWorkingDir = System.getProperty("user.dir");
    public static final String DATA_FOLDER_STRING = "data";
    public static final String DUKE_TXT_FILE_STRING = "duke.txt";
    public static final File dataDirName = new File(DATA_FOLDER_STRING);
    public static final File savedFileName = new File(currentUsersWorkingDir,
            DATA_FOLDER_STRING + File.separator + DUKE_TXT_FILE_STRING);
    private int taskCountFromFile;
    public static ArrayList<Task> taskListFromFile = new ArrayList<Task>();

    /**
     * Loads tasks from directory /data and filename duke.txt.
     * After reading the tasks from the file, Task Manager gets the task list and task count.
     * And prints the task list.
     *
     * @param tm The Task Manager object.
     * @throws IOException           If there is error reading the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public void loadTasksFromSavedFile(TaskManager tm) throws IOException, FileNotFoundException {
        FileReader fr = new FileReader(savedFileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            initialiseTaskFromSavedFile(line, tm);
        }
        fr.close();
        tm.getTaskListFromFile(taskListFromFile);
        tm.getTaskCountFromFile(taskCountFromFile);
        tm.listTasks();
        //PrintManager.printBotExceptionMessage("hello");
    }

    /**
     * Initialises the task from the file based on the first character in the line read.
     * The first character determines the task type.
     * Add the details of the task to the arraylist.
     *
     * @param line Each line read from file.
     * @param tm   The Task Manager object.
     */
    private void initialiseTaskFromSavedFile(String line, TaskManager tm) {
        char firstCharacterOfLine = line.charAt(CHARACTER_AT_INDEX_ZERO);
        String[] taskDetailsArray;
        switch (firstCharacterOfLine) {
        case ADD_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(taskDetailsArray, ADD);
            break;
        case DEADLINE_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(taskDetailsArray, DEADLINE);
            break;
        case EVENT_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(taskDetailsArray, EVENT);
            break;
        case TODO_CHARACTER:
            taskDetailsArray = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(taskDetailsArray, TODO);
            break;
        default:
            PrintManager.printNormalMessage(firstCharacterOfLine + " does not match any tasks initials");
            break;
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
     * @param taskDetailsArray a filtered array that contains details of any task.
     * @param taskType         the type of task.
     */
    public void addTaskFromSavedFile(String[] taskDetailsArray, TaskType taskType) {
        Task newTask = null;
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
        taskListFromFile.add(newTask);
        initialiseTaskStatusFromSavedFile(taskDetailsArray[INTEGER_ZERO]);
        taskCountFromFile++;
    }

    /**
     * Initialises task done status based on number.
     * If file status equals 1, task is done, update the task list.
     * Else, task is not done.
     *
     * @param fileDetailStatus The number indicating the dones status of the task
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
        if (!dataDirName.exists()) {
            dataDirName.mkdir();
            PrintManager.printNormalMessage("created data folder");
        } else {
            PrintManager.printNormalMessage("data folder exists");
        }
    }


    /**
     * Check if duke.txt exists by trying to create a new duke.txt file.
     * If exists, print file exists.
     * Else, print file created.
     * Try catch to capture any errors while creating new file.
     */
    private void checkSavedFileExist() throws DukeException {
        try {
            if (!savedFileName.createNewFile()) {
                PrintManager.printNormalMessage("duke.txt exists");
            } else {
                PrintManager.printNormalMessage("File created: " + savedFileName.getName());
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
        System.out.println("Successfully wrote to the file.");
    }

}
