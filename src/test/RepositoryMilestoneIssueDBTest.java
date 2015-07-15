package test;

import org.junit.Before;
import service.database.DatabaseConnectionFactory;
import service.database.DatabaseService;

import java.sql.Connection;

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

}
