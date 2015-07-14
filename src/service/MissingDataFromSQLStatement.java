package service;

public class MissingDataFromSQLStatement extends Exception{

    public MissingDataFromSQLStatement(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingDataFromSQLStatement(String message) {
        super(message);
    }
}
