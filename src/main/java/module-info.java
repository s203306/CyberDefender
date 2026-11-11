module pl.cyberdefender {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens pl.cyberdefender.controller to javafx.fxml;

    exports pl.cyberdefender;
}