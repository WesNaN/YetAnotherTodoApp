package model;

import java.util.ArrayList;

/**
 *  This class creates some kind of collection gather under a name
 */

public class Project
{
    /**
     *  This String @name is a name of a project
     */
    String name;

    /**
     *  This ArrayList stores task in this project
     */
    ArrayList<Task> tasks = new ArrayList<>();
}
