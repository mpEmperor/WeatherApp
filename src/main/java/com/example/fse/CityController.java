package com.example.fse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CityController {
    @FXML
    TextField cityField;
    Parent root;
    Scene scene;
    Stage stage;
    public void enteredCity(ActionEvent event) throws IOException {
        Main.setCity(cityField.getText());
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
