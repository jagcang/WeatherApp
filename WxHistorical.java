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
 * Write a description of class WxHistorical here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WxHistorical
{
   // instance variables - replace the example below with your own
        private JsonElement jse = null;
        private final String apiKey = "6a54ade05689e570106c9d2c01c0caca";
        
      
        public boolean getWx(double lat, double lon, int dt)
           {
              
            try
            {
                // Construct WxStation API URL
                URL wxURL = new URL("http://api.openweathermap.org/data/2.5/onecall/timemachine?lat="
                        + lat
                        + "&lon="
                        + lon
                        + "&dt="
                        + dt
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
        
         public String getHistoricalWeatherData(int hour, String attribute) 
       {
          return jse.getAsJsonObject()
          .get("hourly").getAsJsonArray()
          .get(hour).getAsJsonObject()
          .get("weather").getAsJsonArray()
          .get(0).getAsJsonObject()
          .get("description").getAsString();
       }
       
        private String getHistoricalWeather2Data(int hour, String attribute) 
       {
          return jse.getAsJsonObject()
          .get("hourly").getAsJsonArray()
          .get(hour).getAsJsonObject()
          .get(attribute).getAsString();
       }
       
       public String [] getHistoricalTemp()
       {
           String[] t = new String[5];

            for (int i = 0; i < t.length; i++) 
           {
               t[i] = getHistoricalWeather2Data(i, "temp");
               t[i] = t[i] + "°F";
           }

           return t;
       }
       
        public String [] getHistoricalWeather()
       {
           String[] c = new String[5];

            for (int i = 0; i < c.length; i++) 
           {
               c[i] = getHistoricalWeatherData(i, "description");
           }

           return c;
       }
       
        
        
        
}
