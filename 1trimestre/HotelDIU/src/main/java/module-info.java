module com.example.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;

    opens com.example.hotel to javafx.fxml;
    exports com.example.hotel;

    // Add this line to open the controller package to javafx.fxml
    opens com.example.hotel.controller to javafx.fxml;
    exports com.example.hotel.controller;
}
