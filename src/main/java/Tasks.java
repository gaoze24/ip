import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int index;
    private int count;

    public Tasks() {
        this.index = 1;
        this.count = 0;
    }

    // Method that stores task and description into the task array. It can categorize different type of tasks.
    public void storeTask(String task, String description) {
        System.out.println("Got it. I've added this task:");

        if (task.equals("todo")) {
            tasks.add(new ToDos(index, description.replaceFirst("todo ", "")));
        } else if (task.equals("deadline")) {
            String[] s = description.split("/by");
            tasks.add(new Deadlines(index, s[0].replaceFirst("deadline ", ""), s[1]));
        } else if (task.equals("event")) {
            String[] s = description.split("/from|/to");
            tasks.add(new Events(index, s[0].replaceFirst("event ", ""), s[1], s[2]));
        }

        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " task/s in the list.");
        index++;
    }

    /**
     * Overload storeTask class that accepts a string array.
     * Stores tasks into the list based on their types.
     * @param list Array that store information about each task.
     */
    public void storeTask(String[] list) {
        if (list.length == 3) {
            tasks.add(new ToDos(index, list[1].equals("true"), list[2]));
        } else if (list.length == 4) {
            tasks.add(new Deadlines(index, list[1].equals("true"), list[2], list[3]));
        } else if (list.length == 5) {
            tasks.add(new Events(index, list[1].equals("true"), list[2], list[3], list[4]));
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
