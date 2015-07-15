package service.database;

import service.ConnectionError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnectionFactory
{
    /**
     * This class handles database DBConnection.
     */

    public static final DatabaseConnectionFactory INSTANCE = new DatabaseConnectionFactory();
    private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    private DatabaseConnectionFactory() {}

    public static Connection getConnection(String databaseName, String username, String password) throws ConnectionError
    {
        Connection connection = null;
        try
        {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/" + databaseName, username, password);
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.warning("Driver not found");
            throw new ConnectionError(null, e);
        }
        catch (SQLException e)
        {
            LOGGER.warning("Cannot establish DBConnection!");
            throw new ConnectionError(null, e);
        }

        return connection;
    }

    public static DatabaseConnectionFactory getINSTANCE()
    {
        return INSTANCE;
    }
}
