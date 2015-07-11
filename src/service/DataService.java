package service;

import model.Label;
import model.Project;
import model.Task;

public interface DataService
{
     /**
      * This interface provides methods for saving app data.
      */

     /**
      * @param task is an instance of {@link model.Task}
      * @throws ConnectionError
      */
     void addTask(Task task) throws ConnectionError;

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
     void updateTask(Task task) throws ConnectionError;

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
     void addLabel(Label label) throws ConnectionError;
     /**
      *
      * @param label is an instance of {@link model.Label}
      * @throws ConnectionError
      */
     void removeLabel(Label label) throws ConnectionError;
}
