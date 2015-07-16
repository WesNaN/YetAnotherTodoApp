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
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Tests for GitObjects
 * Tests for adding into DB
 */
public class RepositoryMilestoneIssueDBTest {

    Connection DBconnection;
    DatabaseService databaseService;
    Repository repository;

    /**
     * @BeforeClass needs methods to be static. we cant oblige
     * the if statement works around this fact
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        if (DBconnection == null || !DBconnection.isClosed()) {
            DBconnection = DatabaseConnectionFactory.getConnection("test", "sa", "sa");
            databaseService = new DatabaseService(DBconnection);
            repository = new Repository("name", "description", Color.ALICEBLUE, LocalDate.now(), LocalDate.now().plusDays(3));
            repository = databaseService.addRepository(repository);
        }
    }

    /**
     * @AfterClass notation needs all methods to be static. We cant oblige to this
     * To include this method into the last test is a clunky workaround
     * IMPORTANT have last test call this method
     * @throws SQLException
     */
    private void tearDown() throws SQLException {
        databaseService.wipeAndResetDB();
        DBconnection.close();
    }

    /**
     * Method for checking if Objects can be created with no problem
     */
    @Test
    public void createObjects() {
        assertNotNull(repository);
        Issue issue = new Issue(repository.getId(), "name", "description");
        assertNotNull(issue);
        Issue issue1 = new Issue(repository.getId(), "name", "description");
        Milestone milestone = new Milestone(repository, "name", "description", LocalDate.now().plusDays(3), issue, issue1);
        assertEquals(2, milestone.getAssignedIssues().size());
    }

    /**
     * Test if exception is thrown if milestone end date is after repository end date
     */
    @Test(expected=IllegalArgumentException.class)
    public void testMilstoneDateValidation() {
        Issue issue1 = new Issue(repository.getId(), "name", "description");
        Milestone milestone = new Milestone(repository, "name", "description", LocalDate.now().plusDays(13), issue1);
    }

    /**
     * Checking if adding\retrieving\Generating id from DB works as expected
     */
    @Test
    public void addingRepositoryToDB() {
        assertNotEquals(0, repository.getId());
        assertEquals(Color.ALICEBLUE, repository.getColor());
    }
    /**
     * adding a owner that has not been given ID from DB will cast exception
     */
    @Test(expected = IllegalStateException.class)
    public void addUninitializedOwner() {
        Repository repository = new Repository("name", "description", Color.ALICEBLUE, LocalDate.now(), LocalDate.now().plusDays(3));
        Issue issue1 = new Issue(repository.getId(), "name", "description");
    }

    @Test
    public void addingIssueToDB() {
        Issue issue = new Issue(repository.getId(), "name", "description", (byte)1, (short)20);
        Issue issue1 = new Issue(repository.getId(),"name", "description");

        int defaultid = issue.getId();
        issue = databaseService.addIssue(issue);
        issue1 = databaseService.addIssue(issue1);

        assertNotEquals(defaultid, issue.getId());
        assertNotEquals(defaultid, issue1.getId());

        //Method to cleanup DB, see javadoc for method
        try {
            tearDown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
