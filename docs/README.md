# User Guide

Duke is a **Command Line Interface** (CLI) bot made for students who are forgetful and want to keep up with their daily tasks. Fast typers will benefit the most out of this as Duke is able to get tasks faster than normal GUI apps.

* [Quick Setup](#quick-setup)
* [Features](#features)
  * [Viewing Help](#viewing-help--help)
  * [List all tasks](#list-all-tasks--list)
  * [Add a task](#add-a-task--add)
  * [Add a Todo task](#add-a-todo-task--todo)
  * [Add a Deadline task](#add-a-deadline-task--deadline)
  * [Add an Event task](#add-an-event-task--event)
  * [Done a task](#done-a-task--done)
  * [Delete a task](#delete-a-task--delete)
  * [View user command history](#view-user-command-history--hist)
  * [Bye Duke](#bye-duke--bye)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
* [Command Summary](#command-summary)

## Quick Setup
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/t-l-xin/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for your Duke program.
4. Open a terminal from the *home folder* of your program.
5. Start by typing `java -jar ip.jar`. The output of the program would be as shown below.
```
 ______________
|            @ | #####  ####        #####   ####
|         @@   |   #   #    #       #    # #    #
|       @@     |   #   #    # ##### #    # #    #
|  @@  @@      |   #   #    #       #    # #    #
|   @@@        |   #   #    #       #    # #    #
|    @         |   #    ####        #####   ####
 --------------
 
Hi! I'm Duke! 
Your personal bot to make sure you do work.
Have you started ur tasks? Start by typing "help"
____________________________________________________________
 
How to use this bot:
Type ur command in the following format
cmd [args] /[options] [additional args]

____________________________________________________________

1. help - Displays help information such as commands available
2. list - Displays list of tasks
3. add [task description] - Add a normal task
4. todo [task description] - Add a Todo task
5. deadline [task description] /by DD/MM/YYYY HHMM - Add a Deadline task
6. event [task description] /at DD/MM/YYYY HHMM - Add a Event task
7. done [TASK_number] - Mark a task as done
8. delete [TASK_number] - Delete a task from the task list
9. hist - Displays list of previous user input commands
10. bye - Exit Duke program

____________________________________________________________

loading saved files...

____________________________________________________________
```

6. You may then start typing your commands under the "Type ur command:" prompt.
7. Refer to Features below for details of each command. 

## Features

### *Notes*
* Words in UPPER_CASE are the parameters to be supplied by the user. </br> e.g. in `todo [TASK_DESCRIPTION]`, `TASK_DESCRIPTION` is a parameter which can be used as `todo CS2101 slides` 
* Items in square brackets are needed. </br> e.g. `todo [TASK_DESCRIPTION]` requires the `[TASK_DESCRIPTION]` for the program to save the task to the task list. 
* Any fields that requires dates such as `[DATE_TO_BE_COMPLETED]` or `[DATE_OF_EVENT]` should be filled in this format: `[DD/MM/YYYY HHMM]`
* For adding Deadline and Event tasks, both require their respective delimiters `/by` and `/at` in order for Duke to add the tasks to the task list.

### Viewing Help : `help`

User type `help` and Duke will output a list of commands available.

Example of usage: `help`

Expected outcome:

Shows a list of commands available and some additional arguments and format required to ensure command can be parsed by Duke.


```
How to use this bot:
Type ur command in the following format
cmd [args] /[options] [additional args]

____________________________________________________________

1. help, list, hist - no additional arguments required
2. done [task no]
3. delete [task no]
4. add [task description]
5. todo [task description]
6. deadline [task description] /by YYYY/MM/DD hh:mm
7. event [task description] /at YYYY/MM/DD hh:mm

____________________________________________________________

```


### List all tasks : `list`

User type `list` and Duke will list all the tasks in the list.

Example of usage:
`list`

Expected outcome:
Outcome shows a list of tasks.

```
____________________________________________________________

1. [E][ ] meet at sch (at: 22 08 2021 08:00 AM)
2. [D][ ] CS2106 lab (by: 22 09 2021 14:00 PM)

Total Tasks: 2
____________________________________________________________
```


### Add a task : `add`

User type `add read book` and Duke adds the task to the list.   

Example of usage: `add [TASK_DESCRIPTION]`

Expected outcome:

Duke adds task to task list and print the added status of the task to the task list.


```
____________________________________________________________
adding: read book
____________________________________________________________

data folder exists
duke.txt exists
Successfully wrote to the file.
```


### Add a Todo task : `todo`

User type `todo return book`and Duke adds a Todo task to the list.

Example of usage: `todo [TASK_DESCRIPTION]`

Expected outcome:

Duke adds Todo task to the task list and print the added status of the Todo task.


```
____________________________________________________________
adding: return book
____________________________________________________________

data folder exists
duke.txt exists
Successfully wrote to the file.
```


### Add a Deadline task : `deadline`

User type `deadline essay reading /by 10/10/2021 1500` and Duke adds a Deadline task to the list.

Example of usage: `deadline [TASK_DESCRIPTION] /by [DATE_TO_BE_COMPLETED]`

Expected outcome:

Duke adds Deadline task to the task list and print the added status of the Deadline task.


```
____________________________________________________________
adding: CS2106 lab /by 22/09/2021 1400
____________________________________________________________

data folder exists
duke.txt exists
Successfully wrote to the file.
```


### Add an Event task : `event`

User type `event group meeting /at 11/09/2021 0900` and Duke adds a Event task to the list.

Example of usage: `event [TASK_DESCRIPTION] /at [DATE_OF_EVENT]`

Expected outcome:

Duke adds Event task to the task list and print the added status of the Event task.


```
____________________________________________________________
adding: CS2113 group meeting /at 18/09/2021 2100
____________________________________________________________

data folder exists
duke.txt exists
Successfully wrote to the file.
```


### Done a task : `done`

User type `done 2` and Duke will output that task 2 is done.

Example of usage: `done [TASK_NUMBER]`

Expected outcome:

Duke marks the task specified by the task number as done, and outputs the name of the task that is done.


```
____________________________________________________________
Good Job, u have completed
task: lab
____________________________________________________________

data folder exists
duke.txt exists
Successfully wrote to the file.

```


### Delete a task : `delete`

User type `delete 1` and Duke deletes task 1.

Example of usage: `delete [TASK_NUMBER]`

Expected outcome:

Duke deletes task from its list and outputs the task name of the task deleted.


```
____________________________________________________________
Removed task:
[D][X] lab (by: 22 09 2021 14:00 PM)
____________________________________________________________

data folder exists
duke.txt exists
Successfully wrote to the file.
```


### View user command history : `hist`

User type `hist` and Duke output a list of commands the user previously input for the current session.

Example of usage:
`hist`

Expected outcome:
Shows a list of user commands previously input for the current session.


```
____________________________________________________________

1. help
2. list
3. hist

____________________________________________________________
```


### Bye Duke : `bye`

User type `bye` and Duke program is to exit.

Example of usage: `bye`

Expected outcome:

Duke receives signal to exit and terminates by printing an exit message.


```
____________________________________________________________
Well done for today! Remember to have 8 hrs of sleep!
____________________________________________________________
```


### Saving the data

All Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duke data are saved as a TXT file [*home folder*]/data/duke.txt. Advanced users are welcome to update data directly by editing that data file.
*home folder* refers to the folder where you initially placed ip.jar, mentioned at the Quick Setup section.

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
History | `hist`
Bye | `bye`

[Back to the top](#user-guide)
