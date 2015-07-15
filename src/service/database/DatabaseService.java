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

    public DatabaseService(Connection connection) throws ConnectionError
    {
        this.DBconnection = connection;
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
            LOGGER.warning("SQLError while adding a Task to database!");
            e.printStackTrace();
        }
    }

    @Override
    public void removeTask(Task task) throws ConnectionError
    {
        PreparedStatement stmt = null;

        try
        {
            stmt = DBconnection.prepareStatement("DELETE FROM Tasks WHERE id = ?");
            stmt.setInt(1, task.getTaskId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while adding user with id = " + task.getTaskId());
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(Task task, Calendar calendar, Project project) throws ConnectionError
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = DBconnection.prepareStatement("UPDATE Tasks " +
                    "SET id = ?, title = ?, description = ?, priority = ?, label = ?, due = ?, project_id = ?, calendar_id = ?" +
                    "WHERE id = ?");
//            stmt.setInt(1, 1);
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getLabel().getName());
            stmt.setTimestamp(5, Timestamp.valueOf(task.getDue()));
            stmt.setInt(6, calendar.getCalendarId());
            stmt.setInt(7, project.getProjectId());
            stmt.setInt(8, task.getTaskId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while updating a task with id = " + task.getTaskId() + "and title = " + task.getTaskId());
            e.printStackTrace();
        }
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
        PreparedStatement stmt = null;

        try
        {
            stmt = DBconnection.prepareStatement("DELETE FROM Projects WHERE id = ?");
            stmt.setInt(1, project.getProjectId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while removing Project with id = " + project.getProjectId() + "and name = " + project.getName());
            e.printStackTrace();
        }
    }

    @Override
    public void addLabel(Task task, Label label) throws ConnectionError
    {
        task.setLabel(label);

        PreparedStatement stmt = null;

        try
        {
            stmt = DBconnection.prepareStatement("UPDATE Tasks SET label = ? WHERE id = ?");
            stmt.setString(1, label.getName());
            stmt.setInt(2, task.getTaskId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while adding label: " + label.getName() + " to task with id = " + task.getTaskId() + " and title = " + task.getTitle());
            e.printStackTrace();
        }

    }

    @Override
    public void removeLabel(Task task, Label label) throws ConnectionError
    {
        if (task.getLabel() != null)
        {
            task.setLabel(null);
        }

        PreparedStatement stmt = null;

        try
        {
            stmt = DBconnection.prepareStatement("UPDATE Tasks SET label = ? WHERE id = ?");
            stmt.setNull(1, Types.VARCHAR);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while removing labal: " + label.getName() + " from task with id = "
                    + task.getTaskId() + " and name = " + task.getTitle());
            e.printStackTrace();
        }
    }

    /**
     * function to insert new Calendarobject into DB.
     * Will also fetch and set generated ID
     * @param calendar
     */
    @Override
    public void addCalendar(Calendar calendar)
    {
        PreparedStatement stmt = null;
        try
        {
            String sql = "INSERT INTO Calendars(name, color) VALUES (?,?)";
            stmt = DBconnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, calendar.getName());
            stmt.setString(2, calendar.getColor().toString());
            stmt.executeUpdate();

            calendar.setID(findAutoNumber(stmt));
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while adding calendar with id = " + calendar.getCalendarId() + " and name = " + calendar.getName());
            e.printStackTrace();
        }
    }

    /**
     * function to insert a Task into DB
     * @param task
     */
    public void addTask(Task task) {

    }

    @Override
    public void removeCalendar(Calendar calendar) throws ConnectionError
    {
        PreparedStatement stmt = null;

        try
        {
            stmt = DBconnection.prepareStatement("DELETE FROM Calendars WHERE id = ?");
            stmt.setInt(1, calendar.getCalendarId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while removing a calendar with id = " + calendar.getCalendarId() + " and name " + calendar.getName());
            e.printStackTrace();
        }
    }

    private int findAutoNumber(Statement statement) throws SQLException {
        ResultSet result = statement.getGeneratedKeys();
        result.next();
        return result.getInt(1);
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
}
