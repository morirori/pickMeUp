package michal.pickMeUp;


import com.michal.pickMeUp.Utills.DBConnectionManager;
import com.michal.pickMeUp.Model.DAO.RideDAO;
import com.michal.pickMeUp.Model.Entity.Ride;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Exception.FinishedRideException;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@RunWith(JUnit4.class)

public class RideDAOTest extends TestCase {
    DBConnectionManager manager;
    Statement statement;
    Ride ride;
    User user;

    @Before
    public void setUP(){

        String startPoint= "Kraków";
        String endPoint= "Tarnów";
        Boolean isFinished= true;
        Date date = new Date(1017
                , 11
                , 12
                , 23
                , 23);
        Integer freeSpaces=4;
        Float cost= Float.parseFloat("25");
        manager=new DBConnectionManager();
        ride = new Ride(startPoint,endPoint,isFinished,date,freeSpaces,cost);
        user=new User("michal","morawiec","123456789123","cwikow 99 ","bmw","michal@gmail.com","76476912", 18);
    }

    @Test
    public void createRideTest() throws FinishedRideException, SQLException {
        manager= new DBConnectionManager();
        manager.establishConnection();
        RideDAO dao = new RideDAO();
        dao.createRide(user,ride.getStartPoint(),ride.getEndPoint(),ride.getStart(),ride.getCost(),ride.getFinished(),ride.getFreeSpaces());
        statement = manager.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM projekt.rides order by RideID desc limit 1 ");
        rs.next();
        System.out.println("\ninput Time " + ride.getTime() +" recived Time "+ rs.getString("esitmatedTime") );
        System.out.println("\ninput Dist " + ride.getDistace() +" recived Time "+ rs.getString("distance") );
        assertTrue(" OK ", ride.getStartPoint().equals(rs.getString("startPoint")));
        assertTrue(" OK ", ride.getEndPoint().equals(rs.getString("endPoint")));
        assertTrue(" OK ", ride.getDistace().equals(rs.getString("distance")));
        assertTrue(" OK ", ride.getTime().equals(rs.getString("esitmatedTime")));


    }

}
