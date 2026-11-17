package pl.cyberdefender;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.cyberdefender.controller.SceneNavigator;
import pl.cyberdefender.controller.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class App extends Application {

    private static final String MESSAGES_BUNDLE = "pl.cyberdefender.messages";
    private static final String STYLESHEET = "/pl/cyberdefender/css/app.css";

    static final double WINDOW_WIDTH = 1280;
    static final double WINDOW_HEIGHT = 800;
    static final double MIN_WIDTH = 1024;
    static final double MIN_HEIGHT = 700;

    @Override
    public void start(Stage stage) {
        ResourceBundle messages = ResourceBundle.getBundle(MESSAGES_BUNDLE);
        Scene scene = new Scene(new StackPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(loadStylesheet());

        SceneNavigator navigator = new SceneNavigator(scene, messages);
        navigator.show(Screen.MAIN_MENU);

        stage.setTitle(messages.getString("app.title"));
        stage.setScene(scene);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    private String loadStylesheet() {
        URL css = App.class.getResource(STYLESHEET);
        if (css == null) {
            throw new IllegalStateException("Nie znaleziono arkusza stylów: " + STYLESHEET);
        }
        return css.toExternalForm();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
