package test;

import service.database.DatabaseService;

public class Test1
{
    public static void main(String args[])
    {
        System.out.println("Test");

        DatabaseService service = new DatabaseService();

        service.connect();
    }
}