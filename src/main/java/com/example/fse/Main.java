package com.example.fse;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main extends Application {
    private static String city;
    public static String getCity() {
        return city;
    }
    public static void setCity(String city) {
        Main.city = city;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                        URI.create("https://api.weatherapi.com/v1/forecast.json?key=0fb6820927be47a38d3154348220206&q="+city+"&days=1&aqi=no&alerts=no"))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Weather weather = new Gson().fromJson(response.body(), Weather.class);
        MainController.setWeather(weather);
        System.out.println(weather.getLocation().getLocaltime().split(" ")[1]);
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("cityenter.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Weather");
        stage.setScene(scene);
        stage.show();
    }

}
