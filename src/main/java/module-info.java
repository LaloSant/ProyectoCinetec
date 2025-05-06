module com.fundbd.cine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires java.base;

    opens com.fundbd.cine.controller to javafx.fxml;
    exports com.fundbd.cine;
}
