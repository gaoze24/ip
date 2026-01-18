import java.util.Scanner;

public class Chatbot {
    private Items items;

    public Chatbot() {
        items = new Items();
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
            if (input.equals("list")) {
                System.out.println(items.toString());
            } else if (input.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                items.completeItem(index);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + items.showItem(index));
            } else if (input.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                items.incompleteItem(index);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + items.showItem(index));
            } else {
                items.storeItem(input);
                System.out.println("added: " + input);
            }
            System.out.println(horizontal_line);
            input = sc.nextLine();
        }

        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(horizontal_line);
    }
}
