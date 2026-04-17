module com.example.javafxmastermindcontrole {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.javafxmastermindcontrole to javafx.fxml;
    exports com.example.javafxmastermindcontrole;
}