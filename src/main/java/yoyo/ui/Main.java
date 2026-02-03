package yoyo.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Main class for yoyo GUI interface.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Label("Hello World!"));

        stage.setScene(scene);
        stage.show();
    }
}
