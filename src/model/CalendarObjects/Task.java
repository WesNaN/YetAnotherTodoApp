package model.CalendarObjects;

import model.Label;
import model.Priority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Task Object
 */
public class Task {

    private final int id;
    private static int idCounter =0;
    private String title;
    private String description;
    private String location; //maybe coordinates from google maps?
    private String attendants;
    private Label label;
    LocalDateTime reminder; //make this a separate object x min before due? List for more than one reminder?
    LocalDateTime due;
    List<LocalDateTime> LastEdit = new ArrayList<>(); //first = created
    private Priority priority;

    /* All variables are defined in the Constructor.
       All variables must be defined however if there is no
       due date we can set a value of null. This can
       denote no due date and is easy to check.
     */

    public Task(String title, String description, String location, String attendants, Label label, LocalDateTime reminder, LocalDateTime due, Priority priority) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.attendants = attendants;
        this.label = label;
        this.reminder = reminder;
        this.due = due;
        LastEdit.add(LocalDateTime.now());
        this.priority = priority;
        this.id = idCounter++; //get id from DB?
        updateOrAddInDB();
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAttendants() {
        return attendants;
    }
    public Label getLabel() {
        return label;
    }
    public LocalDateTime getReminder() {
        return reminder;
    }
    public LocalDateTime getDue() {
        return due;
    }
    public void setDue(LocalDateTime due) {
        this.due = due;
    }
    public List<LocalDateTime> getLastEdits() {
        return LastEdit;
    }
    public Priority getPriority() {
        return priority;
    }

    /**
     * Method for updating Task
     * Maybe not needed when DB is up and running?
     * @param title
     * @param description
     * @param location
     * @param attendants
     * @param label
     * @param reminder
     * @param due
     * @param priority
     */
    public void updateTask(String title, String description, String location, String attendants, Label label, LocalDateTime reminder, LocalDateTime due, Priority priority) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.attendants = attendants;
        this.label = label;
        this.reminder = reminder;
        this.due = due;
        this.priority = priority;
        LastEdit.add(LocalDateTime.now());

        updateOrAddInDB();
    }

    /**
     * This will update Task in DB
     */
    private void updateOrAddInDB() {

    }

    /**
     * returns true if duedate has Passed
     * @return
     */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(due);
    }
}