package model;

import java.util.ArrayList;

@Deprecated //by Repository
public class Project
{
    private static int id = 1;
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
