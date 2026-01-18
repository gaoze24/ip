import java.util.Scanner;

public class Yoyo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------------");
        System.out.println("Hello! I'm Yoyo.");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("-----------------------------");
            System.out.println(input);
            System.out.println("-----------------------------");
            input = sc.nextLine();
        }

        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("-----------------------------");
    }
}
