package Database.Connection;

import java.util.HashMap;

public class Connection {
    public static HashMap<String, String> getConnectionVariables(){
        HashMap<String, String> connectionVariables = new HashMap<>();
        connectionVariables.put("url", "");
        connectionVariables.put("user","");
        connectionVariables.put("pw","");
        return connectionVariables;
    }
}
