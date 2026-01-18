public class Tasks {
    private Task[] tasks = new Task[101];
    private int index;


    public Tasks() {
        this.index = 1;
    }

    public void storeTask(String task, String description) {
        System.out.println("Got it. I've added this task:");

        if (task.equals("todo")) {
            tasks[index] = new ToDos(index, description.replaceFirst("todo ", ""));
        } else if (task.equals("deadline")) {
            String[] s = description.split("/by");
            tasks[index] = new Deadlines(index, s[0].replaceFirst("deadline ", ""), s[1]);
        } else if (task.equals("event")) {
            String[] s = description.split("/from|/to");
            tasks[index] = new Events(index, s[0].replaceFirst("event ", ""), s[1], s[2]);
        }

        System.out.println("  " + tasks[index].toString());
        System.out.println("Now you have " + index + " task/s in the list.");
        index++;
    }

    public void completeTask(int index) {
        tasks[index].complete();
    }

    public void incompleteTask(int index) {
        tasks[index].incomplete();
    }

    public String showTask(int index) {
        return tasks[index].toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < index; i++) {
            if (i == index - 1) {
                sb.append(i).append(". ").append(tasks[i].toString());
            } else {
                sb.append(i).append(". ").append(tasks[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
