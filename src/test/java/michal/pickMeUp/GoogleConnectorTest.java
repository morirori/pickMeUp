package michal.pickMeUp;


import com.google.maps.errors.ApiException;
import com.michal.pickMeUp.Utills.GoogleConnector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;


public class GoogleConnectorTest      extends TestCase {
    GoogleConnector connector;
    @Before
    public void createGoogleManger(){
        connector= new GoogleConnector();
    }

    @org.junit.Test
    public void testTimeAndDistance() throws InterruptedException, ApiException, IOException {
        connector= new GoogleConnector();
        System.out.println("Inside testPrintMessage()");
        HashMap map = connector.getElements("Kraków", "Tarnów");
        String distance = (String) map.get("distance");
        String time = (String) map.get("time");
        System.out.println(distance);
        System.out.println(time);
        //assertEquals()
    }

}
