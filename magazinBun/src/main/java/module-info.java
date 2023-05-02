module com.example.magazinBun {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.jetbrains.annotations;
    requires java.desktop;

    opens com.example.magazinBun to javafx.fxml;
    exports com.example.magazinBun;
}