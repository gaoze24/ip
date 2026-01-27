package yoyo.command;

import yoyo.exception.YoyoException;
import yoyo.task.Deadlines;
import yoyo.task.Events;
import yoyo.task.Task;
import yoyo.task.Tasks;
import yoyo.task.ToDos;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

/**
 * yoyo.command.Parser class is responsible for parsing input and checking input integrity.
 */
public class Parser {
    private final Set<String> OPERATIONS =
            Set.of("todo", "event", "deadline", "bye", "mark", "unmark", "list", "delete", "check");

    /**
     * Parsing the user input into machine friendly format.
     * Return a task variable to be further executed by yoyo.command.Command class.
     *
     * @param input The user input that is to be parsed.
     * @param tasks Current list of tasks.
     * @return A task class that is to be executed
     * @throws YoyoException If the user input is not in the right format.
     */
    public Task parse(String input, Tasks tasks) throws YoyoException {
        checkInput(input, tasks);

        String[] elements = input.split(" /to | /from | /by ");
        String[] firstPart = elements[0].split(" ", 2);

        if (firstPart[0].equals("list")) {
            return new Task("list");
        } else if (elements.length == 1) {
            if (firstPart[0].equals("todo")) {
                return new ToDos(firstPart[1]);
            } else {
                return new Task(firstPart[1], firstPart[0]);
            }
        } else if (elements.length == 2) {
            return new Deadlines(firstPart[1], elements[1]);
        } else if (elements.length == 3) {
            return new Events(firstPart[1], elements[1], elements[2]);
        }

        return new Task();
    }

    // Method that checks if the user input meets the expectations, will throw exceptions if errors are found.
    private void checkInput(String input, Tasks tasks) throws YoyoException {
        String task = input.split(" ")[0];
        int firstSpaceIndex = input.indexOf(' ');

        if (firstSpaceIndex != -1) {
            input = input.substring(firstSpaceIndex + 1);
        } else {
            if (!task.equals("bye") && !task.equals("list")) {
                throw new YoyoException("Missing description");
            }
        }

        String[] inputs = input.split(" ");

        if (!OPERATIONS.contains(task)) {
            throw new YoyoException("Sorry, I do not recognise this.");
        } else if (task.equals("todo") && inputs.length == 0) {
            throw new YoyoException("Sorry, the description of a todo cannot be empty");
        } else if (task.equals("event")) {
            String[] s = input.split(" /from | /to ");
            if (inputs.length == 1) {
                throw new YoyoException("Sorry, the description and duration of a event cannot be empty");
            } else if (s.length != 3 || s[0].isEmpty()) {
                throw new YoyoException("Sorry, the description and duration of a event cannot be empty");
            }
            try {
                LocalDate start = LocalDate.parse(s[1]);
                LocalDate end = LocalDate.parse(s[2]);
            } catch (DateTimeParseException e) {
                throw new YoyoException("Sorry, you have not input a valid date. Please follow yyyy-mm-dd");
            }
        } else if (task.equals("deadline")) {
            String[] s = input.split(" /by ");
            if (inputs.length == 1) {
                throw new YoyoException("Sorry, the description and date of a deadline cannot be empty");
            } else {
                if (s.length != 2 || s[0].isEmpty()) {
                    throw new YoyoException("Sorry, the description and date of the deadline cannot be empty");
                }
            }
            try {
                LocalDate deadline = LocalDate.parse(s[1]);
            } catch (DateTimeParseException e) {
                throw new YoyoException("Sorry, you have not input a valid date. Please follow yyyy-mm-dd");
            }
        } else if (task.equals("mark")) {
            if (inputs.length != 1 || !isNumeric(inputs[0])) {
                throw new YoyoException("Sorry, you need to specify which task is to be marked");
            } else if (!tasks.checkExists(Integer.parseInt(inputs[0]))) {
                throw new YoyoException("Sorry, the task does not exist");
            }
        } else if (task.equals("unmark")) {
            if (inputs.length != 1 || !isNumeric(inputs[0])) {
                throw new YoyoException("Sorry, you need to specify which task is to be unmarked");
            } else if (!tasks.checkExists(Integer.parseInt(inputs[0]))) {
                throw new YoyoException("Sorry, the task does not exist");
            }
        } else if (task.equals("delete")) {
            if (inputs.length != 1 || !isNumeric(inputs[0])) {
                throw new YoyoException("Sorry, you need to specify which task is to be deleted");
            } else if (!tasks.checkExists(Integer.parseInt(inputs[0]))) {
                throw new YoyoException("Sorry, the task does not exist");
            }
        } else if (task.equals("check")) {
            if (inputs.length != 1) {
                throw new YoyoException("Sorry, you need to specify which date is to be check");
            }
            try {
                LocalDate checkDate = LocalDate.parse(inputs[0]);
            } catch (DateTimeParseException e) {
                throw new YoyoException("Sorry, you have not input a valid date.  Please follow yyyy-mm-dd");
            }
        }
    }

    // Boolean method that checks if the string is consisted of numeric values only.
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
