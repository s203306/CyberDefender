package pl.cyberdefender;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    static final double WINDOW_WIDTH = 1280;
    static final double WINDOW_HEIGHT = 800;
    static final double MIN_WIDTH = 1024;
    static final double MIN_HEIGHT = 700;

    @Override
    public void start(Stage stage){
        StackPane root = new StackPane(new Label("CyberDefender"));

        stage.setTitle("CyberDefender");
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
