package service.database;

import model.Label;
import model.Project;
import model.Task;
import org.h2.tools.RunScript;
import service.ConnectionError;
import service.DataService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
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
        testDatabase();
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

    private void prepareDatabase()
    {
        try
        {
            URL url = getClass().getResource("prepare_database.sql");
            RunScript.execute(con, new FileReader(url.getPath()));
        }
        catch (SQLException e)
        {
            LOGGER.warning("Error in prepare_database.sql script, cannot execute it!");
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            LOGGER.warning("'prepare_database.sql' file not found!");
            e.printStackTrace();
        }
    }

    public void testDatabase()
    {

        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("INSERT INTO Tasks(id, name, content, priority, label, project_id) " +
                    "VALUES (?,?,?,?,?,?);");
            stmt.setInt(1, 1);
            stmt.setString(2, "Dishes");
            stmt.setString(3, "Do the dishes");
            stmt.setInt(4, 2);
            stmt.setString(5, "Label1");
            stmt.setInt(6, 232);
            stmt.executeUpdate();

            stmt = con.prepareStatement("INSERT INTO Tasks(id, name, content, priority, label, project_id) " +
                    "VALUES (?,?,?,?,?,?);");

            stmt.setInt(1, 2);
            stmt.setString(2, "Program");
            stmt.setString(3, "Java");
            stmt.setInt(4, 1);
            stmt.setString(5, "Label2");
            stmt.setInt(6, 234);
            stmt.executeUpdate();

            stmt = con.prepareStatement("SELECT * FROM Tasks");
            ResultSet set = stmt.executeQuery();

            while (set.next())
            {
                System.out.println("Id = " + set.getInt(1));
                System.out.println("Name = " + set.getString(2));
                System.out.println("Content = " + set.getString(3));
                System.out.println("Priority = " + set.getInt(4));
                System.out.println("Label = " + set.getString(5));
                System.out.println("Project id = " + set.getInt(6));
            }
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while testing the database!");
            e.printStackTrace();
        }
    }
}
