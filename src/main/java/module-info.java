module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alephnought.duitriagui to javafx.fxml;
    exports com.alephnought.duitriagui;
    exports com.alephnought.duitriagui.model;
    opens com.alephnought.duitriagui.model to javafx.fxml;
}