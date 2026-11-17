package pl.cyberdefender.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ResourceBundle;

/** Kontroler ekranu wyboru scenariusza (scenario-select.fxml). */
public class ScenarioSelectController implements NavigatorAware {

    /** Teksty interfejsu; wstrzykuje je FXMLLoader (nazwa pola musi brzmieć "resources"). */
    @FXML private ResourceBundle resources;

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
                "Podejrzany komputer księgowości",
                "Fałszywy alarm o północy");

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
        String empty = resources.getString("scenarioSelect.placeholder.empty");
        scenarioTitleLabel.setText(title);
        descriptionLabel.setText(resources.getString("scenarioSelect.placeholder.details"));
        goalLabel.setText(empty);
        learningLabel.setText(empty);
        resourcesLabel.setText(empty);
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