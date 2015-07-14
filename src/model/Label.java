package model;

/**
 *  This class should be used as a context to given {@link model.Task}. <br>
 *  It proposes different manner of joining("labeling"). {@link model.Task}
 */

public class Label
{
    String name;

    public String getName()
    {
        return name;
    }

    public Label(String name)
    {
        this.name = name;
    }
}
