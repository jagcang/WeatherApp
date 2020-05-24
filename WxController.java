/*
 * WxController
 * This is the controller for the FXML document that contains the view. 
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author John Agcang
 */
public class WxController implements Initializable {
  
  @FXML
  private Button btnGetWx;

  @FXML
  private TextField txtZipcode;
  
  @FXML
  private Label lblCityState;

  @FXML
  private Label lblHighT1;
  
   @FXML
  private Label lblHighT2;
  
   @FXML
  private Label lblHighT3;
  
   @FXML
  private Label lblHighT4;
  
   @FXML
  private Label lblHighT5;
  
  
  @FXML
  private Label lblLowT1;
  
   @FXML
  private Label lblLowT2;
  
   @FXML
  private Label lblLowT3;
  
   @FXML
  private Label lblLowT4;
  
   @FXML
  private Label lblLowT5;
  
  @FXML
  private ImageView iconWx;
    
  @FXML
  private ImageView iconWx1;
  
   @FXML
  private ImageView iconWx2;
  
   @FXML
  private ImageView iconWx3;
  
   @FXML
  private ImageView iconWx4;
  
   @FXML
  private ImageView iconWx5;
  
  @FXML
  private ImageView iconSunset;
  
  @FXML
  private ImageView iconSunrise;
  
  @FXML
  private Label lblDateT1;
  
   @FXML
  private Label lblDateT2;
  
   @FXML
  private Label lblDateT3;
  
   @FXML
  private Label lblDateT4;
  
   @FXML
  private Label lblDateT5;
  
   @FXML
  private Label lblLat;
  
   @FXML
  private Label lblLon;
  
  @FXML
  private Label lblSunset;
  
  @FXML
  private Label lblSunrise;
  
  @FXML
  private Label lblHTemp1;
  
   @FXML
  private Label lblHTemp2;
  
   @FXML
  private Label lblHTemp3;
  
   @FXML
  private Label lblHTemp4;
  
   @FXML
  private Label lblHTemp5;
  
  @FXML
  private Label lblW1;
  
   @FXML
  private Label lblW2;
  
   @FXML
  private Label lblW3;
  
   @FXML
  private Label lblW4;
  
   @FXML
  private Label lblW5;
  
  
  @FXML
  private void handleButtonAction(ActionEvent e) {
 
    // Has the go button been pressed?
    if(e.getSource() == btnGetWx)
    {
           WxForecast weather = new WxForecast();
           WxCurrent w = new WxCurrent();
           Wx f = new Wx();
           WxHistorical h = new WxHistorical();
           String[] highs;
           String[] lows;
           String[] dates;
           Image[] icons;
           String[] temp;
           String[] condition;
        
        // Get zipcode
         String zipcode = txtZipcode.getText();
         //System.out.println("Input from interface: " + zipcode);

        // Check if your zipcode is valid 
        if (weather.getWx(zipcode) && w.getWx(zipcode) && f.getWx(w.getLatC(), w.getLonC()) && h.getWx(w.getLatC(), w.getLonC(), w.getDT()))
        {
           
                highs = weather.getHighForecast();
                lows = weather.getLowForecast();
                icons = weather.getIcons();
                dates = weather.getDate();
                temp = h.getHistoricalTemp();
                condition = h.getHistoricalWeather();

                lblCityState.setText(w.getLocation());
                
                
               
                lblSunset.setText(f.getSunset());
                lblSunrise.setText(f.getSunrise());
                lblLat.setText(String.valueOf(w.getLatC()));
                lblLon.setText(String.valueOf(w.getLonC()));
                lblHighT1.setText(highs[0]);
                lblHighT2.setText(highs[1]);
                lblHighT3.setText(highs[2]);
                lblHighT4.setText(highs[3]);
                lblHighT5.setText(highs[4]);
                lblLowT1.setText(lows[0]);
                lblLowT2.setText(lows[1]);
                lblLowT3.setText(lows[2]);
                lblLowT4.setText(lows[3]);
                lblLowT5.setText(lows[4]);
                iconWx1.setImage(icons[0]);
                iconWx2.setImage(icons[1]);
                iconWx3.setImage(icons[2]);
                iconWx4.setImage(icons[3]);
                iconWx5.setImage(icons[4]);
                lblDateT1.setText(dates[0]);
                lblDateT2.setText(dates[1]);
                lblDateT3.setText(dates[2]);
                lblDateT4.setText(dates[3]);
                lblDateT5.setText(dates[4]);
                iconSunset.setImage(new Image("Sunset.png"));
                iconSunrise.setImage(new Image("Sunrise.png"));
                lblHTemp1.setText(temp[0]);
                lblHTemp2.setText(temp[1]);
                lblHTemp3.setText(temp[2]);
                lblHTemp4.setText(temp[3]);
                lblHTemp5.setText(temp[4]);
                lblW1.setText(condition[0]);
                lblW2.setText(condition[1]);
                lblW3.setText(condition[2]);
                lblW4.setText(condition[3]);
                lblW5.setText(condition[4]);
                
        }
        else
        {
            lblCityState.setText("Invalid Zipcode");
            lblHighT1.setText("");
            lblHighT2.setText("");
            lblHighT3.setText("");
            lblHighT4.setText("");
            lblHighT5.setText("");
            iconWx.setImage(new Image("badzipcode.png"));
        }
    }
  }
 

  @Override
  public void initialize(URL url, ResourceBundle rb) 
  {
    WxForecast weather = new WxForecast();
    WxCurrent w = new WxCurrent();
  }    

}
