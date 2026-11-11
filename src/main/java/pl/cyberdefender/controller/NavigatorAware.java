package pl.cyberdefender.controller;

/**
 * Kontroler, który potrzebuje przełączać ekrany.
 * SceneNavigator po wczytaniu FXML sprawdza, czy kontroler implementuje
 * ten interfejs, i jeśli tak, przekazuje mu siebie.
 */
public interface NavigatorAware {

    void setNavigator(SceneNavigator navigator);
}