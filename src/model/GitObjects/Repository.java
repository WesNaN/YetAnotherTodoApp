package model.GitObjects;

import javafx.scene.paint.Color;

import java.time.LocalDate;

/**
 * This is the class describing a project.
 * W'ell try to use git terms, thereby the naming
 *
 * A repo has issues that can be grouped into Milestones. Milestones have a due date.
 */
public final class Repository extends BaseIssue {

    private Color color; //colorcode when shown in gui
    private LocalDate projectStart;
    private LocalDate projectEnd;

    /**
     * this constructor is for new objects that dont have their id given by DB yet
     * @param name
     * @param description
     * @param color
     * @param projectStart
     * @param projectEnd
     */
    public Repository(String name, String description, Color color, LocalDate projectStart, LocalDate projectEnd) {
        super(name, description);
        this.color = color;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
    }

    /**
     * This constructor is used when fetching object from DB
     * @param id
     * @param name
     * @param description
     * @param color
     * @param projectStart
     * @param projectEnd
     */
    public Repository(int id, String name, String description, Color color, LocalDate projectStart, LocalDate projectEnd) {
        super(id, name, description);
        this.color = color;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
    }

    public Color getColor() {
        return color;
    }
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * we should not change startdate after we create object
     * therefore no setProjectStart method provided
     * @return
     */
    public LocalDate getProjectStart() {
        return projectStart;
    }
    public LocalDate getProjectEnd() {
        return projectEnd;
    }
    public void setProjectEnd(LocalDate projectEnd) {
        this.projectEnd = projectEnd;
    }
}