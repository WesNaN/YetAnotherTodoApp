package test;

import service.ConnectionError;
import service.database.DatabaseService;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test1
{
    public static void main(String args[]) throws ConnectionError
    {
        System.out.println("Test");

        //DatabaseService service = new DatabaseService();

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }
}