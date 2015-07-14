package model.CalendarObjects;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Calendar Object
 * This holds a group of tasks
 */
public final class Calendar {

    private static int id = 0;
    private int calendarId;
    private String name;
    private Color color; //chosen colorcode for this calendar
    private List<Task> tasks = new ArrayList<>();

    public Calendar()
    {
        calendarId = id++;
    }

    public String getName()
    {
        return name;
    }

    public Color getColor()
    {
        return color;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }

    public int getCalendarId()
    {
        return calendarId;
    }
}