package model.GitObjects;

/**
 * superclass for GitObjects
 */
abstract class BaseIssue {

    private int id; //todo: get from DB.
    private String name;
    private String description;
    short DBMaxDescriptionlength = 500; //todo: get from db

    public BaseIssue(String name, String description) {
        if (description.length() > DBMaxDescriptionlength)
            throw new IllegalArgumentException("description is to long currently: " + description.length() + " characters. But needs to be under " + DBMaxDescriptionlength);
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public short getDBMaxDescriptionlength() {
        return DBMaxDescriptionlength;
    }
    public void setDBMaxDescriptionlength(short DBMaxDescriptionlength) {
        this.DBMaxDescriptionlength = DBMaxDescriptionlength; //todo: make call to DB directly from this method?
    }
}