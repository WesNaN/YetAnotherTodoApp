package service;

public class ConnectionError extends Exception
{
    public ConnectionError(String msg, Exception e)
    {
        if (msg != null)
        {
            System.out.println(msg);
        }
        e.printStackTrace();
    }

}
