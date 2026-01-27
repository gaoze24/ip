package yoyo.ui;

import yoyo.command.Parser;
import yoyo.command.Command;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskFile;
import yoyo.task.Tasks;

import java.util.List;
import java.util.Scanner;

/**
 * Handles UI and chat logics.
 */
public class Chatbot {
    private Tasks tasks = new Tasks();
    private TaskFile taskfile = new TaskFile();
    private Parser parser = new Parser();
    private Command command = new Command();

    /**
     * Initialise the Chatbot class.
     */
    public Chatbot() {
    }

    /**
     * Method that handles chat logics.
     * Works with Task, TaskFile, Parser and Command classes.
     */
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
     * Use the output from the tasks class to write to the task file
     */
    private void saveTask() {
        this.taskfile.WriteList(tasks.fileOutput());
    }

    /**
     * Read the string list from the task file and store the task into a tasks class.
     */
    private void readTask() {
        List<String> list = this.taskfile.ReadList();
        for (String s : list) {
            this.tasks.storeTask(s.split(" \\| "));
        }
    }
}
