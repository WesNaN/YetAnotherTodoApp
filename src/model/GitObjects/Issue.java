package model.GitObjects;

/**
 * this is an issue. a problem that needs to be taken care of
 */
public final class Issue extends BaseIssue {

    private final int ownerId;
    private byte priority;
    private short difficulty; //how much work is required to svolve issue
    private boolean finished;

    public Issue(int ownerId, String name, String description, byte priority, short difficulty) {
        super(name, description);
        if (ownerId < 1)
            throw new IllegalStateException("OwnerId neds to be larger than 0. Do the repository have a id from DB?");
        this.ownerId = ownerId;
        Issue(priority, difficulty, false);
    }

    /**
     * Constructor when fetching object from DB
     * @param id
     * @param ownerId
     * @param name
     * @param description
     * @param priority
     * @param difficulty
     * @param finished
     */
    public Issue(int id, int ownerId, String name, String description, byte priority, short difficulty, boolean finished) {
        super(id, name, description);
        if (ownerId < 1)
            throw new IllegalStateException("OwnerId neds to be larger than 0. Do the repository have a id from DB?");
        this.ownerId = ownerId;
        Issue(priority, difficulty, finished);
    }

    public Issue(int ownerId, String name, String description) {
        this(ownerId, name, description, (byte)5, (short)50);
    }

    /**
     * Private Constructor Helper
     * @param priority
     * @param difficulty
     * @param finished
     */
    private void Issue( byte priority, short difficulty, boolean finished ) {
        this.priority = priority;
        this.difficulty = difficulty;
        this.finished = finished;
    }

    public int getOwnerId() {
        return ownerId;
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