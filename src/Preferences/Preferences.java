package Preferences;

import service.ConnectionError;
import service.DataService;
import service.database.DatabaseService;

public class Preferences
{
    private static final DataService DEFAULT_DATASERVICE = new DatabaseService();
    public static DataService dataService = DEFAULT_DATASERVICE;

    public static void setDataService(DataService dataService)
    {
        Preferences.dataService = dataService;
    }
}
