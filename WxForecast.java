import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import com.google.gson.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
    /**
     * Write a description of class WxModel here.
     *
     * @author (John Agcang)
     * @version (5/3/2020)
     */
       public class WxForecast
       {
        // instance variables - replace the example below with your own
        private JsonElement jse = null;
        private final String apiKey = "6a54ade05689e570106c9d2c01c0caca";
        
        public boolean getWx(String zipcode)
           {
               
            try
            {
                // Construct WxStation API URL
                URL wxURL = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="
                        + zipcode
                        + "&appid=" 
                        + apiKey);
    
                // Open the URL
                InputStream is = wxURL.openStream(); // throws an IOException
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
    
                // Read the result into a JSON Element
                jse = new JsonParser().parse(br);
          
                // Close the connection
                is.close();
                br.close();
            }
            catch (java.io.UnsupportedEncodingException uee)
            {
                System.out.println("oops, unsupported encoding exception");
                uee.printStackTrace();
            }
            catch (java.net.MalformedURLException mue)
            {
                System.out.println("oops, malformed url exception");
                mue.printStackTrace();
            }
            catch (java.io.IOException ioe)
            {
                System.out.println("Error: No cities match your search query");          
            }
            
            //Checks if zipcode was valid
            return isValid();
       }
       
       public boolean isValid()
       {
           try
           {
               //Zipcode is valid
              String location = jse.getAsJsonObject().get("city").getAsJsonObject().get("name")
              .getAsString();
              return true;
           }
           catch(java.lang.NullPointerException npe)
           {
               //Zipcode is  NOT valid
               return false;
           }
       
       }
       
       private String getForecastData(int day, String attribute) 
       {
          if (jse == null) getWx("forecasts");

          return jse.getAsJsonObject()
          .get("response").getAsJsonArray()
          .get(0).getAsJsonObject()
          .get("list").getAsJsonArray()
          .get(day).getAsJsonObject()
          .get(attribute).getAsString();
       }
       
    
       
       
    public String[] getHighForecast() {
        String[] highs = new String[7];

        for (int i = 0; i < highs.length; i++) {
            highs[i] = getForecastData(i, "temp_max");
            highs[i] = highs[i] + "°F";
        }

        return highs;
    }
            
        
            
            

       
      
}
