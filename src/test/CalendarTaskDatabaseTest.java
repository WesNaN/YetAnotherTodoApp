package test;

import javafx.scene.paint.Color;
import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;
import model.Label;
import org.junit.Before;
import org.junit.Test;
import service.database.DatabaseConnectionFactory;
import service.database.DatabaseService;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Unit Tests for Calendar and Tasks.
 * Tests for adding these objects into Database
 */
public class CalendarTaskDatabaseTest {

    Connection DBconnection;
    DatabaseService databaseService;

    @Before
    public void setUp() throws Exception {
        DBconnection = DatabaseConnectionFactory.getConnection("test", "sa", "sa");
        databaseService = new DatabaseService(DBconnection);
    }

    /**
     * Testing that a color gets set when none is given
     */
    @Test
    public void testCalendarColorWhennotassigned() {
        Calendar calendar1 = new Calendar("testwithnocolor");
        assertEquals(calendar1.getColor(), Color.AQUA); //todo: generate a color that is not in use somewhere
    }

    /**
     * testing addCalendar function, checks if function retrieves and sets an id from DB
     */
    @Test
    public void addingCalendar() {
        Calendar calendar = new Calendar("testCaledar", Color.ALICEBLUE);
        databaseService.addCalendar(calendar);
        assertThat(calendar.getCalendarId(), not(0));
    }

    /**
     * Checking if id cannot be set outside of addCalendar method
     * 
     * @throws IllegalAccessError
     */
    @Test(expected = IllegalAccessError.class)
    public void calendarIdwhenalreadyGenerated() throws IllegalAccessError {
        Calendar calendar = new Calendar("testCaledar");
        databaseService.addCalendar(calendar);
        calendar.setID(3);
    }

    @Test
    public void testCalendarAndTask() {
        ArrayList<LocalDateTime> reminders = new ArrayList<>();
        reminders.add(LocalDateTime.now().plusDays(1));
        reminders.add(LocalDateTime.now().plusDays(1).plusHours(2));
        LocalDateTime due = LocalDateTime.now().plusDays(2);

        Calendar calendar = new Calendar("testCaledar", Color.ALICEBLUE);

        Task task1 = new Task("Program", "Program to hours in Java", "Warsaw", new Label("Some label"), reminders, null, 1, calendar.getCalendarId());
        Task task2 = new Task("Tractor", "Buy a tractor", "Siedlce", new Label("Some label too"), reminders, due, 3, calendar.getCalendarId());

        assertEquals(task1.getDue(), null);
        assertEquals(task2.getDue(), due);
        assertEquals(calendar.getColor(), Color.ALICEBLUE);

        databaseService.addTask(task1);
    }



}