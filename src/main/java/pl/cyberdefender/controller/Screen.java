package pl.cyberdefender.controller;

/** Ekrany aplikacji wraz z definicjami FXML */
public enum Screen {

    MAIN_MENU("main-menu.fxml"),
    INSTRUCTIONS("instructions.fxml"),
    SCENARIO_SELECT("scenario-select.fxml");

    private static final String FXML_DIR = "/pl/cyberdefender/fxml/";

    private final String fxmlPath;

    Screen(String fxmlFile) {
        this.fxmlPath = FXML_DIR + fxmlFile;
    }

    /** Ścieżka do pliku FXML liczona od korzenia classpath (src/main/resources). */
    public String getFxmlPath() {
        return fxmlPath;
    }
}