# User Guide

Duke is a **Command Line Interface** (CLI) bot made for students who are forgetful and want to keep up with their daily tasks. Fast typers will benefit the most out of this as Duke is able to get tasks faster than normal GUI apps.
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

1. help, list, hist - no additional arguments required
2. done [task no]
3. delete [task no]
4. add [task description]
5. todo [task description]
6. deadline [task description] /by YYYY/MM/DD hh:mm
7. event [task description] /at YYYY/MM/DD hh:mm

____________________________________________________________

```
6. You may then start typing your commands under the "Type ur command:" prompt.
7. Refer to Features below for details of each command. 

## Features

### *Notes*


### List all tasks : `list`

User type `list` and does not require additional arguments.

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

### Viewing Help : `help`

User type `help` and Duke will output a list of commands available.

Example of usage: `help`

Expected outcome:

Shows a list of commands available and some additional arguments and format required to ensure command can be parsed by Duke.

```

```


### Viewing history of user commands : `hist`

User type `hist` and Duke output a list of commands the user previously input for the current session.

Example of usage:
`hist`

Expected outcome:
Shows a list of user commands previously input for the current session.

```

```

### Done a task : `done`

User type `done 2` and Duke will output that task 2 is done.

Example of usage: `done [TASK NUMBER]`

Expected outcome:

Duke marks the task specified by the task number as done, and outputs the name of the task that is done.

```
expected output
```

### Delete a task : `delete`

User type `delete 1` and Duke deletes task 1. 

Example of usage: `delete [TASK NUMBER]`

Expected outcome:

Duke deletes task from its list and outputs the task name of the task deleted.

```
expected output
```

### Add a normal task : `add`

Add a task to the list.   

Example of usage: `add [TASK DESCRIPTION]`

Expected outcome:

Duke adds task to task list and print the added status of the task to the task list.

```
expected output
```

### Add a Todo task : `todo`



Example of usage: `todo [TASK DESCRIPTION]`

Expected outcome:

Description of the outcome.

```
expected output
```

### Add a Deadline task : `deadline`

Describe the action and its outcome.

Example of usage: `deadline [TASK DESCRIPTION] /by [DD/MM/YYYY HHMM]`

Expected outcome:

Description of the outcome.

```
expected output
```

### Add an Event task : `event`

Describe the action and its outcome.

Example of usage: `event [TASK DESCRIPTION] /at [DD/MM/YYYY HHMM]`

Expected outcome:

Description of the outcome.

```
expected output
```

### Saving the data

All Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duke data are saved as a TXT file [*home folder*]/data/duke.txt. Advanced users are welcome to update data directly by editing that data file.
*home folder* refers to the folder where you initially placed ip.jar, mentioned at the Quick Setup section.

## FAQ

## Command Summary


