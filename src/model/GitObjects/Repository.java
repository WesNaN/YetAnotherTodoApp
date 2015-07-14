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

    public Repository(String name, String description, Color color, LocalDate projectStart, LocalDate projectEnd) {
        super(name, description);
        this.color = color;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
    }
}