package App.persistence.tool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import App.Model.Databasebs.DatabaseType;

/**
 * DatabaseDAO
 */
public class DatabaseDAO {

    JDBCSingleton jdbcInstance;
    public DatabaseDAO() {
        this.jdbcInstance = JDBCSingleton.getInstance();
    }

    public DatabaseType getdatabaseType(int id){
        DatabaseType databasetype = new DatabaseType();
        try {
        Connection con = this.jdbcInstance.getConnection();
        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery("select * from databasetype where id ="+id);
        if (rs.next()) {
            
            databasetype = new DatabaseType(rs.getString(2),rs.getString(3),rs.getString(4));
            
        }
        con.close();
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    return databasetype;
    }
}