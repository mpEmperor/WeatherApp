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
    Label tempLabel, timeLabel, Location;
    @FXML
    ImageView imageView, bgImageView;

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
        tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_c()).concat(""));
        imageView.setImage(new Image("http://"+weather.getCurrent().getCondition().getIcon().substring(2)));
        bgImageView.setImage(new Image(String.valueOf(getClass().getResource("Extremely modern weather (Instagram story) (1).jpg"))));
        System.out.println(weather.getCurrent().getCondition().getIcon().substring(2));
    }
}
