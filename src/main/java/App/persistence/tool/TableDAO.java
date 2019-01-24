package App.persistence.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * TableDAO
 */
public class TableDAO {

 
    public Map<String,Integer> getTables(int database, Connection con){
        Map<String,Integer> tables = new HashMap<String,Integer>();
        try{
            PreparedStatement pstmt = con.prepareStatement("select name,id from targettable where database =?");
            pstmt.setInt(1, database);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                tables.put(rs.getString(1), rs.getInt(2));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return tables;
    }
}