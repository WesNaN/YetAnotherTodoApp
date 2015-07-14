package service;

import model.CalendarObjects.Calendar;
import model.Label;
import model.Project;
import model.CalendarObjects.Task;

public interface DataService
{
     /**
      * This interface provides methods for saving app data.
      */

     /**
      * @param task is an instance of {@link model.Task}
      * @throws ConnectionError
      */
     void addTask(Task task, Calendar calendar, Project project) throws ConnectionError;

     /**
      *
      * @param task is an instance of {@link model.Task}
      * @throws ConnectionError
      */
     void removeTask(Task task) throws ConnectionError;

     /**
      *
      * This method updates fields of given {@link model.Task} without deleting current object
      *
      * @param task is an instance of {@link model.Task}
      * @throws ConnectionError
      */
     void updateTask(Task task, Calendar calendar, Project project) throws ConnectionError;

     /**
      *
      * @param project is an instance of {@link model.Project}
      * @throws ConnectionError
      */
     void addProject(Project project) throws ConnectionError;

     /**
      *
      * @param project is an instance of {@link model.Project}
      * @throws ConnectionError
      */
     void removeProject(Project project) throws ConnectionError;

     /**
      *
      * @param label is an instance of {@link model.Label}
      * @throws ConnectionError
      */
     void addLabel(Task task, Label label) throws ConnectionError;

     /**
      *
      * @param label is an instance of {@link model.Label}
      * @throws ConnectionError
      */
     void removeLabel(Task task, Label label) throws ConnectionError;

     /**
      *
      * @param calendar
      * @throws ConnectionError
      */
     void addCalendar(Calendar calendar) throws ConnectionError;

     /**
      *
      * @param calendar
      * @throws ConnectionError
      */
     void removeCalendar(Calendar calendar) throws ConnectionError;

}
