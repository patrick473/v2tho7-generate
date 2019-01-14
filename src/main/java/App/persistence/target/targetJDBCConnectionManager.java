package App.persistence.target;


import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import App.Model.Databasebs.Database;

class targetJDBCConnectionManager {

    private Database database;

    public targetJDBCConnectionManager(Database database){
        this.database = database;
    }

    
    public  Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection con = null;
        con = DriverManager.getConnection("jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC14", "tool", "tooldb");
        return con;

    }

}