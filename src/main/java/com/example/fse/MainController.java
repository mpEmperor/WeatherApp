package com.example.fse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label tempLabel, timeLabel, locationLabel,countryLabel, windLabel, humidityLabel, pressureLabel,uvLabel, conditionLabel,LastUpdatedLabel,feelsLikeLabel, date, hour1Label, hour2Label, hour3Label, hour4Label, hour5Label, hour6Label, hour7Label, hour8Label, hour9Label, hour10Label, hour11Label, hour12Label, hour13Label, hour14Label, hour15Label, hour16Label, hour17Label, hour18Label, hour19Label, hour20Label, hour21Label, hour22Label, hour23Label, hour24Label,temp1Label, temp2Label, temp3Label, temp4Label, temp5Label, temp6Label, temp7Label, temp8Label, temp9Label, temp10Label, temp11Label, temp12Label, temp13Label, temp14Label, temp15Label, temp16Label, temp17Label, temp18Label, temp19Label, temp20Label, temp21Label, temp22Label, temp23Label, temp24Label;
    @FXML
    ImageView imageView, bgImageView, windImage, humidityImage1, humidityImage2, pressureImage, uvImage, hour1Image, hour2Image, hour3Image, hour4Image, hour5Image, hour6Image, hour7Image, hour8Image, hour9Image, hour10Image, hour11Image, hour12Image, hour13Image, hour14Image, hour15Image, hour16Image, hour17Image, hour18Image, hour19Image, hour20Image, hour21Image, hour22Image, hour23Image, hour24Image;
    @FXML
    CheckBox changeBox;
    Stage stage;
    Scene scene;
    Parent root;


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

        hourly();

        //Background
        if (weather.getCurrent().getIs_day() == 1) {
            if (weather.getCurrent().getCondition().getText().equals("Sunny")) {
                bgImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("SunnyDay.jpg"))));
            } else if (weather.getCurrent().getCondition().getText().contains("rain")) {
                bgImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("RainingDay.jpg"))));
            } else if (weather.getCurrent().getCondition().getText().contains("snow")) {
                bgImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("SnowyDay.jpg"))));
            }
        } else {
            if (weather.getCurrent().getCondition().getText().contains("rain")) {
                bgImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("RainingNight.jpg"))));
            } else if (weather.getCurrent().getCondition().getText().contains("snow")) {
                bgImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("SnowyNight.jpg"))));
            }
            bgImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("SunnyNight.jpg"))));
        }

        windLabel.setText(weather.getCurrent().getWind_dir() + System.lineSeparator() + weather.getCurrent().getWind_kph() + " km/h");
        windImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("wind.png"))));

        humidityImage1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("humidity1.png"))));
        humidityImage2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("humidity2.png"))));
        humidityLabel.setText((weather.getCurrent().getHumidity()) + "%");

        pressureLabel.setText((weather.getCurrent().getPressure_in() + " inHg"));
        pressureImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("pressure.png"))));

        uvImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("UV.png"))));
        uvLabel.setText("" + weather.getCurrent().getUv());

        timeLabel.setText(time);

        tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_c()).concat("°C"));

        locationLabel.setText(weather.getLocation().getName());
        countryLabel.setText(weather.getLocation().getCountry());
        conditionLabel.setText(weather.getCurrent().getCondition().getText());

        LastUpdatedLabel.setText("Last Updated: " + weather.getCurrent().getLast_updated());

        feelsLikeLabel.setText("Feels Like: " + weather.getCurrent().getFeelslike_c() + "°C");

        imageView.setImage(new Image("http://"+weather.getCurrent().getCondition().getIcon().substring(2)));


    }
    public void changeToF() {
        if (changeBox.isSelected()) {
            tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_f()).concat("°F"));
            feelsLikeLabel.setText("Feels Like: " + weather.getCurrent().getFeelslike_f() + "°F");
            windLabel.setText(weather.getCurrent().getWind_dir() + System.lineSeparator() + weather.getCurrent().getWind_mph() + " mph");
        } else {
            tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_c()).concat("°C"));
            feelsLikeLabel.setText("Feels Like: " + weather.getCurrent().getFeelslike_c() + "°C");
            windLabel.setText(weather.getCurrent().getWind_dir() + System.lineSeparator() + weather.getCurrent().getWind_kph() + " kph");
        }
    }
    public void chooseCity(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cityenter.fxml")));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void hourly(){
        hour1Label.setText("12am");
        hour2Label.setText("1am");
        hour3Label.setText("2am");
        hour4Label.setText("3am");
        hour5Label.setText("4am");
        hour6Label.setText("5am");
        hour7Label.setText("6am");
        hour8Label.setText("7am");
        hour9Label.setText("8am");
        hour10Label.setText("9am");
        hour11Label.setText("10am");
        hour12Label.setText("11am");
        hour13Label.setText("12pm");
        hour14Label.setText("1pm");
        hour15Label.setText("2pm");
        hour16Label.setText("3pm");
        hour17Label.setText("4pm");
        hour18Label.setText("5pm");
        hour19Label.setText("6pm");
        hour20Label.setText("7pm");
        hour21Label.setText("8pm");
        hour22Label.setText("9pm");
        hour23Label.setText("10pm");
        hour24Label.setText("11pm");
        hour1Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(0).getCondition().getIcon().substring(2)));
        hour2Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(1).getCondition().getIcon().substring(2)));
        hour3Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(2).getCondition().getIcon().substring(2)));
        hour4Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(3).getCondition().getIcon().substring(2)));
        hour5Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(4).getCondition().getIcon().substring(2)));
        hour6Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(5).getCondition().getIcon().substring(2)));
        hour7Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(6).getCondition().getIcon().substring(2)));
        hour8Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(7).getCondition().getIcon().substring(2)));
        hour9Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(8).getCondition().getIcon().substring(2)));
        hour10Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(9).getCondition().getIcon().substring(2)));
        hour11Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(10).getCondition().getIcon().substring(2)));
        hour12Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(11).getCondition().getIcon().substring(2)));
        hour13Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(12).getCondition().getIcon().substring(2)));
        hour14Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(13).getCondition().getIcon().substring(2)));
        hour15Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(14).getCondition().getIcon().substring(2)));
        hour16Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(15).getCondition().getIcon().substring(2)));
        hour17Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(16).getCondition().getIcon().substring(2)));
        hour18Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(17).getCondition().getIcon().substring(2)));
        hour19Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(18).getCondition().getIcon().substring(2)));
        hour20Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(19).getCondition().getIcon().substring(2)));
        hour21Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(20).getCondition().getIcon().substring(2)));
        hour22Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(21).getCondition().getIcon().substring(2)));
        hour23Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(22).getCondition().getIcon().substring(2)));
        hour24Image.setImage(new Image("http://" + weather.getForecast().getForecastday().get(1).getHour().get(23).getCondition().getIcon().substring(2)));
        temp1Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(0).getTemp_c());
        temp2Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(1).getTemp_c());
        temp3Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(2).getTemp_c());
        temp4Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(3).getTemp_c());
        temp5Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(4).getTemp_c());
        temp6Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(5).getTemp_c());
        temp7Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(6).getTemp_c());
        temp8Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(7).getTemp_c());
        temp9Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(8).getTemp_c());
        temp10Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(9).getTemp_c());
        temp11Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(10).getTemp_c());
        temp12Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(11).getTemp_c());
        temp13Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(12).getTemp_c());
        temp14Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(13).getTemp_c());
        temp15Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(14).getTemp_c());
        temp16Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(15).getTemp_c());
        temp17Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(16).getTemp_c());
        temp18Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(17).getTemp_c());
        temp19Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(18).getTemp_c());
        temp20Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(19).getTemp_c());
        temp21Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(20).getTemp_c());
        temp22Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(21).getTemp_c());
        temp23Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(22).getTemp_c());
        temp24Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(23).getTemp_c());

    }

}
