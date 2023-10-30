module com.example.techstock {
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
    requires org.apache.commons.codec;

    opens com.example.techstock to javafx.fxml;
    opens com.example.techstock.domain to javafx.base;

    exports com.example.techstock;

    exports com.example.techstock.views.software;
    opens com.example.techstock.views.software to javafx.fxml;

    exports com.example.techstock.domain;
    opens com.example.techstock.domain to javafx.base;



}