package model;

import java.time.LocalDateTime;
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
    private int id;
    private String title;
    private String content;
    private Date due_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    /* All variables are defined in the Constructor.
       All variables must be defined however if there is no
       due date we can set a value of null. This can
       denote no due date and is easy to check.
     */
    public Task(int id, String title, String content, Date due_at,LocalDateTime created_at, LocalDateTime updated_at){
        this.id = id;
        this.title = title;
        this.content = content;
        this.due_at = due_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    //Helper function to get id.
    private int getId(){
        return id;
    }

    /*
        Basic class to set Task title;
     */
    private void setTitle(String title){
        this.title = title;
    }

    /*
        Basic class to set Task content;
     */
    private void setContent(String content){
        this.content = content;
    }
}


