package yoyo.command;

import yoyo.exception.YoyoException;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.Task;
import yoyo.task.Tasks;
import yoyo.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

/**
 * yoyo.command.Parser class is responsible for parsing input and checking input integrity.
 */
public class Parser {
    private static final Set<String> OPERATIONS =
            Set.of("todo", "event", "deadline", "bye", "mark", "unmark", "list", "delete", "check");

    /**
     * Parsing the user input into a machine-friendly format.
     * Return a task variable to be further executed by yoyo.command.Command class.
     *
     * @param input The user input that is to be parsed.
     * @param tasks Current list of tasks.
     * @return A task class that is to be executed
     * @throws YoyoException If the user input is not in the right format.
     */
    public Task parse(String input, Tasks tasks) throws YoyoException {
        assert input != null : "Input string to parse cannot be null";
        assert tasks != null : "Tasks list cannot be null";
        checkInput(input, tasks);

        String[] firstPart = input.split(" ", 2);
        String command = firstPart[0];

        if (command.equals("list")) {
            return new Task("list");
        } else if (command.equals("todo")) {
            return new ToDo(firstPart[1].trim());
        } else if (command.equals("deadline")) {
            String description = getPart(input, "deadline", "/by");
            String by = getPartAfter(input, "/by");
            return new Deadline(description, by);
        } else if (command.equals("event")) {
            String description = getPart(input, "event", "/from", "/to");
            String from = getPartAfter(input, "/from");
            String to = getPartAfter(input, "/to");
            return new Event(description, from, to);
        } else if (firstPart.length > 1) {
            return new Task(firstPart[1], command);
        }

        return new Task();
    }

    private String getPart(String input, String command, String... delimiters) {
        int startIndex = input.indexOf(command) + command.length();
        int endIndex = input.length();
        for (String delimiter : delimiters) {
            int dIndex = input.indexOf(delimiter);
            if (dIndex != -1 && dIndex < endIndex) {
                endIndex = dIndex;
            }
        }
        return input.substring(startIndex, endIndex).trim();
    }

    private String getPartAfter(String input, String delimiter) {
        int dIndex = input.indexOf(delimiter);
        if (dIndex == -1) {
            return "";
        }
        int startIndex = dIndex + delimiter.length();
        int endIndex = input.length();

        // Find the next delimiter
        String[] allDelimiters = {"/from", "/to", "/by"};
        for (String d : allDelimiters) {
            if (d.equals(delimiter)) {
                continue;
            }
            int nextDIndex = input.indexOf(d);
            if (nextDIndex != -1 && nextDIndex > dIndex && nextDIndex < endIndex) {
                endIndex = nextDIndex;
            }
        }
        return input.substring(startIndex, endIndex).trim();
    }

    /**
     * Check if the user input is in the right format.
     * @param input The user input to be checked.
     * @param tasks Current list of tasks.
     * @throws YoyoException If the user input is not in the right format.
     */
    private void checkInput(String input, Tasks tasks) throws YoyoException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        if (!Parser.OPERATIONS.contains(command)) {
            throw new YoyoException("Sorry, I do not recognise this.");
        }

        if (command.equals("bye") || command.equals("list")) {
            return;
        }

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new YoyoException("Missing description");
        }

        String remaining = parts[1];

        switch (command) {
        case "todo":
            validateTodo(remaining);
            break;
        case "event":
            validateEvent(input);
            break;
        case "deadline":
            validateDeadline(input);
            break;
        case "mark":
        case "unmark":
        case "delete":
            validateIndexBasedCommand(command, remaining, tasks);
            break;
        case "check":
            validateCheck(remaining);
            break;
        case "find":
            validateFind(remaining);
            break;
        default:
            // Should not reach here due to OPERATIONS.contains check
            break;
        }
    }

    private void validateTodo(String description) throws YoyoException {
        if (description.trim().isEmpty()) {
            throw new YoyoException("Sorry, the description of a todo cannot be empty");
        }
    }

    private void validateEvent(String input) throws YoyoException {
        String description = getPart(input, "event", "/from", "/to");
        String from = getPartAfter(input, "/from");
        String to = getPartAfter(input, "/to");

        if (description.isEmpty()) {
            throw new YoyoException("Sorry, the description of an event cannot be empty");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new YoyoException("Sorry, the duration of an event cannot be empty (need /from and /to)");
        }
        try {
            LocalDate.parse(from);
            LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new YoyoException("Sorry, you have not input a valid date. Please follow yyyy-mm-dd");
        }
    }

    private void validateDeadline(String input) throws YoyoException {
        String description = getPart(input, "deadline", "/by");
        String by = getPartAfter(input, "/by");

        if (description.isEmpty()) {
            throw new YoyoException("Sorry, the description of a deadline cannot be empty");
        }
        if (by.isEmpty()) {
            throw new YoyoException("Sorry, the date of a deadline cannot be empty (need /by)");
        }
        try {
            LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new YoyoException("Sorry, you have not input a valid date. Please follow yyyy-mm-dd");
        }
    }

    private void validateIndexBasedCommand(String command, String indexStr, Tasks tasks) throws YoyoException {
        indexStr = indexStr.trim();
        if (!isNumeric(indexStr)) {
            throw new YoyoException("Sorry, you need to specify which task is to be " + command + "ed");
        }
        int index = Integer.parseInt(indexStr);
        if (!tasks.checkExists(index)) {
            throw new YoyoException("Sorry, the task does not exist");
        }
    }

    private void validateCheck(String dateStr) throws YoyoException {
        dateStr = dateStr.trim();
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new YoyoException("Sorry, you have not input a valid date.  Please follow yyyy-mm-dd");
        }
    }

    private void validateFind(String query) throws YoyoException {
        if (query.trim().isEmpty()) {
            throw new YoyoException("Sorry, you need to specify what you want to find");
        }
    }

    /**
     * Check if a string is numeric.
     * @param str The string to be checked.
     * @return True if the string is numeric, False otherwise.
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
