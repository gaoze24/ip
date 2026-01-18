import java.util.Scanner;

public class Yoyo {
    private static String[] items = new String[101];
    private static int index = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------------");
        System.out.println("Hello! I'm Yoyo.");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("-----------------------------");
            if (input.equals("list")) {
                for (int i = 1; i < index; i++) {
                    System.out.println(i + ". " + items[i]);
                }
            } else {
                items[index] = input;
                index++;
                System.out.println("added: " + input);
            }
            System.out.println("-----------------------------");
            input = sc.nextLine();
        }

        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("-----------------------------");
    }
}
