import java.time.LocalDate;

public class Task {
    private int id;
    private boolean completed;
    private String description;

    public Task(int id, String description) {
        this.id = id;
        this.completed = false;
        this.description = description;
    }

    public Task(int id, boolean completed, String description) {
        this.id = id;
        this.completed = completed;
        this.description = description;
    }

    // Method that return a boolean value to indicate if the task is completed.
    public boolean isCompleted() {
        return this.completed;
    }

    // Method that returns the description of the task.
    public String getName() {
        return this.description;
    }

    // Method that marks the task as completed.
    public void complete() {
        this.completed = true;
    }

    // Method that marks the task as not completed.
    public void incomplete() {
        this.completed = false;
    }

    public String taskOutput() {
        return this.completed + " | " +  this.description;
    }

    /**
     * Get the type of the task
     * @return The type of the task in String
     */
    public String getType() {
        return "Task";
    }

    /**
     * Check if the task is active on this date.
     * @param date The date to be checked.
     * @return True is the task is active on this date, False otherwise.
     */
    public boolean checkDate(LocalDate date) {
        return false;
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        String status = this.completed ? "X" : " ";
        return "[" + status + "] " + this.description;
    }
}
