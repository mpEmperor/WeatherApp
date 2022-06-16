package com.example.fse;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class CityController {
    @FXML
    TextField cityField;
    @FXML
    Label invalidLabel;
    Parent root;
    Scene scene;
    Stage stage;
    public void enteredCity(ActionEvent event) throws IOException, InterruptedException {
        String city = cityField.getText();
        if (city.contains(" ")) {
            city = city.replaceAll(" ", "_");
        }
        // create a client
        var client = HttpClient.newHttpClient();
        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create("https://api.weatherapi.com/v1/forecast.json?key=0fb6820927be47a38d3154348220206&q="+city+"&days=3&aqi=no&alerts=no"))
                .header("accept", "application/json")
                .build();
        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Weather weather = new Gson().fromJson(response.body(), Weather.class);
        MainController.setWeather(weather);
       // try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
      //  } catch (Exception e) {
        //    invalidLabel.setVisible(true);
       // }
    }
}
