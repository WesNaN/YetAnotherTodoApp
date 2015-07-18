package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Grouping all testClasses together here. add testclasses to the @Suite.SuiteClasses array
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalendarTaskDatabaseTest.class,
        RepositoryMilestoneIssueDBTest.class,
        RepositoryTest.class })
public class AllTests {

}