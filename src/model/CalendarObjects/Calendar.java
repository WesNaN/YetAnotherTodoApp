package model.CalendarObjects;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Calendar Object
 * This holds a group of tasks
 */
public final class Calendar {

    private int calendarId = 0;
    private String name;
    private Color color; //chosen colorcode for this calendar //todo: generate a color thats not in usesomewhere
    private List<Task> tasks = new ArrayList<>();

    public Calendar(String name, Color color) {
        this.name = name;
        this.color = color;
        //TODO: Decide if we want to insert intoDB here or not (DB generates id)
    }
    public Calendar(String name) {
        this(name, Color.AQUA);
    }

    public void setID(int id) {
        if (calendarId != 0)
            throw new IllegalAccessError("you can only set id for a calendar once!");
        calendarId = id;
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