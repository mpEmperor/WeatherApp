package com.example.fse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
    Label tempLabel, timeLabel, locationLabel,countryLabel, windLabel, humidityLabel, pressureLabel,uvLabel, conditionLabel,LastUpdatedLabel,feelsLikeLabel, date, hour1Label, hour2Label, hour3Label, hour4Label, hour5Label, hour6Label, hour7Label, hour8Label, hour9Label, hour10Label, hour11Label, hour12Label, hour13Label, hour14Label, hour15Label, hour16Label, hour17Label, hour18Label, hour19Label, hour20Label, hour21Label, hour22Label, hour23Label, hour24Label,temp1Label, temp2Label, temp3Label, temp4Label, temp5Label, temp6Label, temp7Label, temp8Label, temp9Label, temp10Label, temp11Label, temp12Label, temp13Label, temp14Label, temp15Label, temp16Label, temp17Label, temp18Label, temp19Label, temp20Label, temp21Label, temp22Label, temp23Label, temp24Label, temp1Label1, temp2Label1, temp3Label1, temp4Label1, temp5Label1, temp6Label1, temp7Label1, temp8Label1, temp9Label1, temp10Label1, temp11Label1, temp12Label1, temp13Label1, temp14Label1, temp15Label1, temp16Label1, temp17Label1, temp18Label1, temp19Label1, temp20Label1, temp21Label1, temp22Label1, temp23Label1, temp24Label1, hour1Label1, hour2Label1, hour3Label1, hour4Label1, hour5Label1, hour6Label1, hour7Label1, hour8Label1, hour9Label1, hour10Label1, hour11Label1, hour12Label1, hour13Label1, hour14Label1, hour15Label1, hour16Label1, hour17Label1, hour18Label1, hour19Label1, hour20Label1, hour21Label1, hour22Label1, hour23Label1, hour24Label1;
    @FXML
    ImageView imageView, bgImageView, windImage, humidityImage1, humidityImage2, pressureImage, uvImage, hour1Image, hour2Image, hour3Image, hour4Image, hour5Image, hour6Image, hour7Image, hour8Image, hour9Image, hour10Image, hour11Image, hour12Image, hour13Image, hour14Image, hour15Image, hour16Image, hour17Image, hour18Image, hour19Image, hour20Image, hour21Image, hour22Image, hour23Image, hour24Image, hour1Image1, hour2Image1, hour3Image1, hour4Image1, hour5Image1, hour6Image1, hour7Image1, hour8Image1, hour9Image1, hour10Image1, hour11Image1, hour12Image1, hour13Image1, hour14Image1, hour15Image1, hour16Image1, hour17Image1, hour18Image1, hour19Image1, hour20Image1, hour21Image1, hour22Image1, hour23Image1, hour24Image1;
    @FXML
    CheckBox changeBox;
    @FXML
    LineChart trendGraph;
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
        XYChart.Series<Number, Number> series = new XYChart.Series();
        for (int i = 0; i < weather.getForecast().precipTrendMM().size(); i ++) {
            series.getData().add(new XYChart.Data<>(i, Math.round(weather.getForecast().precipTrendMM().get(i))));
        }
        trendGraph.getData().add(series);

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

        hourlyNextDay();
        hourlyCurrentDay();

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


        System.out.println(weather.getForecast().getAverageCelsius());
    }
    public void changeToF() {
        if (changeBox.isSelected()) {
            tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_f()).concat("°F"));
            feelsLikeLabel.setText("Feels Like: " + weather.getCurrent().getFeelslike_f() + "°F");
            windLabel.setText(weather.getCurrent().getWind_dir() + System.lineSeparator() + weather.getCurrent().getWind_mph() + " mph");
            temp1Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(0).getTemp_f() + "°F");
            temp2Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(1).getTemp_f() + "°F");
            temp3Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(2).getTemp_f() + "°F");
            temp4Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(3).getTemp_f() + "°F");
            temp5Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(4).getTemp_f() + "°F");
            temp6Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(5).getTemp_f() + "°F");
            temp7Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(6).getTemp_f() + "°F");
            temp8Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(7).getTemp_f() + "°F");
            temp9Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(8).getTemp_f() + "°F");
            temp10Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(9).getTemp_f() + "°F");
            temp11Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(10).getTemp_f() + "°F");
            temp12Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(11).getTemp_f() + "°F");
            temp13Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(12).getTemp_f() + "°F");
            temp14Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(13).getTemp_f() + "°F");
            temp15Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(14).getTemp_f() + "°F");
            temp16Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(15).getTemp_f() + "°F");
            temp17Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(16).getTemp_f() + "°F");
            temp18Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(17).getTemp_f() + "°F");
            temp19Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(18).getTemp_f() + "°F");
            temp20Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(19).getTemp_f() + "°F");
            temp21Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(20).getTemp_f() + "°F");
            temp22Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(21).getTemp_f() + "°F");
            temp23Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(22).getTemp_f() + "°F");
            temp24Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(23).getTemp_f() + "°F");

        } else {
            tempLabel.setText(String.valueOf(weather.getCurrent().getTemp_c()).concat("°C"));
            feelsLikeLabel.setText("Feels Like: " + weather.getCurrent().getFeelslike_c() + "°C");
            windLabel.setText(weather.getCurrent().getWind_dir() + System.lineSeparator() + weather.getCurrent().getWind_kph() + " kph");
            temp1Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(0).getTemp_c() + "°C");
            temp2Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(1).getTemp_c() + "°C");
            temp3Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(2).getTemp_c() + "°C");
            temp4Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(3).getTemp_c() + "°C");
            temp5Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(4).getTemp_c() + "°C");
            temp6Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(5).getTemp_c() + "°C");
            temp7Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(6).getTemp_c() + "°C");
            temp8Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(7).getTemp_c() + "°C");
            temp9Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(8).getTemp_c() + "°C");
            temp10Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(9).getTemp_c() + "°C");
            temp11Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(10).getTemp_c() + "°C");
            temp12Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(11).getTemp_c() + "°C");
            temp13Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(12).getTemp_c() + "°C");
            temp14Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(13).getTemp_c() + "°C");
            temp15Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(14).getTemp_c() + "°C");
            temp16Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(15).getTemp_c() + "°C");
            temp17Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(16).getTemp_c() + "°C");
            temp18Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(17).getTemp_c() + "°C");
            temp19Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(18).getTemp_c() + "°C");
            temp20Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(19).getTemp_c() + "°C");
            temp21Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(20).getTemp_c() + "°C");
            temp22Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(21).getTemp_c() + "°C");
            temp23Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(22).getTemp_c() + "°C");
            temp24Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(23).getTemp_c() + "°C");
        }
    }
    public void chooseCity(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cityenter.fxml")));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void hourlyNextDay(){
        date.setText("" + weather.getForecast().getForecastday().get(1).getDate());
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
        temp1Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(0).getTemp_c() + "°C");
        temp2Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(1).getTemp_c() + "°C");
        temp3Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(2).getTemp_c() + "°C");
        temp4Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(3).getTemp_c() + "°C");
        temp5Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(4).getTemp_c() + "°C");
        temp6Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(5).getTemp_c() + "°C");
        temp7Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(6).getTemp_c() + "°C");
        temp8Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(7).getTemp_c() + "°C");
        temp9Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(8).getTemp_c() + "°C");
        temp10Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(9).getTemp_c() + "°C");
        temp11Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(10).getTemp_c() + "°C");
        temp12Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(11).getTemp_c() + "°C");
        temp13Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(12).getTemp_c() + "°C");
        temp14Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(13).getTemp_c() + "°C");
        temp15Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(14).getTemp_c() + "°C");
        temp16Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(15).getTemp_c() + "°C");
        temp17Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(16).getTemp_c() + "°C");
        temp18Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(17).getTemp_c() + "°C");
        temp19Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(18).getTemp_c() + "°C");
        temp20Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(19).getTemp_c() + "°C");
        temp21Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(20).getTemp_c() + "°C");
        temp22Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(21).getTemp_c() + "°C");
        temp23Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(22).getTemp_c() + "°C");
        temp24Label.setText("" + weather.getForecast().getForecastday().get(1).getHour().get(23).getTemp_c() + "°C");

    }
    public void hourlyCurrentDay(){
        date.setText("" + weather.getForecast().getForecastday().get(1).getDate());
        hour1Label1.setText("12am");
        hour2Label1.setText("1am");
        hour3Label1.setText("2am");
        hour4Label1.setText("3am");
        hour5Label1.setText("4am");
        hour6Label1.setText("5am");
        hour7Label1.setText("6am");
        hour8Label1.setText("7am");
        hour9Label1.setText("8am");
        hour10Label1.setText("9am");
        hour11Label1.setText("10am");
        hour12Label1.setText("11am");
        hour13Label1.setText("12pm");
        hour14Label1.setText("1pm");
        hour15Label1.setText("2pm");
        hour16Label1.setText("3pm");
        hour17Label1.setText("4pm");
        hour18Label1.setText("5pm");
        hour19Label1.setText("6pm");
        hour20Label1.setText("7pm");
        hour21Label1.setText("8pm");
        hour22Label1.setText("9pm");
        hour23Label1.setText("10pm");
        hour24Label1.setText("11pm");
        hour1Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(0).getCondition().getIcon().substring(2)));
        hour2Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(1).getCondition().getIcon().substring(2)));
        hour3Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(2).getCondition().getIcon().substring(2)));
        hour4Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(3).getCondition().getIcon().substring(2)));
        hour5Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(4).getCondition().getIcon().substring(2)));
        hour6Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(5).getCondition().getIcon().substring(2)));
        hour7Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(6).getCondition().getIcon().substring(2)));
        hour8Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(7).getCondition().getIcon().substring(2)));
        hour9Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(8).getCondition().getIcon().substring(2)));
        hour10Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(9).getCondition().getIcon().substring(2)));
        hour11Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(10).getCondition().getIcon().substring(2)));
        hour12Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(11).getCondition().getIcon().substring(2)));
        hour13Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(12).getCondition().getIcon().substring(2)));
        hour14Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(13).getCondition().getIcon().substring(2)));
        hour15Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(14).getCondition().getIcon().substring(2)));
        hour16Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(15).getCondition().getIcon().substring(2)));
        hour17Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(16).getCondition().getIcon().substring(2)));
        hour18Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(17).getCondition().getIcon().substring(2)));
        hour19Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(18).getCondition().getIcon().substring(2)));
        hour20Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(19).getCondition().getIcon().substring(2)));
        hour21Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(20).getCondition().getIcon().substring(2)));
        hour22Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(21).getCondition().getIcon().substring(2)));
        hour23Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(22).getCondition().getIcon().substring(2)));
        hour24Image1.setImage(new Image("http://" + weather.getForecast().getForecastday().get(0).getHour().get(23).getCondition().getIcon().substring(2)));
        temp1Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(0).getTemp_c() + "°C");
        temp2Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(1).getTemp_c() + "°C");
        temp3Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(2).getTemp_c() + "°C");
        temp4Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(3).getTemp_c() + "°C");
        temp5Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(4).getTemp_c() + "°C");
        temp6Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(5).getTemp_c() + "°C");
        temp7Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(6).getTemp_c() + "°C");
        temp8Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(7).getTemp_c() + "°C");
        temp9Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(8).getTemp_c() + "°C");
        temp10Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(9).getTemp_c() + "°C");
        temp11Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(10).getTemp_c() + "°C");
        temp12Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(11).getTemp_c() + "°C");
        temp13Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(12).getTemp_c() + "°C");
        temp14Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(13).getTemp_c() + "°C");
        temp15Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(14).getTemp_c() + "°C");
        temp16Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(15).getTemp_c() + "°C");
        temp17Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(16).getTemp_c() + "°C");
        temp18Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(17).getTemp_c() + "°C");
        temp19Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(18).getTemp_c() + "°C");
        temp20Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(19).getTemp_c() + "°C");
        temp21Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(20).getTemp_c() + "°C");
        temp22Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(21).getTemp_c() + "°C");
        temp23Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(22).getTemp_c() + "°C");
        temp24Label1.setText("" + weather.getForecast().getForecastday().get(0).getHour().get(23).getTemp_c() + "°C");
    }


}
