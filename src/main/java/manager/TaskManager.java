package manager;

import exception.DukeException;
import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;

import static task.TaskType.ADD;
import static task.TaskType.TODO;
import static task.TaskType.DEADLINE;
import static task.TaskType.EVENT;

public class TaskManager {
    public static final String BY_STRING = "/by";
    public static final String AT_STRING = "/at";
    public static final String PIPE_CHARACTER = "\\|";
    public static final String DATA_FOLDER_STRING = "data";
    public static final String DUKE_TXT_FILE_STRING = "duke.txt";
    public static final String TASK_COMPLETED_STATUS_NO_1 = "1";

    String currentUsersWorkingDir = System.getProperty("user.dir");
    Path dataFolderPath = Paths.get(currentUsersWorkingDir, DATA_FOLDER_STRING);
    Path savedFilePath = Paths.get(currentUsersWorkingDir, DATA_FOLDER_STRING, DUKE_TXT_FILE_STRING);

    ArrayList<Task> taskList = new ArrayList<Task>();
    File dataDirName = new File(DATA_FOLDER_STRING);
    File savedFileName = new File(currentUsersWorkingDir, DATA_FOLDER_STRING + File.separator + DUKE_TXT_FILE_STRING);
    private int taskCount = 0;

    public void filterTasks(String[] cmdArray) {
        PrintManager pm = new PrintManager();
        switch (cmdArray[0]) {
        case "add":
            try {
                addTask(cmdArray[1], ADD);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            saveTasksToFile();
            break;
        case "todo":
            try {
                addTask(cmdArray[1], TODO);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage(" todo task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            saveTasksToFile();
            break;
        case "deadline":
            try {
                addTask(cmdArray[1], DEADLINE);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("deadline task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            saveTasksToFile();
            break;
        case "event":
            try {
                addTask(cmdArray[1], EVENT);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("event task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            saveTasksToFile();
            break;
        case "":
            pm.printBotStatusMessage("no command detected, please try again");
            break;
        default:
            pm.printBotStatusMessage("invalid command, please try again");
            break;
        }
    }

    public Task getTaskType(String newTask, TaskType type) throws DukeException {
        switch (type) {
        case ADD:
            return new Task(newTask);
        case TODO:
            return new Todo(newTask);
        case DEADLINE:
            String[] taskDetailsArray = getTaskNameAndTime(newTask, BY_STRING);
            return new Deadline(taskDetailsArray[0], taskDetailsArray[1]);
        case EVENT:
            String[] taskDetailsArray2 = getTaskNameAndTime(newTask, AT_STRING);
            return new Event(taskDetailsArray2[0], taskDetailsArray2[1]);
        }
        return null;
    }

    public String[] getTaskNameAndTime(String taskDetail, String delimiter)
            throws DukeException, StringIndexOutOfBoundsException {
        checkForDelimiter(taskDetail, delimiter);
        int indexOfDelimiter = taskDetail.indexOf(delimiter);
        checkEmptyDetails(indexOfDelimiter);
        String detail = taskDetail.substring(0, indexOfDelimiter - 1).trim();
        String datetime = taskDetail.substring(indexOfDelimiter + delimiter.length()).trim();
        checkEmptyDateTime(datetime);
        String[] taskDetailsArray = {detail, datetime};
        return taskDetailsArray;
    }

    private void checkEmptyDetails(int indexOfDelimiter) throws DukeException {
        if (indexOfDelimiter == 0) {
            throw new DukeException("\nDuke: can't find details");
        }
    }

    private void checkEmptyDateTime(String datetime) throws DukeException {
        if (datetime.length() == 0) {
            throw new DukeException("\nDuke: can't find datetime");
        }
    }

    private void checkForDelimiter(String taskDetail, String delimiter) throws DukeException {
        if (!taskDetail.contains(delimiter)) {
            throw new DukeException(String.format("\nDuke: can't find %s", delimiter));
        }
    }

    public void addTask(String newTask, TaskType type) throws DukeException {
        Task typ = getTaskType(newTask, type);
        CmdManager.printAddStatus(newTask);
        taskList.add(typ);
        taskCount++;
    }

    public void doneOrDeleteTask(String taskNo, String operation) {
        try {
            executeDoneOrDeleteOperation(taskNo, operation);
        } catch (NullPointerException ne) {
            PrintManager.printBotExceptionMessage(
                    "NullPointerException: please key in a valid index");
        } catch (IndexOutOfBoundsException ae) {
            PrintManager.printBotExceptionMessage(
                    "ArrayIndexOutOfBoundsException: index start from 1");
        }
        saveTasksToFile();
    }

    private void executeDoneOrDeleteOperation(String taskNo, String operation)
            throws NullPointerException, IndexOutOfBoundsException {
        if (operation.equals("done")) {
            markAsDone(taskNo);
        }
        if (operation.equals("delete")) {
            deleteTask(taskNo);
        }
    }

    public void listTasks() {
        if (taskCount > 0) {
            PrintManager.printTaskListMessage(taskList, taskCount);
        } else {
            PrintManager.printEmptyTaskListMessage();
        }
    }

    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        taskList.get(taskIndex).setDone();
        PrintManager.printBotStatusMessage(
                String.format("Good Job, u have completed\ntask: %s", taskList.get(taskIndex).getTaskName()));
    }

    public void deleteTask(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        PrintManager.printBotStatusMessage(
                String.format("Removed task:\n%s", taskList.get(taskIndex)));
        taskList.remove(taskIndex);
        taskCount--;
    }

    public String[] separateByPipeCharacter(String line) {
        String[] detailsArr = line.split(PIPE_CHARACTER);
        return detailsArr;
    }

    public void addTaskFromSavedFile(String[] detailsArr, TaskType taskType) {
        Task newTask = null;
        switch (taskType) {
        case ADD:
            newTask = new Task(detailsArr[1]);
            break;
        case TODO:
            newTask = new Todo(detailsArr[1]);
            break;
        case DEADLINE:
            newTask = new Deadline(detailsArr[1], detailsArr[2]);
            break;
        case EVENT:
            newTask = new Event(detailsArr[1], detailsArr[2]);
            break;
        }
        taskList.add(newTask);
        initialiseTaskStatusFromFile(detailsArr[0]);
        taskCount++;
    }

    public void initialiseTaskStatusFromFile(String fileDetailStatus) {
        if (fileDetailStatus.equals(TASK_COMPLETED_STATUS_NO_1)) {
            taskList.get(taskCount).setDone();
        }
    }

    public void initialiseTaskFromSavedFile(String line) {
        char firstCharacterOfLine = line.charAt(0);
        String[] detailsArr;
        switch (firstCharacterOfLine) {
            case 'A':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromSavedFile(detailsArr, ADD);
                break;
            case 'D':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromSavedFile(detailsArr, DEADLINE);
                break;
            case 'E':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromSavedFile(detailsArr, EVENT);
                break;
            case 'T':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromSavedFile(detailsArr, TODO);
                break;
            default:
                PrintManager.printNormalMessage(firstCharacterOfLine + " does not match any tasks initials");
                break;
        }
    }

    public void loadTasksFromSavedFile() throws IOException, FileNotFoundException {
        FileReader fr = new FileReader(savedFileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            initialiseTaskFromSavedFile(line);
        }
        fr.close();
        listTasks();
    }

    public void checkDirectoryExistOrCreate() {
        if (!dataDirName.exists()) {
            dataDirName.mkdir();
            PrintManager.printNormalMessage("created data folder");
        } else {
            PrintManager.printNormalMessage("data folder exists");
        }
    }

    public void checkSavedFileExistOrCreate() {
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

    public void saveTasksToFile() {
        checkDirectoryExistOrCreate();
        checkSavedFileExistOrCreate();
        try {
            writeToSavedFile();
        } catch (FileNotFoundException fe) {
            PrintManager.printBotExceptionMessage(" no data files yet");
        } catch (IOException e) {
            PrintManager.printBotExceptionMessage(" error writing to file");
        }
    }

    private void writeToSavedFile() throws IOException, FileNotFoundException {
        FileWriter myWriter = new FileWriter(savedFileName);
        for (int i = 0; i < taskCount; i++) {
            myWriter.write(String.format("%s\n", taskList.get(i).saveToFileStringFormat()));
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
}
