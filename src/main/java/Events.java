public class Events extends Task{
    private String start;
    private String end;

    public Events(int id, String name, String start, String end) {
        super(id, name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.start + "to:" + this.end + ")";
    }
}
