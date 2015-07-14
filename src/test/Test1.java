package test;

import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;
import model.Label;
import model.Project;
import service.ConnectionError;
import service.database.DatabaseService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test1
{
    public static void main(String args[]) throws ConnectionError
    {
        System.out.println("Test");

        DatabaseService service = new DatabaseService();


        ArrayList<LocalDateTime> reminders = new ArrayList<>();
        reminders.add(LocalDateTime.now().plusDays(1));
        reminders.add(LocalDateTime.now().plusDays(1).plusHours(2));
        LocalDateTime due = LocalDateTime.now().plusDays(2);

        Calendar calendar1 = new Calendar();
        Project project1 = new Project();

        Task task1 = new Task("Program", "Program to hours in Java", "Warsaw", new Label("Some label"), reminders, due, 1);
        Task task2 = new Task("Tractor", "Buy a tractor", "Siedlce", new Label("Some label too"), reminders, due, 3);

        service.addCalendar(calendar1);
        service.addProject(project1);

        //service.addTask(task1, calendar1, project1); //todo: this crashes
        //service.addTask(task2, calendar1, project1);

    }
}