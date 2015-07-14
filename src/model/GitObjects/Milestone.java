package model.GitObjects;

import java.time.LocalDate;
import java.util.List;

/**
 * This Describes milestones. milestones is a collection of issues that have a target date,
 * for when they should be done.
 */
public final class Milestone extends BaseIssue {

    private LocalDate plannedFinnish;
    private List<Issue> assignedIssues; //todo: get from DB?

    public Milestone(String name, String description, LocalDate plannedFinnish, List<Issue> assignedIssues) {
        super(name, description);
        this.plannedFinnish = plannedFinnish;
        this.assignedIssues = assignedIssues;
    }

    public byte percentDone() {
        long doneIssues = assignedIssues.stream()
                                        .map(Issue::isFinished)
                                        .count();
        return (byte) (assignedIssues.size() / doneIssues); //should never be more than 100 -> byte todo:doublecheck math
    }
}