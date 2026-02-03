package yoyo.ui;

import yoyo.command.Command;
import yoyo.command.Parser;
import yoyo.exception.YoyoException;

import yoyo.task.Task;
import yoyo.task.TaskFile;
import yoyo.task.Tasks;

import java.util.Scanner;
import java.util.List;

/**
 * Handles UI and chat logics.
 */
public class Chatbot {
    private static final String HORIZONTAL_LINE = "-----------------------------";
    private Tasks tasks = new Tasks();
    private TaskFile taskfile = new TaskFile();
    private Parser parser = new Parser();
    private Command command = new Command();

    /**
     * Initialise the Chatbot class.
     */
    public Chatbot() {
        readTask();
    }

    /**
     * Method that handles chat logics.
     * Works with Task, TaskFile, Parser and Command classes.
     */
    public void chat() {
        Scanner sc = new Scanner(System.in);

        printHorizonalLine();
        System.out.println("Hello! I'm Yoyo.");
        System.out.println("What can I do for you?");
        printHorizonalLine();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            printHorizonalLine();

            try {
                Task task = this.parser.parse(input, tasks);
                String response = this.command.execute(tasks, task);
                System.out.println(response);
                saveTask();
            } catch (YoyoException e) {
                System.out.println(e.getMessage());
            } finally {
                printHorizonalLine();
                input = sc.nextLine();
            }
        }

        printHorizonalLine();
        System.out.println("Bye. Hope to see you again!");
        printHorizonalLine();
    }

    /**
     * Returns a response from the chatbot based on the input.
     * @param input User input.
     * @return Chatbot's response.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again!";
        }
        try {
            Task task = this.parser.parse(input, tasks);
            String response = this.command.execute(tasks, task);
            saveTask();
            return response;
        } catch (YoyoException e) {
            return e.getMessage();
        }
    }

    private void printHorizonalLine() {
        System.out.println(Chatbot.HORIZONTAL_LINE);
    }

    /**
     * Use the output from the tasks class to write to the task file
     */
    private void saveTask() {
        this.taskfile.writeList(tasks.fileOutput());
    }

    /**
     * Read the string list from the task file and store the task into a tasks class.
     */
    private void readTask() {
        List<String> list = this.taskfile.readList();
        for (String s : list) {
            this.tasks.storeTask(s.split(" \\| "));
        }
    }
}
