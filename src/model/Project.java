package model;

import java.util.ArrayList;

/**
 *  This class creates collection gather under a {@link #name}
 */

public class Project
{
    /**
     *  This String is a name of a project
     */
    String name;

    /**
     *  This {@link java.util.ArrayList} stores tasks in this project
     */
    ArrayList<Task> tasks = new ArrayList<>();
}
