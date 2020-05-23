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
  private ImageView iconWx;
  
  @FXML
  private void handleButtonAction(ActionEvent e) {
 
    // Has the go button been pressed?
    if(e.getSource() == btnGetWx)
    {
           WxForecast weather = new WxForecast();
           WxCurrent w = new WxCurrent();
           String[] highs;
        
        // Get zipcode
         String zipcode = txtZipcode.getText();
         highs = weather.getHighForecast();
        if (weather.getWx(zipcode))
        {
            lblCityState.setText(w.getLocation());
            lblHighT1.setText(highs[1]);
            lblHighT2.setText(highs[2]);
            lblHighT3.setText(highs[3]);
            lblHighT4.setText(highs[4]);
            lblHighT5.setText(highs[5]);
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
