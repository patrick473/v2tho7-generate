package App.persistence.target;


import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import App.Model.Databasebs.Database;

public class TargetJDBCConnectionManager {

    private Database database;

    public TargetJDBCConnectionManager(Database database){
        this.database = database;
    }

    
    public  Connection getConnection() throws ClassNotFoundException, SQLException {
        Database database = this.database;
        Connection con = null;
        try{
            String url = String.format("jdbc:%s:thin:@//%s:%s/%s", database.dialect().jdbctypecode(),database.host(),database.port(),database.name());
            con = DriverManager.getConnection(url, database.username(), database.password());
            System.out.println(con.isValid(10)); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return con;

    }

}
