import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles Task Files reading and writing logics.
 */
public class TaskFile {
    private Path path = Path.of("./data/yoyo.txt");

    /**
     * Initialise the TaskFile class, check if the task file exists.
     * Create the file if it does not exist.
     * @throws IOException If the task file cannot be created.
     */
    public TaskFile() {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.writeString(path, "");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when trying to write the file");
        }
    }

    /**
     * Read and return the content from the task file.
     * Retrun an empty list if failed to read.
     *
     * @return A list of strings of content from the task file
     */
    public List<String> ReadList() {
        try {
            List<String> list = Files.readAllLines(path);
            return list;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Write content to the task file, return true if successful.
     * Return false if write not successful.
     *
     * @param message The message to be written.
     * @return True/False on whether writing is successful.
     */
    public boolean WriteList(String message){
        try {
            Files.writeString(path, message);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
