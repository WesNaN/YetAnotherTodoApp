package model.GitObjects;

/**
 * this is an issue. a problem that needs to be taken care of
 */
public final class Issue extends BaseIssue {

    private byte priority;
    private short difficulty; //how much work is required to svolve issue
    private boolean finished;

    public Issue(String name, String description, byte priority, short difficulty) {
        super(name, description);
        this.priority = priority;
        this.difficulty = difficulty;
        finished = false;
    }
    public Issue(String name, String description) {
        this(name, description, (byte)5, (short)50);
    }

    public byte getPriority() {
        return priority;
    }
    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public short getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}