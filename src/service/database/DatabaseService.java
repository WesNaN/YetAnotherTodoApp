package service.database;

import model.Label;
import model.Project;
import model.Task;
import service.ConnectionError;
import service.DataService;

import java.sql.*;
import java.util.logging.Logger;

public class DatabaseService implements DataService
{
    /**
     * This class handles database connection.
     */

    Connection con = null;
    private static final Logger LOGGER = Logger.getLogger(
            Thread.currentThread().getStackTrace()[0].getClassName());

    public DatabaseService() throws ConnectionError
    {
        connect();
        prepareDatabase();
    }

    public void connect() throws ConnectionError
    {
        try
        {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.warning("Driver not found");
            throw new ConnectionError(null, e);
        }
        catch (SQLException e)
        {
            LOGGER.warning("Cannot establish connection!");
            throw new ConnectionError(null, e);
        }
    }

    @Override
    public void addTask(Task task) throws ConnectionError
    {

    }

    @Override
    public void removeTask(Task task) throws ConnectionError
    {

    }

    @Override
    public void updateTask(Task task) throws ConnectionError
    {

    }

    @Override
    public void addProject(Project project) throws ConnectionError
    {

    }

    @Override
    public void removeProject(Project project) throws ConnectionError
    {

    }

    @Override
    public void addLabel(Label label) throws ConnectionError
    {

    }

    @Override
    public void removeLabel(Label label) throws ConnectionError
    {

    }

    public void prepareDatabase()
    {
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DROP TABLE table1");
            stmt.executeUpdate("CREATE TABLE table1 ( user varchar(50) )");
            stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Andre' )");
            stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Nicholas' )");

            ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
            while (rs.next())
            {
                String name = rs.getString("user");
                System.out.println(name);
            }
            stmt.close();
            con.close();
        }
        catch (SQLException e)
        {
            LOGGER.warning("");
            e.printStackTrace();
        }

    }
}
