module com.fundbd.cine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens com.fundbd.cine to javafx.fxml;
    exports com.fundbd.cine;
}
