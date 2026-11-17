package pl.cyberdefender.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/** Kontroler ekranu wyboru scenariusza (scenario-select.fxml). */
public class ScenarioSelectController implements NavigatorAware {

    private static final String PLACEHOLDER =
            "Szczegóły scenariusza zostaną wczytane z pliku JSON (Etap 2).";

    @FXML private ListView<String> scenarioList;
    @FXML private Label scenarioTitleLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label goalLabel;
    @FXML private Label learningLabel;
    @FXML private Label resourcesLabel;

    private SceneNavigator navigator;

    @Override
    public void setNavigator(SceneNavigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    private void initialize() {
        scenarioList.getItems().addAll(
                "[Podejrzany komputer księgowości]",
                "[Fałszywy alarm o północy]");

        scenarioList.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldTitle, newTitle) -> showDetails(newTitle));

        scenarioList.getSelectionModel().selectFirst();
    }

    private void showDetails(String title) {
        if (title == null) {
            scenarioTitleLabel.setText("");
            descriptionLabel.setText("");
            goalLabel.setText("");
            learningLabel.setText("");
            resourcesLabel.setText("");
            return;
        }
        scenarioTitleLabel.setText(title);
        descriptionLabel.setText(PLACEHOLDER);
        goalLabel.setText("—");
        learningLabel.setText("—");
        resourcesLabel.setText("—");
    }

    @FXML
    private void onBack() {
        navigator.show(Screen.MAIN_MENU);
    }

    @FXML
    private void onStart() {
        String selected = scenarioList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("Nie wybrano scenariusza.");
            return;
        }
        System.out.println("Wybrano scenariusz: " + selected);
    }
}