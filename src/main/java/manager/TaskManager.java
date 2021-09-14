package manager;

import exception.DukeException;
import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.*;

import static task.TaskType.*;

public class TaskManager {
    public static final int MAX_TASKS_LIMIT = 100;
    public static final String BY_STRING = "/by";
    public static final String AT_STRING = "/at";
    public static final String PIPE_CHARACTER = "\\|";

    private Task[] taskList = new Task[MAX_TASKS_LIMIT];
    private int taskCount = 0;

    File dir = new File("data");
    File savedFile = new File(dir, "duke.txt");

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
            break;
        case "todo":
            try {
                addTask(cmdArray[1], TODO);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage(" todo task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            break;
        case "deadline":
            try {
                addTask(cmdArray[1], DEADLINE);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("deadline task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
            break;
        case "event":
            try {
                addTask(cmdArray[1], EVENT);
            } catch (ArrayIndexOutOfBoundsException ae) {
                PrintManager.printBotExceptionMessage("event task needs details");
            } catch (DukeException de) {
                PrintManager.printBotExceptionMessage(de.getMessage());
            }
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
        if (!taskDetail.contains(delimiter)) {
            throw new DukeException(String.format("\nDuke: can't find %s", delimiter));
        }
        int indexOfDelimiter = taskDetail.indexOf(delimiter);
        if (indexOfDelimiter == 0) {
            throw new DukeException("\nDuke: can't find details");
        }
        //PrintManager.printNormalMessage("index of delimiter: " + indexOfDelimiter);
        String detail = taskDetail.substring(0, indexOfDelimiter - 1).trim();
        String datetime = taskDetail.substring(indexOfDelimiter + delimiter.length()).trim();
        if (datetime.length() == 0) {
            throw new DukeException("\nDuke: can't find datetime");
        }
        String[] taskDetailsArray = {detail, datetime};
        return taskDetailsArray;
    }

    public void addTask(String newTask, TaskType type) throws DukeException {
        Task typ = getTaskType(newTask, type);
        CmdManager.printAddStatus(newTask);
        taskList[taskCount] = typ;
        taskCount++;
    }

    public void listTasks() {
        if (taskCount > 0) {
            PrintManager.printTaskListMessage(taskList, taskCount);
        } else {
            PrintManager.printEmptyTaskListMessage();
        }
    }

    public boolean isValidIndex(int userInputIndex) {
        return userInputIndex < taskCount && userInputIndex >= 0;
    }

    public void markAsDone(String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        taskList[taskIndex].setDone();
        PrintManager.printBotStatusMessage(
                String.format("Good Job, u have completed\ntask: %s", taskList[taskIndex].getTaskName()));
        /**
         if (isValidIndex(taskIndex)) {
         taskList[taskIndex].setDone();
         PrintManager.printBotStatusMessage(
         String.format("Good Job, u have completed\ntask: %s", taskList[taskIndex].getTaskName()));
         } else {
         PrintManager.printBotStatusMessage(
         String.format("\nDuke: invalid task num, please key in a valid task num from 1 - %d", taskCount));
         }
         */
    }

    public String[] separateByPipeCharacter(String line){
        String[] detailsArr = line.split(PIPE_CHARACTER);
        int startIndex = 0;
        System.out.printf("line: %s\n", line);
        for(String detail: detailsArr){
            System.out.printf("[%d] %s ", startIndex, detail);
            startIndex++;
        }

        return detailsArr;
    }

    public void addTaskFromFile(String[] detailsArr, TaskType taskType){
        Task newTask = null;
        switch (taskType){
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
        //CmdManager.printAddStatus(newTask.toString());
        taskList[taskCount] = newTask;
        if(detailsArr[0].equals("1")){
            taskList[taskCount].setDone();
        }
        taskCount++;
    }

    public void initialiseTaskFromFile(String line){
        char firstCharacterOfLine = line.charAt(0);
        System.out.println("first char: " + firstCharacterOfLine);
        String[] detailsArr;
        switch (firstCharacterOfLine){
            case 'A':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromFile(detailsArr, ADD);
                break;
            case 'D':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromFile(detailsArr, DEADLINE);
                break;
            case 'E':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromFile(detailsArr, EVENT);
                break;
            case 'T':
                detailsArr = separateByPipeCharacter(line.substring(2));
                addTaskFromFile(detailsArr, TODO);
                break;
            default:
                System.out.println(firstCharacterOfLine + " does not match any tasks initials");
                break;
        }
    }

    public void loadTasksFromSavedFile() throws IOException {
        try {
            FileReader fr=new FileReader(savedFile);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                initialiseTaskFromFile(line);
            }
            fr.close();
            listTasks();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void checkDirectoryExist() throws IOException {
        if (dir.mkdirs()) {
            System.out.println("Directory created: " + dir.getName());
        } else {
            System.out.println("Directory already exists.");
        }
    }

    public void checkSavedFileExist() throws IOException {
        if (savedFile.createNewFile()) {
            System.out.println("File created: " + savedFile.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public void checkSavedFileCreated(){

        try {
            checkDirectoryExist();
            checkSavedFileExist();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveTasksToFile(){
        checkSavedFileCreated();

        try {
            FileWriter myWriter = new FileWriter(savedFile);
            for (int i = 0; i < taskCount; i++) {
                myWriter.write(String.format("%s\n", taskList[i].saveToFileStringFormat()));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
