package yoyo.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A container for storing Task objects.
 * Handles logics about Tasks.
 * Use an internal ArrayList to store Tasks.
 */
public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initialise the Tasks class.
     */
    public Tasks() {}

    /**
     * Store a yoyo.task.Task variable into tasks list.
     *
     * @param task The task to be stored
     */
    public void storeTask(Task task) {
        System.out.println("Got it. I've added this task:");
        tasks.add(task);
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " task/s in the list.");
    }

    /**
     * Overload storeTask class that accepts a string array.
     * Stores tasks into the list based on their types.
     * @param list Array that stores information about each task.
     */
    public void storeTask(String[] list) {
        if (list.length == 3) {
            tasks.add(new ToDos(list[0].equals("true"), list[1]));
        } else if (list.length == 4) {
            tasks.add(new Deadlines(list[0].equals("true"), list[1], list[2]));
        } else if (list.length == 5) {
            tasks.add(new Events(list[0].equals("true"), list[1], list[2], list[3]));
        }
    }

    /**
     * Mark one task as completed.
     * @param index The index of the task to be marked as completed.
     */
    public void completeTask(int index) {
        tasks.get(index - 1).complete();
    }

    /**
     * Mark one task as incomplete.
     * @param index The index of the task to be marked as incomplete.
     */
    public void incompleteTask(int index) {
        tasks.get(index - 1).incomplete();
    }

    /**
     * Show representation of a specific task.
     * @param index The index of the task to be shown.
     * @return A customized representation of the task.
     */
    public String showTask(int index) {
        return tasks.get(index - 1).toString();
    }

    /**
     * Delete a specific task from the list.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Count the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int count() {
        return this.tasks.size();
    }

    /**
     * Check if the index is valid.
     * @param index The index to be checked.
     * @return True if the index is valid, False otherwise.
     */
    public boolean checkExists(int index) {
        if (index > tasks.size() || index <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Output All the tasks in an easy-to-manipulate pattern in String
     *
     * @return all the information about tasks in a string
     */
    public String fileOutput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i == tasks.size() - 1) {
                sb.append(task.taskOutput());
            } else {
                sb.append(task.taskOutput()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Check if any tasks are active on the input date.
     * Return an organised string of all the applicable tasks.
     *
     * @param date The date to be checked.
     * @return The String representation of the list of tasks.
     */
    public String checkDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkDate(date)) {
                sb.append(tasks.get(i).toString());
                if (i != tasks.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Override toString to return a customized representation of the task.
     * @return A customized representation of the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                sb.append(i + 1).append(". ").append(tasks.get(i).toString());
            } else {
                sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
