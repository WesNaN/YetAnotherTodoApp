package model;

import java.util.ArrayList;

public class Project
{
    private static int id = 0;
    private int projectId;
    private String name;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Project()
    {
        projectId = id++;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public int getProjectId()
    {
        return projectId;
    }
}
