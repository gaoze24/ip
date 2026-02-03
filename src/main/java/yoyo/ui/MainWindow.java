package yoyo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chatbot yoyo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/img.png"));
    private Image yoyoImage = new Image(this.getClass().getResourceAsStream("/images/img_1.png"));

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Yoyo. How can I help you today?", yoyoImage)
        );
    }

    /**
     * Sets the chatbot instance for this controller.
     * @param d The Chatbot instance.
     */
    public void setYoyo(Chatbot d) {
        yoyo = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yoyo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yoyo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, yoyoImage)
        );
        userInput.clear();
    }
}
