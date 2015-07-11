package model;

import java.util.ArrayList;

/**
 *  This class creates collection gather under a @name
 */

public class Project
{
    /**
     *  This String @name is a name of a project
     */
    String name;

    /**
     *  This ArrayList stores tasks in this project
     */
    ArrayList<Task> tasks = new ArrayList<>();
}
