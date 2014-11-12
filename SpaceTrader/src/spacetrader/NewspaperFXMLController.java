/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class NewspaperFXMLController implements Initializable {
    private Weather weather;
    private String con, tem, pre, hum;
    private Image img;
    @FXML
    private Pane weatherPane;
    @FXML
    private ImageView pictureView;
    @FXML
    private Label condition;
    @FXML
    private Label temp;
    @FXML
    private Label precip;
    @FXML
    private Label humid;

    /**
     * Initializes the controller class. Generates the weather conditions
     * and then sets all of the labels to the correct values in the newspaper
     * screen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        weather = new Weather();
        con = weather.getCurrentCondition();
        tem = weather.getTemp();
        pre = weather.getChanceOfPrecipitation();
        hum = weather.getHumidity();
        condition.setText(con);
        temp.setText("Temperature: " + tem);
        precip.setText("Precipitation: " + pre);
        humid.setText("Humidity: " + hum);
        img = genImage();
        pictureView.setImage(img);
    }
    /**
     * Helper method to generate the correct weather image.
     * based on the current weather conditions.
     * @return Image
     */
    private Image genImage() {
        int t = Integer.parseInt(tem.substring(0, tem.indexOf(' ')));
        int p = Integer.parseInt(pre.substring(0, pre.indexOf('%')));
        switch (con) {
            case "Sunny":
                if (t >= 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather1.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather6.png");
                    }
                } else if (t > 30 && t < 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather8.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather5.png");
                    }
                } else {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather3.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather15.png");
                    }
                }
                break;
            case "Overcast":
               if (t >= 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather8.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather2.png");
                    }
                } else if (t > 30 && t < 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather8.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather5.png");
                    }
                } else {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather11.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather15.png");
                    }
                }
               break;
            case "Cloudy":
                if (t >= 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather10.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather14.png");
                    }
                } else if (t > 30 && t < 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather12.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather9.png");
                    }
                } else {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather4.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather16.png");
                    }
                }
               break;
            case "Rainy":
                if (t >= 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather17.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather14.png");
                    }
                } else if (t > 30 && t < 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather9.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather9.png");
                    }
                } else {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather9.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather12.png");
                    }
                }
               break;
            case "Snowy":
                if (t >= 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather9.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather12.png");
                    }
                } else if (t > 30 && t < 70) {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather12.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather4.png");
                    }
                } else {
                    if (p <= 40) {
                        img = new Image("/spacetrader/resources/Weather4.png");
                    } else {
                        img = new Image("/spacetrader/resources/Weather16.png");
                    }
                }
               break;
            case "Frightful":
                img = new Image("/spacetrader/resources/Weather7.png");
                break;
            default:
                img = new Image("/spacetrader/resources/Weather1.png");
                break;
        }
        return img;
    }
}
