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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.Calendar;
    /**
     * Write a description of class WxModel here.
     *
     * @author (John Agcang)
     * @version (5/23/2020)
     */
       public class WxForecast
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
                URL wxURL = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="
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
                String message = jse.getAsJsonObject().get("message").getAsString();
                return true;
           }
           catch (java.lang.NullPointerException npe)
           {
               // We did not see error so this is a valid zip
               return false;
           }
       }
       
       private String getForecastMainData(int day, String attribute) 
       {
          return jse.getAsJsonObject()
          .get("list").getAsJsonArray()
          .get(day*8).getAsJsonObject()
          .get("main").getAsJsonObject()
          .get(attribute).getAsString();
       }
       
       private String getForecastWeatherData(int day, String attribute) 
       {
          return jse.getAsJsonObject()
          .get("list").getAsJsonArray()
          .get(day).getAsJsonObject()
          .get("weather").getAsJsonArray()
          .get(0).getAsJsonObject()
          .get(attribute).getAsString();
       }
       
       
        
       public String[] getHighForecast() 
       {
           String[] highs = new String[5];

            for (int i = 0; i < highs.length; i++) 
           {
               highs[i] = getForecastMainData(i, "temp_max");
               highs[i] = highs[i] + "°F";
           }

           return highs;
       } 
       
        public String[] getLowForecast() 
       {
           String[] lows = new String[5];

            for (int i = 0; i < lows.length; i++) 
           {
               lows[i] = getForecastMainData(i, "temp_min");
               lows[i] = lows[i] + "°F";
           }

           return lows;
       }
       
            public Image[] getIcons() 
        {
            Image[] icons = new Image[5];

            for (int i = 0; i < icons.length; i++) 
            {
                String url = "http://openweathermap.org/img/wn/" + getForecastWeatherData(i*8, "icon") + "@2x.png";
                Image icon = new Image(url);

                icons[i] = new Image(url);
            }
        

            return icons;
       }
       
          public String[] getDate() 
       {
           String[] dates = new String[5];

            for (int i = 0; i < dates.length; i++) 
           {
               dates[i] =  jse.getAsJsonObject().get("list").getAsJsonArray().get(i*8).getAsJsonObject().get("dt_txt").getAsString();
               
           }

           return dates;
       } 
}
