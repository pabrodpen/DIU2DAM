module com.example.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.ejaniopasado to javafx.fxml;
    exports com.example.ejaniopasado.controller;
    opens com.example.ejaniopasado.controller to javafx.fxml;
    exports com.example.ejaniopasado.model;
    opens com.example.ejaniopasado.model to javafx.fxml;
    exports com.example.ejaniopasado.view;
    opens com.example.ejaniopasado.view to javafx.fxml;
    exports com.example.ejaniopasado.util;
    opens com.example.ejaniopasado.util to javafx.fxml;
    exports com.example.ejaniopasado;
}