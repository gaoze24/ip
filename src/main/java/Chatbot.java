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
