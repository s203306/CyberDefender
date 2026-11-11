package pl.cyberdefender.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

/** Kontroler menu głównego (main-menu.fxml). */
public class MainMenuController implements NavigatorAware {
    private SceneNavigator navigator;

    @Override
    public void setNavigator(SceneNavigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    private void onNewGame() {
        navigator.show(Screen.SCENARIO_SELECT);
    }

    @FXML
    private void onInstructions() {
        navigator.show(Screen.INSTRUCTIONS);
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }
}