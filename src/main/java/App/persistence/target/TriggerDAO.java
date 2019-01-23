package App.persistence.target;

import java.sql.Connection;
import java.sql.Statement;

import App.Model.Databasebs.Database;

/**
 * TriggerDAO
 */
public class TriggerDAO {

    public boolean insertTrigger(Database database,String trigger){
        TargetJDBCConnectionManager connectionManager = new TargetJDBCConnectionManager(database);
        boolean result = false;
        try {
            Connection con = connectionManager.getConnection();
            Statement stmt = con.createStatement();
            result = stmt.execute(trigger);
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}