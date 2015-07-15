package model.CalendarObjects;

import model.Label;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Task Object describes when, what and where a event is
 */
public final class Task { //todo change name to event?

    private int taskId = 0; //todo: get from DB
    private String title;
    private String description;
    private String location; //maybe coordinates from google maps?
    private Label label;
    private List<LocalDateTime> reminders; //make this a separate object x min before due? List for more than one reminders?
    private LocalDateTime due;
    private LocalDateTime lastEdit;
    private int priority;
    private final int ownerId; //id to the calendar that owns this task

    /* All variables are defined in the Constructor.
       All variables must be defined however if there is no
       due date we can set a value of null. This can
       denote no due date and is easy to check.
     */

    public Task(String title, String description, String location, Label label, List<LocalDateTime> reminders, LocalDateTime due, int priority, int ownerId) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.label = label;
        this.reminders = reminders;
        this.due = due;
        lastEdit = LocalDateTime.now();
        this.priority = priority;
        this.ownerId = ownerId;
    }

    /**
     * Getters
     * @return
     */
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
    public LocalDateTime getlastEdit() {
        return lastEdit;
    }
    public int getTaskId()
    {
        return taskId;
    }
    public int getOwnerid() {
        return ownerId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setLabel(Label label)
    {
        this.label = label;
    }
    public void setReminders(List<LocalDateTime> reminders)
    {
        this.reminders = reminders;
    }
    public void setLastEdit(LocalDateTime lastEdit)
    {
        this.lastEdit = lastEdit;
    }
    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    /**
     * returns true if duedate has Passed
     * @return
     */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(due);
    }
}
