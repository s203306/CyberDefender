package pl.cyberdefender.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

/** Kontroler menu głównego (main-menu.fxml). */
public class MainMenuController {

    @FXML
    private void onNewGame() {
        // TODO: przejście do wyboru scenariusza
        System.out.println("Kliknięto: Nowa gra");
    }

    @FXML
    private void onInstructions() {
        // TODO: przejście do ekranu instrukcji
        System.out.println("Kliknięto: Instrukcja");
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }
}