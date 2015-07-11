package service.database;

import model.Label;
import model.Project;
import model.Task;
import service.ConnectionError;
import service.DataService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseService implements DataService
{
    public void connect()
    {
        try
        {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/test", "test", "");
            Statement stmt = con.createStatement();
            //stmt.executeUpdate( "DROP TABLE table1" );
            stmt.executeUpdate( "CREATE TABLE table1 ( user varchar(50) )" );
            stmt.executeUpdate( "INSERT INTO table1 ( user ) VALUES ( 'Andre' )" );
            stmt.executeUpdate( "INSERT INTO table1 ( user ) VALUES ( 'Nicholas' )" );

            ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
            while( rs.next() )
            {
                String name = rs.getString("user");
                System.out.println( name );
            }
            stmt.close();
            con.close();
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }

    }

    @Override
    public void addTask(Task task) throws ConnectionError
    {

    }

    @Override
    public void removeTask(Task task) throws ConnectionError
    {

    }

    @Override
    public void updateTask(Task task) throws ConnectionError
    {

    }

    @Override
    public void addProject(Project project) throws ConnectionError
    {

    }

    @Override
    public void removeProject(Project project) throws ConnectionError
    {

    }

    @Override
    public void addLabel(Label label) throws ConnectionError
    {

    }

    @Override
    public void removeLabel(Label label) throws ConnectionError
    {

    }
}
