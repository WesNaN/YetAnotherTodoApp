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

public class DatabaseService implements DataService
{
    Connection connection = null;
    private static final Logger LOGGER = Logger.getLogger(
            Thread.currentThread().getStackTrace()[0].getClassName());

    public DatabaseService(Connection connection) throws ConnectionError
    {
        this.connection = connection;
        prepareDatabase(); //todo: only if database is not present or new user\pass
    }


    @Override
    public void addTask(Task task, Calendar calendar, Project project) throws ConnectionError
    {
        PreparedStatement stmt = null;

        try
        {
            stmt = connection.prepareStatement("INSERT INTO Tasks(id, title, description, priority, label, due, project_id, calendar_id) " +
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
            stmt = connection.prepareStatement("DELETE FROM Tasks WHERE id = ?");
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
            stmt = connection.prepareStatement("UPDATE Tasks " +
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
            stmt = connection.prepareStatement("INSERT INTO Projects(id, name) VALUES (?,?)");
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
            stmt = connection.prepareStatement("DELETE FROM Projects WHERE id = ?");
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
            stmt = connection.prepareStatement("UPDATE Tasks SET label = ? WHERE id = ?");
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
            stmt = connection.prepareStatement("UPDATE Tasks SET label = ? WHERE id = ?");
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

    @Override
    public void addCalendar(Calendar calendar) throws ConnectionError
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("INSERT INTO Calendars(id, name) VALUES (?,?)");
            stmt.setInt(1, calendar.getCalendarId());
            stmt.setString(2, calendar.getName());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while adding calendar with id = " + calendar.getCalendarId() + " and name = " + calendar.getName());
            e.printStackTrace();
        }
    }

    @Override
    public void removeCalendar(Calendar calendar) throws ConnectionError
    {
        PreparedStatement stmt = null;

        try
        {
            stmt = connection.prepareStatement("DELETE FROM Calendars WHERE id = ?");
            stmt.setInt(1, calendar.getCalendarId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.warning("SQLError while removing a calendar with id = " + calendar.getCalendarId() + " and name " + calendar.getName());
            e.printStackTrace();
        }
    }

    private void prepareDatabase()
    {
        try
        {
            URL url = getClass().getResource("prepare_database.sql");
            RunScript.execute(connection, new FileReader(url.getPath()));
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
}
