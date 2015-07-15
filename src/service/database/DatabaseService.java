package service.database;

import javafx.scene.paint.Color;
import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;
import model.GitObjects.Repository;
import model.Label;
import model.Project;
import org.h2.tools.RunScript;
import service.ConnectionError;
import service.DataService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class
 */
public class DatabaseService implements DataService
{
    Connection DBConnection = null;
    private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    public DatabaseService(Connection DBConnection) throws ConnectionError
    {
        this.DBConnection = DBConnection;
        prepareDatabase(); //todo: only if database is not present or new user\pass
    }

    @Override
    public void addTask(Task task, Calendar calendar) throws ConnectionError
    {
        PreparedStatement stmt = null;

        try
        {
            stmt = DBConnection.prepareStatement("INSERT INTO Tasks(id, title, description, location, label, due, lastedit, priority, calendar_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?);");

            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getLocation());
            stmt.setString(5, task.getLabel().getName());
            stmt.setTimestamp(6, Timestamp.valueOf(task.getDue()));
            stmt.setTimestamp(7, Timestamp.valueOf(task.getlastEdit()));
            stmt.setInt(8, task.getPriority());
            stmt.setInt(9, calendar.getCalendarId());
            stmt.executeUpdate();

            task.setTaskId(findAutoNumber(stmt));

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
            stmt = DBConnection.prepareStatement("DELETE FROM Tasks WHERE id = ?");
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
    public void updateTask(Task task, Calendar calendar) throws ConnectionError
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = DBConnection.prepareStatement("UPDATE Tasks " +
                    "SET title = ?, description = ?, location = ?, label = ?, due = ?, lastedit = ?,priority = ?, calendar_id = ?" +
                    "WHERE id = ?");

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getLocation());
            stmt.setString(4, task.getLabel().getName());
            stmt.setTimestamp(5, Timestamp.valueOf(task.getDue()));
            stmt.setTimestamp(6, Timestamp.valueOf(task.getlastEdit()));
            stmt.setInt(7, task.getPriority());
            stmt.setInt(8, calendar.getCalendarId());
            stmt.setInt(9, task.getTaskId());
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
            stmt = DBConnection.prepareStatement("INSERT INTO Projects(id, name) VALUES (?,?)");
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
            stmt = DBConnection.prepareStatement("DELETE FROM Projects WHERE id = ?");
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
        if (task.getLabel() != null)
        {
            LOGGER.warning("Task with title = " + task.getTitle() + " and id = " + task.getTaskId() + " already have a label!");
            return;
        }

        task.setLabel(label);

        PreparedStatement stmt = null;

        try
        {
            stmt = DBConnection.prepareStatement("UPDATE Tasks SET label = ? WHERE id = ?");
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
    public void removeLabel(Task task) throws ConnectionError
    {
        if (task.getLabel() != null)
        {
            task.setLabel(null);
        }

        PreparedStatement stmt = null;

        try
        {
            stmt = DBConnection.prepareStatement("UPDATE Tasks SET label = ? WHERE id = ?");
            stmt.setNull(1, Types.VARCHAR);
            stmt.setInt(2, task.getTaskId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while removing label from task with id = "
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
            stmt = DBConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            stmt = DBConnection.prepareStatement("DELETE FROM Tasks WHERE calendar_id = ?");
            stmt.setInt(1, calendar.getCalendarId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while removing tasks that belong to calendar with id = " + calendar.getCalendarId());
            e.printStackTrace();
        }

        try
        {
            stmt = DBConnection.prepareStatement("DELETE FROM Calendars WHERE id = ?");
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
            RunScript.execute(DBConnection, new FileReader(url.getPath()));
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

    /**
     * This method will wipe all data from DB
     */
    public  void wipeAndResetDB() {
        runScript("DropTables.sql");
        prepareDatabase();
    }

    private boolean isDatabasePresent()
    {
        PreparedStatement stmt = null;

        try
        {
            ResultSet rset = DBConnection.getMetaData().getTables(null, null, "", null);
            // TODO check if tables are present
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Repository addRepository(Repository repository) {
        try {
            String sql = "INSERT INTO repository(name, description, color, start, end) VALUES (?,?,?,?,?)";
            PreparedStatement statement = DBConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, repository.getName());
            statement.setString(2, repository.getDescription());
            statement.setString(3, repository.getColor().toString());
            statement.setDate(4, Date.valueOf(repository.getProjectStart()));
            if (repository.getProjectEnd() == null)
                statement.setDate(5, null);
            else
                statement.setDate(5, Date.valueOf(repository.getProjectEnd()));
            statement.execute();

            repository = findRepository(findAutoNumber(statement));
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "could not insert repository into DB");
        }
        return repository;
    }

    public Repository findRepository(int id) {
        Repository repository = null;
        try {
            String sql = "SELECT * FROM repository WHERE id = " + id;
            Statement statement = DBConnection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();

            int resultId = result.getInt("id");
            String name = result.getString("name");
            String description= result.getString("description");
            String colorString = result.getString("color");
            Color color = Color.web(colorString);
            LocalDate projectStart = result.getDate("start").toLocalDate();
            LocalDate projectEnd = result.getDate("end").toLocalDate(); //todo: if null then ?

           repository = new Repository(resultId, name, description, color, projectStart, projectEnd);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "could not get repository from DB");
        }
        return repository;
    }
}
