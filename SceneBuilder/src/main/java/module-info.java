module com.example.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.scenebuilder to javafx.fxml;
    exports com.example.scenebuilder;
    exports com.example.scenebuilder.controller;
    opens com.example.scenebuilder.controller to javafx.fxml;
}