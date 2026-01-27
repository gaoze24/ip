package yoyo.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count;

    public Tasks() {
        this.count = 0;
    }

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

    // Method that marks one task as completed.
    public void completeTask(int index) {
        tasks.get(index - 1).complete();
    }

    // Method that marks one task as incompleted.
    public void incompleteTask(int index) {
        tasks.get(index - 1).incomplete();
    }

    // Method that return a string representation of a task in the array.
    public String showTask(int index) {
        return tasks.get(index - 1).toString();
    }

    // Method that delete a specific task from the array.
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    // Method that returns the number of tasks in the array.
    public int count() {
        return this.count;
    }

    // Method that checks if a specific task exists in the array.
    public boolean checkExists(int index) {
        if (index > tasks.size() || index <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if any tasks contain the input word.
     * Return an organised string of all the applicable tasks.
     * @param word The word to be checked.
     * @return The String representation of the list of tasks.
     */
    public String checkWord(String word) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(word)) {
                sb.append(i + 1).append(". ").append(tasks.get(i).toString());
                if (i != tasks.size() - 1) {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }

    /**
     * Output All the tasks in an easy to manipulate pattern in String
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

    // Override toString method that return a string representation of the entire list.
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
