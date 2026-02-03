package yoyo.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Yoyo using FXML.
 */
public class Main extends Application {

    private Chatbot yoyo = new Chatbot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(400);
            fxmlLoader.<MainWindow>getController().setYoyo(yoyo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
