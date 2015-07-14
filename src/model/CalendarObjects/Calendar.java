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
    private String name;
    private Color color; //chosen colorcode for this calendar
    private List<Task> tasks = new ArrayList<>();

    public Calendar()
    {
        id++;
    }
}