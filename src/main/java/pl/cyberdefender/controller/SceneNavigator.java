package pl.cyberdefender.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** Przełącza ekrany aplikacji, podmieniając korzeń jednej, wspólnej sceny. */
public class SceneNavigator {

    private final Scene scene;
    private final ResourceBundle messages;

    /**
     * Tworzy nawigator dla podanej sceny.
     *
     * @param scene    scena, której korzeń będzie podmieniany
     * @param messages teksty interfejsu przekazywane do każdego wczytywanego widoku
     */
    public SceneNavigator(Scene scene, ResourceBundle messages) {
        this.scene = Objects.requireNonNull(scene, "scene");
        this.messages = Objects.requireNonNull(messages, "messages");
    }

    /**
     * Wyświetla wskazany ekran w oknie aplikacji.
     * <p>
     * Wczytuje plik FXML ekranu razem z tekstami interfejsu, przekazuje ten
     * nawigator jego kontrolerowi (jeśli kontroler implementuje
     * {@link NavigatorAware}) i podmienia korzeń sceny. Okno i scena pozostają
     * te same, więc rozmiar okna się nie zmienia.
     *
     * @param screen ekran do wyświetlenia
     * @throws IllegalStateException jeśli plik FXML ekranu nie istnieje
     *         pod ścieżką podaną w {@link Screen#getFxmlPath()}
     * @throws java.io.UncheckedIOException jeśli plik FXML istnieje, ale nie da
     *         się go wczytać (np. błąd składni, nieistniejący kontroler
     *         lub brakujący klucz tekstu)
     */
    public void show(Screen screen) {
        URL location = SceneNavigator.class.getResource(screen.getFxmlPath());
        if (location == null) {
            throw new IllegalStateException(
                    "Nie znaleziono pliku FXML dla ekranu " + screen + ": " + screen.getFxmlPath());
        }

        FXMLLoader loader = new FXMLLoader(location, messages);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Nie udało się wczytać ekranu " + screen + " (" + screen.getFxmlPath() + ")", e);
        }

        if (loader.getController() instanceof NavigatorAware aware) {
            aware.setNavigator(this);
        }

        scene.setRoot(root);
    }
}