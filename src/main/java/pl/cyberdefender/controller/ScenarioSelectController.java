package pl.cyberdefender.controller;

import javafx.fxml.FXML;

/** Kontroler ekranu wyboru scenariusza (scenario-select.fxml). */
public class ScenarioSelectController implements NavigatorAware {

    private SceneNavigator navigator;

    @Override
    public void setNavigator(SceneNavigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    private void onBack() {
        navigator.show(Screen.MAIN_MENU);
    }
}