package com.example.fse;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class MainController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label tempLabel, timeLabel;
    @FXML
    ImageView imageView;

    private static Weather weather;

    public static Weather getWeather() {
        return weather;
    }

    public static void setWeather(Weather weather) {
        MainController.weather = weather;
    }
    public void initialize() {
        if (weather.getCurrent().getIs_day() == 1) {
        }
        timeLabel.setText(weather.getLocation().getLocaltime().split(" ")[1]);
        tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_c()).concat("°C"));
        imageView.setImage(new Image("http://"+weather.getCurrent().getCondition().getIcon().substring(2)));
        System.out.println(weather.getCurrent().getCondition().getIcon().substring(2));
    }
}
