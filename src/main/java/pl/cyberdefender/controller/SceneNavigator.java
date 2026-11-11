package pl.cyberdefender.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Objects;

/** Przełącza ekrany aplikacji podmieniając korzeń wspólnej sceny. */
public class SceneNavigator {

    private final Scene scene;

    public SceneNavigator(Scene scene) {
        this.scene = Objects.requireNonNull(scene, "scene");
    }

    /**
     * Wyświetla wskazany ekran w oknie aplikacji.
     * Wczytuje plik FXML ekranu, przekazuje ten nawigator jego kontrolerowi i podmienia korzeń sceny.
     * Okno i scena pozostają te same, więc rozmiar okna się nie zmienia.
     *
     * @param screen ekran do wyświetlenia
     * @throws IllegalStateException jeśli plik FXML ekranu nie istnieje
     *         pod ścieżką podaną w {@link Screen#getFxmlPath()}
     * @throws java.io.UncheckedIOException jeśli plik FXML istnieje, ale nie da
     *         się go wczytać (np. błąd składni lub nieistniejący kontroler)
     */
    public void show(Screen screen) {
        URL location = SceneNavigator.class.getResource(screen.getFxmlPath());
        if (location == null) {
            throw new IllegalStateException(
                    "Nie znaleziono pliku FXML dla ekranu " + screen + ": " + screen.getFxmlPath());
        }

        FXMLLoader loader = new FXMLLoader(location);
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