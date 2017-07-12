package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexandru.Negura on 7/13/2017.
 */
public class DBManagerTest {
    @Test
    public void testConnection(){
        try {
            Connection connection = DBManager.getConnection();
            assertNotNull("Should not be null", connection);

            boolean test = DBManager.checkConnection(connection);

            assertTrue("Should be true", test);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
