package model.CalendarObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Calendar Object
 * This holds a group of tasks
 */
public class Calendar {

    private static int id = 0;
    private String name;
    private String ColorCode; //chosen colorcode for this calendar
    private List<Task> tasks = new ArrayList<>();

    public Calendar()
    {
        id++;
    }
}