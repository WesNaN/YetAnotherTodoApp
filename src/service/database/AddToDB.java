package service.database;

import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;
import model.GitObjects.Issue;
import model.GitObjects.Milestone;
import model.GitObjects.Repository;
import service.MissingDataFromSQLStatement;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that holds the functions for adding objects into DB
 */

public final class AddToDB {
    Connection DBconnection;
    private static final Logger LOGGER = Logger.getLogger( AddToDB.class.getName() );

    AddToDB(Connection DBconnection) {
        this.DBconnection = DBconnection;
    }

    //CalendarObjects Inserts here ----------------------------------------

    /**
     * method for insetring calendar and task into db simultaneously
     * @param calendar
     * @param tasks
     * @throws MissingDataFromSQLStatement
     * @throws IllegalArgumentException
     */
    public void addCalendar(Calendar calendar, Task... tasks) throws MissingDataFromSQLStatement {
        addCalendar(calendar);
        for (Task task:tasks) {
            try {
                addTask(task);
            } catch (Exception e) {
                LOGGER.log(Level.FINER, "could not add task to DB", e);
            }
        }
    }

    /**
     * Method for putting a Calendar Object into DB
     * @param calendar
     * @throws IllegalArgumentException
     */
    public void addCalendar(Calendar calendar) {
        LOGGER.log(Level.FINEST, "addCalendar with id: " + calendar.getCalendarId());

        String sql = "select * from calendar where id is " + calendar.getCalendarId(); //needs to be written correctly

        if(sql == null) //todo if result from sql ...
            throw new IllegalArgumentException("Calendar already exists, try update method instead");

        sql = "insert into calendar"; //todo: write this finished
    }

    /**
     * adds a Task object into the DB
     * @param task
     * @throws MissingDataFromSQLStatement
     */
    public void addTask(Task task) throws MissingDataFromSQLStatement {
        LOGGER.log(Level.FINEST, "addTask with id: " + task.getId());

        int calendarid = task.getOwnerid();
        String sql = "select * from calendar where id like " + calendarid;

        if(sql == null) //todo: this is pseudocode. or other way to handle this
            throw new MissingDataFromSQLStatement("Calendarid " + calendarid + " does not exist in DB");

        //todo: add into DB here
    }

    //GitObjects Inserts here ---------------------------------------------

    public void addRepository(Repository repository, Milestone milestone, Issue... issues) {

    }

    public void addRepository(Repository repository) {
        LOGGER.log(Level.FINEST, "addRepoistory with id: " + repository.getId());

        String sql = "select * from reopsitoruy where id is " + repository.getId();

        if(sql == null) //todo: pseudocode
            throw new IllegalArgumentException("repository already exists, try the update method");

        //todo; add to DB here;
    }

}
