import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Chatbot {
    private Tasks tasks;
    private final Set<String> operations =  Set.of("todo", "event", "deadline", "bye", "mark", "unmark", "list");

    public Chatbot() {
        tasks = new Tasks();
    }

    public void chat()
    {
        Scanner sc = new Scanner(System.in);
        String horizontal_line = "-----------------------------";

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm Yoyo.");
        System.out.println("What can I do for you?");
        System.out.println(horizontal_line);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(horizontal_line);
            String task = input.split(" ")[0];

            try {
                inputCheck(task, input);

                if (task.equals("list")) {
                    System.out.println(tasks.toString());
                } else if (task.equals("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    tasks.completeTask(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.showTask(index));
                } else if (task.equals("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    tasks.incompleteTask(index);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.showTask(index));
                } else {
                    tasks.storeTask(task, input);
                }
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

    private void inputCheck(String task, String input) throws YoyoException {
        int firstSpaceIndex = input.indexOf(' ');
        if (firstSpaceIndex != -1) {
            input = input.substring(firstSpaceIndex + 1);
        }
        String[] inputs = input.split(" ");
        if (!operations.contains(task)) {
            throw new YoyoException("Sorry, I do not recognise this.");
        } else if (task.equals("todo") && inputs.length == 0) {
            throw new YoyoException("Sorry, the description of a todo cannot be empty");
        } else if (task.equals("event")) {
            String[] s = input.split("/from|/to");
            if (inputs.length == 1) {
                throw new YoyoException("Sorry, the description and duration of a event cannot be empty");
            } else if (s.length != 3 || s[0].equals("")) {
                throw new YoyoException("Sorry, the description and duration of a event cannot be empty");
            }
        } else if (task.equals("deadline")) {
            String[] s = input.split("/by");
            if (inputs.length == 1) {
                throw new YoyoException("Sorry, the description and date of a deadline cannot be empty");
            } else {
                if (s.length != 2 || s[0].equals("")) {
                    throw new YoyoException("Sorry, the description and date of the deadline cannot be empty");
                }
            }
        } else if (task.equals("mark")) {
            if (!tasks.checkExists(Integer.parseInt(inputs[0]))) {
                throw new YoyoException("Sorry, the task does not exist");
            } else if (inputs.length == 1) {
                throw new YoyoException("Sorry, you need to specify which task is to be marked");
            }
        } else if (task.equals("unmark")) {
            if (!tasks.checkExists(Integer.parseInt(inputs[0]))) {
                throw new YoyoException("Sorry, the task does not exist");
            } else if (inputs.length == 1) {
                throw new YoyoException("Sorry, you need to specify which task is to be unmarked");
            }
        }
    }
}
