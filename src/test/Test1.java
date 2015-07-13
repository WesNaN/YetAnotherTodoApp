package test;

import service.ConnectionError;
import service.database.DatabaseService;

public class Test1
{
    public static void main(String args[]) throws ConnectionError
    {
        System.out.println("Test");

        DatabaseService service = new DatabaseService();

    }
}