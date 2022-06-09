module com.example.fse {
    requires com.google.gson;
    requires java.net.http;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.example.fse to javafx.controls, javafx.fxml, javafx.graphics, com.google.gson;
    exports com.example.fse;
}