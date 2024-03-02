module com.dims.lasttest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.dims.lasttest to javafx.fxml;
    exports com.dims.lasttest;
    exports com.dims.lasttest.Controller;
    opens com.dims.lasttest.Controller to javafx.fxml;
}