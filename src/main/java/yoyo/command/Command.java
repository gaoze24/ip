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
    public String execute(Tasks tasks, Task task) {
        assert tasks != null : "Tasks container should not be null";
        assert task != null : "Task to execute should not be null";

        String type = task.getType();
        assert type != null : "Task type should not be null";

        if (type.equals("mark")) {
            int index = Integer.parseInt(task.getDescription());
            tasks.completeTask(index);
            return "Nice! I've marked this task as done:\n" + "  " + tasks.showTask(index);
        } else if (type.equals("unmark")) {
            int index = Integer.parseInt(task.getDescription());
            tasks.incompleteTask(index);
            return "OK, I've marked this task as not done yet:\n" + "  " + tasks.showTask(index);
        } else if (type.equals("list")) {
            return tasks.toString();
        } else if (type.equals("delete")) {
            int index = Integer.parseInt(task.getDescription());
            String response = "Nice! I've deleted this task as done:\n" + "  " + tasks.showTask(index);
            tasks.deleteTask(index);
            response += "\nNow you have " + tasks.count() + " task/s in the list.";
            return response;
        } else if (type.equals("check")) {
            LocalDate date = LocalDate.parse(task.getDescription());
            return tasks.checkDate(date);
        } else if (type.equals("find")) {
            return "Here are the matching task/s in your list:\n" + tasks.checkWord(task.getDescription());
        } else {
            return tasks.storeTask(task);
        }
    }
}
