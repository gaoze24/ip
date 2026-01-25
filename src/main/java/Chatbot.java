import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Chatbot {
    private Tasks tasks;
    private TaskFile taskfile;
    private Parser parser;
    private Command command;

    // Constructor for Chatbot class, requires no input parameters.
    public Chatbot() {
        this.tasks = new Tasks();
        this.taskfile = new TaskFile();
        this.parser = new Parser();
        this.command = new Command();
    }

    // Main method that handles chat logics
    public void chat() {
        Scanner sc = new Scanner(System.in);
        String horizontal_line = "-----------------------------";

        readTask();

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm Yoyo.");
        System.out.println("What can I do for you?");
        System.out.println(horizontal_line);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(horizontal_line);

            try {
                Task task = this.parser.parse(input, tasks);
                this.command.execute(tasks, task);
                saveTask();
            } catch (YoyoException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(horizontal_line);
                input = sc.nextLine();
            }
        }

        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(horizontal_line);
    }

    /**
     * Use the output from tasks class to write to the task file
     */
    private void saveTask() {
        this.taskfile.WriteList(tasks.fileOutput());
    }

    /**
     * Read the string list from the task file and store the task into tasks class.
     */
    private void readTask() {
        List<String> list = this.taskfile.ReadList();
        for (String s : list) {
            this.tasks.storeTask(s.split(" \\| "));
        }
    }
}
