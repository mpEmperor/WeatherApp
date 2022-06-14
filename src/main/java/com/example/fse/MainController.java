package com.example.fse;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label tempLabel, timeLabel, locationLabel, windLabel, precipitationLabel;
    @FXML
    ImageView imageView, bgImageView, windImage, precipitationImage;


    private static Weather weather;

    public static Weather getWeather() {
        return weather;
    }

    public static void setWeather(Weather weather) {
        MainController.weather = weather;
    }
    public void initialize() {
        String time = weather.getLocation().getLocaltime().split(" ")[1];
        int hour = Integer.parseInt(time.split(":")[0]);
        if (hour > 12) {
            time = time.replace(String.valueOf(hour), String.valueOf(hour - 12));
            time += " pm";
        } else if (hour == 12) {
            time += "pm";
        } else {
            time += " am";
        }
        if (weather.getCurrent().getIs_day() == 1) {
            if (weather.getCurrent().getCondition().getText().equals("Sunny")) {
                bgImageView.setImage(new Image(getClass().getResourceAsStream("SunnyDay.jpg")));
            } else if (weather.getCurrent().getCondition().getText().contains("rain")) {
                bgImageView.setImage(new Image(getClass().getResourceAsStream("RainingDay.jpg")));
            } else if (weather.getCurrent().getCondition().getText().contains("snow")) {
                bgImageView.setImage(new Image(getClass().getResourceAsStream("SnowyDay.jpg")));
            }
        } else {
            if (weather.getCurrent().getCondition().getText().contains("rain")) {
                bgImageView.setImage(new Image(getClass().getResourceAsStream("RainingNight.jpg")));
            } else if (weather.getCurrent().getCondition().getText().contains("snow")) {
                bgImageView.setImage(new Image(getClass().getResourceAsStream("SnowyNight.jpg")));
            }
            bgImageView.setImage(new Image(getClass().getResourceAsStream("SunnyNight.jpg")));
        }
        windLabel.setText(weather.getCurrent().getWind_dir() + System.lineSeparator() + weather.getCurrent().getWind_kph() + " km/h");
        windImage.setImage(new Image(getClass().getResourceAsStream("wind.png")));
        precipitationImage.setImage(new Image("https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather07-512.png"));
        precipitationLabel.setText(String.valueOf(weather.getCurrent().getPrecip_mm()) + " mm");
        timeLabel.setText(time);
        tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_c()).concat("°C"));
        locationLabel.setText(weather.getLocation().getName());
        imageView.setImage(new Image("http://"+weather.getCurrent().getCondition().getIcon().substring(2)));
    }
}
