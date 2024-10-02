module com.example.scenebuilderejemplo {
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

    opens com.example.scenebuilderejemplo to javafx.fxml;
    exports com.example.scenebuilderejemplo;
    exports com.example.scenebuilderejemplo.controller;
    opens com.example.scenebuilderejemplo.controller to javafx.fxml;
}