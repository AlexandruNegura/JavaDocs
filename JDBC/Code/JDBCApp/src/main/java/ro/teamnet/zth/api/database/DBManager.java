package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static ro.teamnet.zth.api.database.DBProperties.PASS;
import static ro.teamnet.zth.api.database.DBProperties.USER;

/**
 * Created by Alexandru.Negura on 7/13/2017.
 */
public class DBManager {
    private DBManager() {
        throw new UnsupportedOperationException();
    }
    public static final String ONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT +
            ":xe";

    private static void registerDriver() throws ClassNotFoundException {
        Class.forName(DBProperties.DRIVER_CLASS);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        registerDriver();
        Connection connection = DriverManager.getConnection(ONNECTION_STRING, USER , PASS  );
        return connection;
    }

    public static boolean checkConnection(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        boolean result = statement.execute("SELECT 1 FROM DUAL");
        return result;
    }
}
