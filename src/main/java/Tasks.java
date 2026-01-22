import java.util.ArrayList;

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
