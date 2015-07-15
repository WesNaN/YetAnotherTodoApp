package test;

import javafx.scene.paint.Color;
import model.GitObjects.Issue;
import model.GitObjects.Milestone;
import model.GitObjects.Repository;
import org.junit.Before;
import org.junit.Test;
import service.database.DatabaseConnectionFactory;
import service.database.DatabaseService;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for GitObjects
 * Tests for adding into DB
 */
public class RepositoryMilestoneIssueDBTest {

    Connection DBconnection;
    DatabaseService databaseService;

    @Before
    public void setUp() throws Exception {
        DBconnection = DatabaseConnectionFactory.getConnection("test", "sa", "sa");
        databaseService = new DatabaseService(DBconnection);
    }

    /**
     * Method for checking if Objects can be created with no problem
     */
    @Test
    public void createObjects() {
        Repository repository = new Repository("name", "description", Color.ALICEBLUE, LocalDate.now(), LocalDate.now().plusDays(3));
        assertNotNull(repository);
        Issue issue = new Issue("name", "description");
        assertNotNull(issue);
        Issue issue1 = new Issue("name", "description");
        Milestone milestone = new Milestone(repository, "name", "description", LocalDate.now().plusDays(3), issue, issue1);
        assertEquals(2, milestone.getAssignedIssues().size());
    }

    /**
     * Test if exception is thrown if milestone end date is after repository end date
     */
    @Test(expected=IllegalArgumentException.class)
    public void testMilstoneDateValidation() {
        Repository repository = new Repository("name", "description", Color.ALICEBLUE, LocalDate.now(), LocalDate.now().plusDays(3));
        Issue issue1 = new Issue("name", "description");
        Milestone milestone = new Milestone(repository, "name", "description", LocalDate.now().plusDays(13), issue1);
    }

    /**
     * Checking if adding\retrieving\Generating id works as expected
     */
    @Test
    public void addingRepositoryToDB() {
        Repository repository = new Repository("name", "description", Color.ALICEBLUE, LocalDate.now(), LocalDate.now().plusDays(3));
        repository = databaseService.addRepository(repository);
        assertNotEquals(0, repository.getId());
        assertEquals(Color.ALICEBLUE, repository.getColor());
    }
}
