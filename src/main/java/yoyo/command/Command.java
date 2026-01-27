package yoyo.command;

import yoyo.task.Task;
import yoyo.task.Tasks;

import java.time.LocalDate;

/**
 * yoyo.command.Command class is responsible for executing all types of command.
 */
public class Command {
    /**
     * Execute the task given.
     *
     * @param tasks Current list of tasks.
     * @param task The task that is to be executed.
     */
    public void execute(Tasks tasks, Task task) {
        String type = task.getType();
        if (type.equals("mark")) {
            int index = Integer.parseInt(task.getDescription());
            tasks.completeTask(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.showTask(index));
        } else if (type.equals("unmark")) {
            int index = Integer.parseInt(task.getDescription());
            tasks.incompleteTask(index);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.showTask(index));
        } else if (type.equals("list")) {
            System.out.println(tasks.toString());
        } else if (type.equals("delete")) {
            int index = Integer.parseInt(task.getDescription());
            System.out.println("Nice! I've deleted this task as done:");
            System.out.println("  " + tasks.showTask(index));
            tasks.deleteTask(index);
            System.out.println("Now you have " + tasks.count() + " task/s in the list.");
        } else if (type.equals("check")) {
            LocalDate date = LocalDate.parse(task.getDescription());
            tasks.checkDate(date);
        } else if (type.equals("find")) {
            System.out.println("Here are the matching task/s in your list:");
            System.out.println(tasks.checkWord(task.getDescription()));
        } else {
            tasks.storeTask(task);
        }
    }
}