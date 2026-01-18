import java.util.Scanner;

public class Chatbot {
    private Tasks tasks;

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
            System.out.println(horizontal_line);
            input = sc.nextLine();
        }

        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(horizontal_line);
    }
}
