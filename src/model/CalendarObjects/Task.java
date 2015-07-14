package model.CalendarObjects;

import model.Label;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Task Object
 */
public class Task {

    private static int id = 0;
    private int taskId;
    private String title;
    private String description;
    private String location; //maybe coordinates from google maps?
    private Label label;
    private List<LocalDateTime> reminders; //make this a separate object x min before due? List for more than one reminders?
    private LocalDateTime due;
    private List<LocalDateTime> lastEdit = new ArrayList<>(); //first = created
    private int priority;

    /* All variables are defined in the Constructor.
       All variables must be defined however if there is no
       due date we can set a value of null. This can
       denote no due date and is easy to check.
     */

    public Task(String title, String description, String location, Label label, List<LocalDateTime> reminders, LocalDateTime due, int priority) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.label = label;
        this.reminders = reminders;
        this.due = due;
        lastEdit.add(LocalDateTime.now());
        this.priority = priority;
        taskId = id++;
    }

    /**
     * Getters
     * @return
     */
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getPriority()
    {
        return priority;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Label getLabel() {
        return label;
    }
    public List<LocalDateTime> getReminders() {
        return reminders;
    }
    public LocalDateTime getDue() {
        return due;
    }
    public void setDue(LocalDateTime due) {
        this.due = due;
    }
    public List<LocalDateTime> getlastEdits() {
        return lastEdit;
    }
    public int getTaskId()
    {
        return taskId;
    }

    /**
     * Method for updating Task
     * Maybe not needed when DB is up and running?
     * @param title
     * @param description
     * @param location
     * @param attendants
     * @param label
     * @param reminders
     * @param due
     * @param priority
     */
    public void updateTask(String title, String description, String location, String attendants, Label label, List<LocalDateTime> reminders, LocalDateTime due, int priority) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.label = label;
        this.reminders = reminders;
        this.due = due;
        this.priority = priority;
        lastEdit.add(LocalDateTime.now());
    }

    /**
     * returns true if duedate has Passed
     * @return
     */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(due);
    }
}
