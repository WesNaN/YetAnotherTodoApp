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

        try {
            tearDown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
