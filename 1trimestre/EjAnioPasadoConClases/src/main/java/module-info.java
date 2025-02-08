module com.example.ejaniopasadoconclases {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    // Librerías de terceros
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires Modelo; // Verifica que este módulo esté disponible y correctamente configurado

    // Apertura de paquetes para reflexión (JavaFX FXML)
    opens com.example.ejaniopasadoconclases to javafx.fxml;
    opens com.example.ejaniopasadoconclases.controller to javafx.fxml;

    // Exporta los paquetes que otros módulos pueden utilizar
    exports com.example.ejaniopasadoconclases;
    exports com.example.ejaniopasadoconclases.controller;
}
