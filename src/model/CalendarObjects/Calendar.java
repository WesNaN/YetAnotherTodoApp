package model.CalendarObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Calendar Object
 * This holds a group of tasks
 */
public class Calendar {
    private static int id = 0;
    private int calendarId;
    private String name;
    private String ColorCode; //chosen colorcode for this calendar
    private List<Task> tasks = new ArrayList<>();

    public Calendar()
    {
        calendarId = id++;
    }

    public String getName()
    {
        return name;
    }

    public String getColorCode()
    {
        return ColorCode;
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