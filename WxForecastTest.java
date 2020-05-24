

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WxForecastTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WxForecastTest
{
    /**
     * Default constructor for test class WxForecastTest
     */
    public WxForecastTest()
    {
    }

    @Test
     public void TestgetForecastMaxTemp()
    {
       WxForecast f = new WxForecast();
       f.getWx("95747");
       assertEquals(true, f.isValid());
       assertEquals(5, f.getHighForecast().length);
    }
    
     @Test
     public void TestgetForecastLowTemp()
    {
       WxForecast f = new WxForecast();
       f.getWx("95747");
       assertEquals(true, f.isValid());
       assertEquals(5, f.getLowForecast().length);
    }
}
