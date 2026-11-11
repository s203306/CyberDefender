package pl.cyberdefender;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.cyberdefender.controller.SceneNavigator;
import pl.cyberdefender.controller.Screen;

public class App extends Application {

    static final double WINDOW_WIDTH = 1280;
    static final double WINDOW_HEIGHT = 800;
    static final double MIN_WIDTH = 1024;
    static final double MIN_HEIGHT = 700;

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);

        SceneNavigator navigator = new SceneNavigator(scene);
        navigator.show(Screen.MAIN_MENU);

        stage.setTitle("CyberDefender");
        stage.setScene(scene);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
