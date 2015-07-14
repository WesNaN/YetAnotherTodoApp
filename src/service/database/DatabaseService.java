package service.database;

import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;
import model.Label;
import model.Project;
import org.h2.tools.RunScript;
import service.ConnectionError;
import service.DataService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * This class handles database connection.
 */
public class DatabaseService implements DataService
{
    private String user = "user";
    private String pass = "pass";
    private String DBname = user + "DB";

    Connection DBconnection = null;
    private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    public DatabaseService() throws ConnectionError
    {
        connect();
        prepareDatabase(); //todo: only if database is not present or new user\pass
       // testDatabase();

    }

    private void connect() throws ConnectionError
    {
        try
        {
            Class.forName("org.h2.Driver");
            //DBconnection = DriverManager.getConnection("jdbc:h2:~/" + DBname, user, pass);
            DBconnection = DriverManager.getConnection("jdbc:h2:~/DB", user, pass);
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
    public void addTask(Task task, Calendar calendar, Project project) throws ConnectionError
    {
        PreparedStatement stmt = null;

        try
        {
            stmt = DBconnection.prepareStatement("INSERT INTO Tasks(id, title, description, priority, label, due, project_id, calendar_id) " +
                    "VALUES (?,?,?,?,?,?,?,?);");

            stmt.setInt(1, task.getTaskId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setInt(4, task.getPriority());
            stmt.setString(5, task.getLabel().getName());
            stmt.setTimestamp(6, Timestamp.valueOf(task.getDue()));
            stmt.setInt(7, calendar.getCalendarId());
            stmt.setInt(8, project.getProjectId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
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

        PreparedStatement stmt = null;
        try
        {
            stmt = DBconnection.prepareStatement("INSERT INTO Projects(id, name) VALUES (?,?)");
            stmt.setInt(1, project.getProjectId());
            stmt.setString(2, project.getName());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
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

    @Override
    public void addCalendar(Calendar calendar) throws ConnectionError
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = DBconnection.prepareStatement("INSERT INTO Calendars(id, name) VALUES (?,?)");
            stmt.setInt(1, calendar.getCalendarId());
            stmt.setString(2, calendar.getName());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void removeCalendar(Calendar calendar) throws ConnectionError
    {

    }

    private void prepareDatabase()
    {
        runScript("prepare_database.sql");
        runScript("GitObjects.sql");
    }

    private void runScript(String URL) {
        try
        {
            URL url = getClass().getResource(URL);
            RunScript.execute(DBconnection, new FileReader(url.getPath()));
        }
        catch (SQLException e)
        {
            LOGGER.warning("Error in " + URL + " script, cannot execute it!");
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            LOGGER.warning("' " + URL + "' file not found!");
            e.printStackTrace();
        }
    }

    public void testDatabase()
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = DBconnection.prepareStatement("INSERT INTO Projects(id, name) VALUES (?,?)");
            stmt.setInt(1, 32);
            stmt.setString(2, "Project1");
            stmt.executeUpdate();

            stmt = DBconnection.prepareStatement("INSERT INTO Calendars(id, name) VALUES (?,?)");
            stmt.setInt(1, 23);
            stmt.setString(2, "Calendar1");
            stmt.executeUpdate();

            stmt = DBconnection.prepareStatement("INSERT INTO Tasks(id, title, description, priority, label, due, project_id, calendar_id) " +
                    "VALUES (?,?,?,?,?,?,?,?);");
            stmt.setInt(1, 1);
            stmt.setString(2, "Dishes");
            stmt.setString(3, "Do the dishes");
            stmt.setInt(4, 2);
            stmt.setString(5, "Label1");
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(7, 32);
            stmt.setInt(8, 23);
            stmt.executeUpdate();

            stmt = DBconnection.prepareStatement("INSERT INTO Tasks(id, title, description, priority, label, due, project_id, calendar_id) " +
                    "VALUES (?,?,?,?,?,?,?,?);");

            stmt.setInt(1, 2);
            stmt.setString(2, "Program");
            stmt.setString(3, "Java");
            stmt.setInt(4, 1);
            stmt.setString(5, "Label2");
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(7, 32);
            stmt.setInt(8, 23);
            stmt.executeUpdate();

            stmt = DBconnection.prepareStatement("SELECT * FROM Tasks");
            ResultSet set = stmt.executeQuery();

            while (set.next())
            {
                System.out.println("Task id = " + set.getInt(1));
                System.out.println("Title = " + set.getString(2));
                System.out.println("Description = " + set.getString(3));
                System.out.println("Priority = " + set.getInt(4));
                System.out.println("Label = " + set.getString(5));
                System.out.println("Date = " + set.getTimestamp(6).toLocalDateTime());
                System.out.println("Project id = " + set.getInt(7));
                System.out.println("Calendar id = " + set.getInt(8));
                System.out.println("-----------------------------------");
            }
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while testing the database!");
            e.printStackTrace();
        }
    }
}
