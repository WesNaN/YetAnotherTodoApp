package test;

import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;
import model.Label;
import model.Project;
import service.ConnectionError;
import service.database.DatabaseConnectionFactory;
import service.database.DatabaseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceTest
{
    public static void main(String args[]) throws ConnectionError
    {
        DatabaseService service = new DatabaseService(DatabaseConnectionFactory.getConnection("test", "sa", "sa"));


        List<LocalDateTime> reminders = new ArrayList<>();
        reminders.add(LocalDateTime.now().plusDays(1));
        reminders.add(LocalDateTime.now().plusDays(2));

        Task task1 = new Task("Title1", "Description1", "Location1", new Label("Label1"), reminders, LocalDateTime.now().plusDays(3), 1, 312);
        Task task2 = new Task("Title2", "Description2", "Location2", new Label("Label2"), reminders, LocalDateTime.now().plusDays(4), 2, 313);


        Calendar calendar = new Calendar("Calendar");
        Project project = new Project();

        service.addCalendar(calendar);
        service.addProject(project);

        service.addTask(task1, calendar);
        service.addTask(task2, calendar);

        task1.setLabel(new Label("new Label"));

        service.updateTask(task1, calendar);

        service.removeLabel(task1);
        service.addLabel(task1, new Label("Label3"));


        service.removeCalendar(calendar);




    }
}
