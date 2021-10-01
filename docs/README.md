# User Guide

Duke is a **Command Line Interface** (CLI) bot made for students who are forgetful and want to keep up with their daily tasks. Fast typers will benefit the most out of this as Duke is able to get tasks faster than normal GUI apps.

* [Quick Setup](#quick-setup)
* [Features](#features)
  * [Viewing Help: `help`](#viewing-help--help)
  * [List all tasks: `list`](#list-all-tasks--list)
  * [Add a task : `add`](#add-a-task--add)
  * [Add a Todo task: : `todo`](#add-a-todo-task--todo)
  * [Add a Deadline task: `deadline`](#add-a-deadline-task--deadline)
  * [Add an Event task: `event`](#add-an-event-task--event)
  * [Done a task: `done`](#done-a-task--done)
  * [Delete a task: `delete`](#delete-a-task--delete)
  * [Find a task by keyword: `find`](#find-a-task-by-keyword--find)
  * [View user command history: `hist`](#view-user-command-history--hist)
  * [Bye Duke: `bye`](#bye-duke--bye)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
  * [Tasks saved format in duke.txt](#tasks-saved-format-in-duketxt)
* [FAQs](#faqs)
* [Command Summary](#command-summary)

## Quick Setup
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/t-l-xin/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for your Duke program.
4. Open a terminal from the *home folder* of your program.
5. Start by typing `java -jar ip.jar`. The output of the program would be as shown below.

    ```md
     ______________
    |            @ | #####  ####        #####   ####
    |         @@   |   #   #    #       #    # #    #
    |       @@     |   #   #    # ##### #    # #    #
    |  @@  @@      |   #   #    #       #    # #    #
    |   @@@        |   #   #    #       #    # #    #
    |    @         |   #    ####        #####   ####
     --------------
    ____________________________________________________________
    Hi! I'm Duke! 
    Your personal bot to make sure you do work.
    Have you started ur tasks? Start by typing "help"
    ____________________________________________________________
    
    loading saved files...
    
    ____________________________________________________________
    no pre-existing data files yet
    ____________________________________________________________
    
    
    Type your command:
    
    
    ```

6. You may then start typing your commands under the "Type your command:" prompt.
7. Refer to Features below for details of each command. 

## Features

### *Notes*
* Words in UPPER_CASE are the parameters to be supplied by the user. 
  * e.g. in `todo [TASK_DESCRIPTION]`, `TASK_DESCRIPTION` is a parameter which can be used as `todo CS2101 slides` 

* Items in square brackets are needed. 
  * e.g. `todo [TASK_DESCRIPTION]` requires the `[TASK_DESCRIPTION]` for the program to save the task to the task list. 

* Any fields that requires dates such as `[DATE_TO_BE_COMPLETED]` or `[DATE_OF_EVENT]` should be filled in this format: `[DD/MM/YYYY HHMM]`

* For adding Deadline and Event tasks, both require their respective delimiters `/by` and `/at` in order for Duke to add the tasks to the task list.

### Viewing Help : `help`

User type `help` and Duke will output a list of commands available.

Format: `help`

Expected outcome:

Shows a list of commands available and some additional arguments and format required to ensure command can be parsed by Duke.


```md
Type your command:

help

____________________________________________________________

How to use this bot:
Type ur command in the following format
cmd [args] /[delimiter] [additional args]

____________________________________________________________

1. help - Displays help information such as commands available

2. list - Displays list of tasks

3. add [TASK_DESCRIPTION] - Add a normal task

4. todo [TASK_DESCRIPTION] - Add a Todo task

5. deadline [TASK_DESCRIPTION] /by [DATE_TO_BE_COMPLETED] - Add a Deadline task
Input DateTime format: DD/MM/YYYY HHMM

6. event [TASK_DESCRIPTION] /at [DATE_OF_EVENT] - Add a Event task
Input DateTime format: DD/MM/YYYY HHMM

7. done [TASK_NUMBER] - Mark a task as done

8. delete [TASK_NUMBER] - Delete a task from the task list

9. find [TASK_KEYWORD] - Find a task by a keyword

10. hist - Displays list of previous user input commands

11. bye - Exit Duke program

____________________________________________________________
```


### List all tasks : `list`

User type `list` and Duke will list all the tasks in the list.

Format: `list`

Expected outcome:
Outcome shows a list of tasks.

```
Type your command:

list

____________________________________________________________
 
1. [T][ ] return book
2. [D][ ] CS2113 iP (by: Oct 01 2021 23:59 PM)
3. [D][ ] CS2106 lab (by: Sep 22 2021 14:00 PM)
4. [D][ ] CS2106 tutorial sheet (by: Sep 13 2021 09:00 AM)
5. [E][ ] CS3103 lab (at: Sep 15 2021 12:00 PM)
6. [E][ ] CS2113 group meet (at: Sep 18 2021 21:00 PM)

Total Tasks: 6
____________________________________________________________
```


### Add a task : `add`

User type `add read book` and Duke adds the task to the list.   

Format: `add [TASK_DESCRIPTION]`

Expected outcome:

Duke adds task to task list and print the added status of the task to the task list.


```
Type your command:

add read book

____________________________________________________________
adding: read book
____________________________________________________________

/data directory created
duke.txt created
Successfully wrote to the file.
```


### Add a Todo task : `todo`

User type `todo return book`and Duke adds a Todo task to the list.

Format: `todo [TASK_DESCRIPTION]`

Expected outcome:

Duke adds Todo task to the task list and print the added status of the Todo task.


```
Type your command:

todo return book

____________________________________________________________
adding: return book
____________________________________________________________

/data directory exists
duke.txt exists
Successfully wrote to the file.
```


### Add a Deadline task : `deadline`

User type `deadline essay reading /by 10/10/2021 1500` and Duke adds a Deadline task to the list.

Format: `deadline [TASK_DESCRIPTION] /by [DATE_TO_BE_COMPLETED]`

Expected outcome:

Duke adds Deadline task to the task list and print the added status of the Deadline task.


```
Type your command:

deadline CS2113 iP /by 01/10/2021 2359

____________________________________________________________
adding: CS2113 iP /by 01/10/2021 2359
____________________________________________________________

/data directory exists
duke.txt exists
Successfully wrote to the file.
```


### Add an Event task : `event`

User type `event group meeting /at 11/09/2021 0900` and Duke adds a Event task to the list.

Format: `event [TASK_DESCRIPTION] /at [DATE_OF_EVENT]`

Expected outcome:

Duke adds Event task to the task list and print the added status of the Event task.


```
Type your command:

event CS2113 group meeting /at 18/09/2021 2100

____________________________________________________________
adding: CS2113 group meeting /at 18/09/2021 2100
____________________________________________________________

/data directory exists
duke.txt exists
Successfully wrote to the file.
```


### Done a task : `done`

User type `done 2` and Duke will output that task 2 is done.

Format: `done [TASK_NUMBER]`

Expected outcome:

Duke marks the task specified by the task number as done, and outputs the name of the task that is done.


```
Type your command:

done 2

____________________________________________________________
Good Job, u have completed
task: CS2113 iP
____________________________________________________________

/data directory exists
duke.txt exists
Successfully wrote to the file.

```


### Delete a task : `delete`

User type `delete 1` and Duke deletes task 1.

Format: `delete [TASK_NUMBER]`

Expected outcome:

Duke deletes task from its list and outputs the task name of the task deleted.


```
Type your command:

delete 5

____________________________________________________________
Removed task:
[E][ ] CS3103 lab (at: Sep 15 2021 12:00 PM)
____________________________________________________________

/data directory exists
duke.txt exists
Successfully wrote to the file.

```

### Find a task by keyword : `find`

User type `find cs2113` and Duke output a list of tasks that contains the user input keyword `cs2113`.

Format: `find [TASK_KEYWORD]`

Expected outcome:
Duke filters the task list and displays a list of tasks that contains the user input keyword. 

```
Type your command:

find cs2113

____________________________________________________________

1. [D][X] CS2113 iP (by: Oct 01 2021 23:59 PM)
2. [E][ ] CS2113 group meet (at: Sep 18 2021 21:00 PM)

Total Tasks: 2
____________________________________________________________

```

### View user command history : `hist`

User type `hist` and Duke output a list of commands the user previously input for the current session.

Format: `hist`

Expected outcome:
Shows a list of user commands previously input for the current session.


```
Type your command:

hist

____________________________________________________________

1. todo return book
2. deadline CS2113 iP /by 01/10/2021 2359
3. list
4. deadline CS2106 lab /by 22/09/2021 1400
5. deadline CS2106 tutorial sheet /by 13/09/2021 0900
6. list
7. event CS3103 lab /at 15/09/2021 1200
8. event CS2113 group meet /at 18/09/2021 2100
9. list
10. done 3
11. delete 6
12. find CS2113
13. hist

____________________________________________________________
```


### Bye Duke : `bye`

User type `bye` and Duke program is to exit.

Format: `bye`

Expected outcome:

Duke receives signal to exit and terminates by printing an exit message.


```
Type your command:

bye

____________________________________________________________
Well done for today! Remember to have 8 hrs of sleep!
____________________________________________________________
```


### Saving the data

All Duke data are saved in the hard disk automatically after any command that requires changing the data. There is no need to save manually.

### Editing the data file

Duke data are saved as a TXT file in **`[home folder]/data/duke.txt`**. Advanced users are welcome to update data directly by editing that data file.
***home folder*** refers to the folder where you initially placed `ip.jar`, mentioned at the Quick Setup section.

**Word of Caution:**

After editing `duke.txt`, users are recommended to ensure that the task format follows the task saved format for files shown in the [next section](#tasks-saved-format-in-duketxt), else there may be errors while loading from `duke.txt`.

### Tasks saved format in duke.txt

This section specifies the format that applies to editing format in duke.txt only,
this subsection is not applicable for user input command line format.

*While editing duke.txt, ensure tasks format follows the below formats.* 
*Failure to follow the format, results in line parsing exceptions when loading task data from duke.txt*

- Todo Tasks are saved in the following format: 
`[TASK_TYPE]|[TASK_STATUS]|[TASK_DESCRIPTION]`
  - Todo Tasks details must be separated by 2 `|`, all 3 fields in `[]` in the format must be present
  - E.g. `T|0|return book` means Task: return book is not done

- Deadline Tasks are saved in the following format: 
`[TASK_TYPE]|[TASK_STATUS]|[TASK_DESCRIPTION]|[DATE_TO_BE_COMPLETED]`
  - Deadline Tasks details must be separated by 3 `|`, all 4 fields in `[]` in the format must be present   
  - E.g. `D|1|CS2106 lab|22/09/2021 1400` means Deadline Task: CS2106 lab is done before deadline of 22 Sep 2021 2pm. 

- Event Tasks are saved in the following format: 
`[TASK_TYPE]|[TASK_STATUS]|[TASK_DESCRIPTION]|[DATE_OF_EVENT]`
  - Event Tasks details must be separated by 3 `|`, all 4 fields in `[]` in the format must be present
  - E.g. `E|0|CS3103 group meeting|30/10/2021 1900` means Event Task: CS3103 group meeting is not done and is held on 30 Sep 2021 7pm.

TASK_TYPE | TASK_STATUS | TASK_DESCRIPTION | DATE_TO_BE_COMPLETED <br> or DATE_OF_EVENT
------------ | ------------- | ------------- | -------------
Todo: `T` <br/> Deadline: `D` <br/> Event: `E` <br/> | Done: `1` <br/> Not Done: `0` <br/> | Describe the task <br/> e.g. CS3103 programming assignment | format must be in `DD/MM/YYYY HHMM` <br> e.g. "18 Sept 2021, 6pm", is written as `18/09/2021 1800`

## FAQs

**Question:** Why cannot load saved file (duke.txt) properly? 

**Answer:** Things to check
- Check that there are tasks data is stored in duke.txt. 
- Check that you followed the format for tasks saved format in duke.txt, refer to [this section](#tasks-saved-format-in-duketxt)
- Check that `duke.txt` is in a folder `/data` and the `/data` folder must be in the same directory as `ip.jar`

**Question:** How can I transfer my files to another Computer?

**Answer:** 
1. Move both `/data` folder and `ip.jar` to your new working environment folder 
2. Ensure that `/data` folder and `ip.jar` are in the same folder
3. Ensure that duke.txt is in `/data` folder and that's all! 
You can start Duke after you have completed these steps.

## Command Summary


Action | Format Examples
------------ | -------------
Help | `help`
List | `list`
Add |`add [TASK_DESCRIPTION]` <br /> e.g. `add read book` 
Todo |`todo [TASK_DESCRIPTION]` <br /> e.g. `todo return book` 
Deadline |`deadline [TASK_DESCRIPTION] /by [DATE_TO_BE_COMPLETED]` <br /> e.g. `deadline CS2106 lab /by 22/09/2021 1400` 
Event |`event [TASK_DESCRIPTION] /by [DATE_OF_EVENT]` <br /> e.g. `event CS2113 group meeting /at 05/09/2021 1800`
Done | `done [TASK_NUMBER]` <br /> e.g. `done 3`
Delete | `delete [TASK_NUMBER]` <br /> e.g. `delete 1`
Find | `find [TASK_KEYWORD]` <br /> e.g. `find tutorial`
History | `hist`
Bye | `bye`

[Back to the top](#user-guide)
