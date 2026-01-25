public class Events extends Task{
    private String start;
    private String end;

    public Events(int id, String name, String start, String end) {
        super(id, name);
        this.start = start;
        this.end = end;
    }

    public Events(int id, boolean completed, String name, String start, String end) {
        super(id, completed, name);
        this.start = start;
        this.end = end;
    }

    /**
     * Override taskOutput to return information about the task.
     * @return Information about the task in String format.
     */
    @Override
    public String taskOutput() {
        return "E | " + super.taskOutput() + " | " + this.start + " | " + this.end;
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.start + "to:" + this.end + ")";
    }
}
