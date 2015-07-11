package model;

import javafx.scene.layout.Priority;

import java.util.Date;

/**
 *  This class is a representation of a real word "task" entity.
 */

public class Task
{
    /*
        Need to look more into the date time properities. Since Date was deprecated
        it would be good to use some of the Java 1.8 functions that were added. I'll look
        more into it. For now I provided some basic functions.
     */
    private long id = 0;
    private String name;
    private String content;
    private Label label;
    private Date reminder;
    private Date due_at;
    private Date created_at;
    private Date updated_at;
    private Priority priority;
    /* All variables are defined in the Constructor.
       All variables must be defined however if there is no
       due date we can set a value of null. This can
       denote no due date and is easy to check.
     */
    public Task( String name, String content,Label label,Date reminder, Date due_at,Date created_at, Date updated_at,Priority priority){
        id++;
        this.name = name;
        this.content = content;
        this.label = label;
        this.reminder = reminder;
        this.due_at = due_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.priority = priority;
    }
    //Helper function to get id.
    private long getId(){
        return id;
    }

    /*
        Basic class to set Task title;
     */
    private void setName(String name){
        this.name = name;
    }

    /*
        Basic class to set Task content;
     */
    private void setContent(String content){
        this.content = content;
    }
}


