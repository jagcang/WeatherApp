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
       public class WxCurrent
       {
        // instance variables - replace the example below with your own
        private JsonElement jse = null;
        private final String apiKey = "6a54ade05689e570106c9d2c01c0caca";
        private static final DateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
        private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        private static DecimalFormat df2 = new DecimalFormat("#.##");
        
        public boolean getWx(String zipcode)
           {
               
            try
            {
                // Construct WxStation API URL
                URL wxURL = new URL("http://api.openweathermap.org/data/2.5/weather?zip="
                        + zipcode
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
            
            //Checks if zipcode was valid
            return isValid();
       }
       
       public boolean isValid()
       {
           try
           {
               //Zipcode is valid
                JsonArray obs = jse.getAsJsonObject().get("weather")
                .getAsJsonArray();
                String location = obs.get(0).getAsJsonObject()
                .get("id").getAsString();
                return true;
           }
           catch(java.lang.NullPointerException npe)
           {
               //Zipcode is  NOT valid
               return false;
           }
       
       }
       
       public String getLocation()
       {
           return jse.getAsJsonObject().get("name").getAsString();
       }
       
       public String getDate()
       {
           Date date = new Date();
           return sdf.format(date);
       }
       
       public String getWeather()
       {
           JsonArray obs = jse.getAsJsonObject().get("weather").getAsJsonArray();
           return obs.get(0).getAsJsonObject().get("main").getAsString();
       }
       
       public double getTemp()
       {
           double t = jse.getAsJsonObject().get("main").getAsJsonObject().
           get("temp").getAsDouble();
           return Math.round(t*10)/10.0;
       }
       
       public double getWindSpeed()
       {
           double w = jse.getAsJsonObject().get("wind").getAsJsonObject().
           get("speed").getAsDouble();
           return Math.round(w*10)/10.0;
       }
       
       public String getWindDirection()
       {
           int degree = jse.getAsJsonObject().get("wind").getAsJsonObject()
           .get("deg").getAsInt();
           String direction = "?";
           if(degree == 360 || degree == 0)
           {
                direction = "N";
           }
           else if(degree > 0 || degree < 90)
           {
                direction = "NE";
           }
           else if(degree == 90)
           {
                direction = "E";
           }
           else if(degree > 90 || degree < 180)
           {
                direction = "SE";
           }
           else if(degree == 180)
           {
                direction = "S";
           }
           else if(degree > 180 || degree < 270)
           {
                direction = "SW";
           }
           else if(degree == 270)
           {
                direction = "W";
           }
           else if(degree > 270 || degree < 360)
           {
               direction = "NW";
           } 
           
           return direction;
        
        }

    
    public double getPressure()
    {
        double p = jse.getAsJsonObject().get("main").getAsJsonObject()
        .get("pressure").getAsDouble()*0.02953;
        return Math.round(p*100)/100.0;
    }
    
    public int getHumidity()
    {
        return jse.getAsJsonObject().get("main").getAsJsonObject()
        .get("humidity").getAsInt();
    }
    
    public Image getImage()
    {
        
      JsonArray obs = jse.getAsJsonObject().get("weather").getAsJsonArray();
      String iconURL = "http://openweathermap.org/img/wn/" 
      + obs.get(0).getAsJsonObject().get("icon").getAsString() + "@2x.png";
      return new Image(iconURL);
    }
   
    
}
