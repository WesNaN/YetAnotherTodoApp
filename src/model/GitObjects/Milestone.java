package model.GitObjects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Describes milestones. milestones is a collection of issues that have a target date,
 * for when they should be done.
 */
public final class Milestone extends BaseIssue {

    private int repositoryid;
    private LocalDate plannedFinnish;
    private List<Integer> assignedIssues = new ArrayList<>(); //todo: get from DB?

    public Milestone(Repository owner, String name, String description, LocalDate plannedFinnish, Issue... assignedIssues) {
        super(name, description);

        if (owner.getId() < 1)
            throw new IllegalStateException("OwnerId neds to be larger than 0. Do the repository have a id from DB?");
        repositoryid = owner.getId();

        if (owner.getProjectEnd() == null || plannedFinnish.isAfter(owner.getProjectEnd()))
            throw new IllegalArgumentException("Projects enddate have to be after milestones Enddate");
        this.plannedFinnish = plannedFinnish;
        addIssue(assignedIssues);
    }

    public LocalDate getPlannedFinnish() {
        return plannedFinnish;
    }
    public void setPlannedFinnish(LocalDate plannedFinnish) {
        this.plannedFinnish = plannedFinnish;
    }

    /**
     * adds any number of issues to this milestone.
     * discard duplicates
     * @param issues
     * @return
     */
    public void addIssue(Issue... issues) {
         Arrays.stream(issues)
                .map(BaseIssue::getId)
                .forEach(this.assignedIssues::add);
        discardDuplicates();

        //todo: needs to add to DB as well
    }
    public List<Integer> getAssignedIssues() {
        return assignedIssues;
    }

    public byte percentDone() {
        //todo: if needed, fetch from DB
        /*long doneIssues = assignedIssues.stream()
                                        .map(Issue::isFinished)
                                        .count();
        return (byte) (assignedIssues.size() / doneIssues); //should never be more than 100 -> byte todo:doublecheck math
*/      throw new NotImplementedException();
    }

    /**
     * dicards duplicate ids from issues
     */
    private void discardDuplicates() {
        assignedIssues.stream().distinct().collect(Collectors.toList());
    }
}