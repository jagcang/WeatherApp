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
     * @version (5/23/2020)
     */
       public class Wx
       {
        // instance variables - replace the example below with your own
        private JsonElement jse = null;
        private final String apiKey = "6a54ade05689e570106c9d2c01c0caca";
        
      
        public boolean getWx(double lat, double lon)
           {
              
            try
            {
                // Construct WxStation API URL
                URL wxURL = new URL("http://api.openweathermap.org/data/2.5/onecall?lat="
                        + lat
                        + "&lon="
                        + lon
                        + "&units=imperial&appid=" 
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
            
            return isValid();
        }
        
         public boolean isValid()
       {
        
           try 
           {
               //Lat and Lon is valid
                String message = jse.getAsJsonObject().get("message")
                .getAsString();
                return false;
           }
           catch (java.lang.NullPointerException npe)
           {
               // We did not see error so this is a valid lat and lon
               return true;
           }
        }

        
        public String getSunset() 
       {
          return jse.getAsJsonObject()
          .get("current").getAsJsonObject()
          .get("sunset").getAsString();

        
       }
       
       
        public String getSunrise() 
       {
          return jse.getAsJsonObject()
          .get("current").getAsJsonObject()
          .get("sunrise").getAsString();
        
       }
    
}

        
        
         