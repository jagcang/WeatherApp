

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WxCurrentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WxCurrentTest
{
    /**
     * Default constructor for test class WxCurrentTest
     */
    public WxCurrentTest()
    {
    }

    @Test
    public void TestgetWx()
    {
        WxCurrent w = new WxCurrent(); 
        w.getWx("95747");
        assertEquals(true, w.isValid());
        
    }
    
    @Test
    public void TestgetWxLocation()
    {
       WxCurrent w = new WxCurrent();
       w.getWx("95747");
       assertEquals(true, w.isValid());
       assertEquals("Roseville", w.getLocation());
    }
}
