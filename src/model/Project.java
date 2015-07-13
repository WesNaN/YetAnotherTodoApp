package model;

import java.util.ArrayList;

/**
 *  This class creates collection gather under a {@link #name}
 */

public class Project
{
    private static int id = 0;
    private String name;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Project()
    {
        id++;
    }
}
