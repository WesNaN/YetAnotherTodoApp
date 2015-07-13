package model;

/**
 *  This enum is representing order of priority in given task.
 *  The higher level means more attention to given task, more notifications etc.
 *
 */

@Deprecated
public enum Priority
{
    URGENT(2), ASAP(3), ANYTIME(0), NOTURGENT(1);

    Priority(int level)
    {
    }
}
