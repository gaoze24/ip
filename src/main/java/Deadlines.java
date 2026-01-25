public class Deadlines extends Task{
    private String deadline;
    public Deadlines(int id, String name, String deadline) {
        super(id, name);
        this.deadline = deadline;
    }

    public Deadlines(int id, boolean completed, String description, String deadline) {
        super(id, completed, description);
        this.deadline = deadline;
    }

    /**
     * Override taskOutput to return information about the task.
     * @return Information about the task in String format.
     */
    @Override
    public String taskOutput() {
        return "D | " + super.taskOutput() + " | " + this.deadline;
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }
}

