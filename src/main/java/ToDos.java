public class ToDos extends Task{
    public ToDos(int id, String name) {
        super(id, name);
    }

    public ToDos(int id, boolean completed, String description) {
        super(id, completed, description);
    }

    /**
     * Override taskOutput to return information about the task.
     * @return Information about the task in String format.
     */
    @Override
    public String taskOutput() {
        return "T | " + super.taskOutput();
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
