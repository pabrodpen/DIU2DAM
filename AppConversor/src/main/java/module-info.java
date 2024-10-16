module com.example.appconversor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires AccesoBBDDMoneda;
    requires java.sql;

    opens com.example.appconversor.controllers to javafx.fxml;
    opens com.example.appconversor to javafx.fxml;
    exports com.example.appconversor;
}