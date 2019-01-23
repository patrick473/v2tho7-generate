package App.persistence.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import App.Model.Databasebs.Database;
import App.Model.Databasebs.DatabaseType;

/**
 * DatabaseDAO
 */
public class DatabaseDAO {

    TableDAO tableDAO;
    JDBCSingleton jdbcInstance;
    public DatabaseDAO() {
        this.jdbcInstance = JDBCSingleton.getInstance();
        this.tableDAO = new TableDAO();
    }
    public Database getDatabase(int id){
        Database database = new Database();
        try {
            Connection con = this.jdbcInstance.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from database where id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {   
                DatabaseType dialect = this.getdatabaseType(rs.getInt(2), con);
                Map<String,Integer> tables = this.tableDAO.getTables(id,con);
                database = new Database(id, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), dialect,tables);
                
            }
            con.close();
        }
         catch(Exception e){
            e.printStackTrace();
        }
        return database;
    }
    public DatabaseType getdatabaseType(int id,Connection con){
        DatabaseType databasetype = new DatabaseType();
        try {
           
        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery("select * from databasetype where id ="+ id);
        if (rs.next()) {
            
            databasetype = new DatabaseType(id,rs.getString(2),rs.getString(3),rs.getString(4));
            
        }
      
    }catch(Exception e){
        e.printStackTrace();
        
    }
    return databasetype;
    }
}