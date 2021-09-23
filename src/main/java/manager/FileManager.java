package manager;

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

public class FileManager {
    public static final String PIPE_CHARACTER = "\\|";
    public static final String TASK_COMPLETED_STATUS_NO_1_STRING = "1";

    public static final char ADD_CHARACTER = 'A';
    public static final char DEADLINE_CHARACTER = 'D';
    public static final char EVENT_CHARACTER = 'E';
    public static final char TODO_CHARACTER = 'T';
    public static final int CHARACTER_AT_INDEX_ZERO = 0;
    public static final int CHARACTER_AT_INDEX_TWO = 2;
    public static final int INTEGER_ONE = 1;
    public static final int INTEGER_TWO = 2;
    public static final int INTEGER_ZERO = 0;

    String currentUsersWorkingDir = System.getProperty("user.dir");
    public static final String DATA_FOLDER_STRING = "data";
    public static final String DUKE_TXT_FILE_STRING = "duke.txt";
    File dataDirName = new File(DATA_FOLDER_STRING);
    File savedFileName = new File(currentUsersWorkingDir, DATA_FOLDER_STRING + File.separator + DUKE_TXT_FILE_STRING);
    private int taskCountFromFile;
    ArrayList<Task> taskListFromFile = new ArrayList<Task>();

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
    }

    private void initialiseTaskFromSavedFile(String line, TaskManager tm) {
        char firstCharacterOfLine = line.charAt(CHARACTER_AT_INDEX_ZERO);
        String[] detailsArr;
        switch (firstCharacterOfLine) {
        case ADD_CHARACTER:
            detailsArr = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(detailsArr, ADD);
            break;
        case DEADLINE_CHARACTER:
            detailsArr = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(detailsArr, DEADLINE);
            break;
        case EVENT_CHARACTER:
            detailsArr = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(detailsArr, EVENT);
            break;
        case TODO_CHARACTER:
            detailsArr = separateByPipeCharacter(line.substring(CHARACTER_AT_INDEX_TWO));
            addTaskFromSavedFile(detailsArr, TODO);
            break;
        default:
            PrintManager.printNormalMessage(firstCharacterOfLine + " does not match any tasks initials");
            break;
        }
    }

    public String[] separateByPipeCharacter(String line) {
        String[] detailsArr = line.split(PIPE_CHARACTER);
        return detailsArr;
    }

    public void addTaskFromSavedFile(String[] detailsArr, TaskType taskType) {
        Task newTask = null;
        switch (taskType) {
        case ADD:
            newTask = new Task(detailsArr[INTEGER_ONE]);
            break;
        case TODO:
            newTask = new Todo(detailsArr[INTEGER_ONE]);
            break;
        case DEADLINE:
            newTask = new Deadline(detailsArr[INTEGER_ONE], detailsArr[INTEGER_TWO]);
            break;
        case EVENT:
            newTask = new Event(detailsArr[INTEGER_ONE], detailsArr[INTEGER_TWO]);
            break;
        }
        taskListFromFile.add(newTask);
        initialiseTaskStatusFromSavedFile(detailsArr[INTEGER_ZERO]);
        taskCountFromFile++;
    }

    public void initialiseTaskStatusFromSavedFile(String fileDetailStatus) {
        if (fileDetailStatus.equals(TASK_COMPLETED_STATUS_NO_1_STRING)) {
            taskListFromFile.get(taskCountFromFile).setDone();
        }
    }

    private void checkDirectoryExistOrCreate() {
        if (!dataDirName.exists()) {
            dataDirName.mkdir();
            PrintManager.printNormalMessage("created data folder");
        } else {
            PrintManager.printNormalMessage("data folder exists");
        }
    }

    private void checkSavedFileExistOrCreate() {
        try {
            if (!savedFileName.createNewFile()) {
                PrintManager.printNormalMessage("duke.txt exists");
            } else {
                PrintManager.printNormalMessage("File created: " + savedFileName.getName());
            }
        } catch (IOException e) {
            PrintManager.printBotExceptionMessage(" error creating duke.txt");
        }
    }

    public void saveTasksToFile(ArrayList<Task> taskList, int taskCount) {
        checkDirectoryExistOrCreate();
        checkSavedFileExistOrCreate();
        try {
            writeToSavedFile(taskList, taskCount);
        } catch (FileNotFoundException fe) {
            PrintManager.printBotExceptionMessage(" no data files yet");
        } catch (IOException e) {
            PrintManager.printBotExceptionMessage(" error writing to file");
        }
    }

    private void writeToSavedFile(ArrayList<Task> taskList, int taskCount) throws IOException, FileNotFoundException {
        FileWriter myWriter = new FileWriter(savedFileName);
        for (int i = INTEGER_ZERO; i < taskCount; i++) {
            myWriter.write(String.format("%s\n", taskList.get(i).saveToFileStringFormat()));
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }

}
