package App.persistence.tool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import App.Model.Businessrulebs.Businessrule;

/**
 * BusinessruleDAO
 */
public class BusinessruleDAO {

    private JDBCSingleton jdbcInstance;
  
    public BusinessruleDAO() {
        this.jdbcInstance = JDBCSingleton.getInstance();
     

    }
    public ArrayList<Businessrule> getRulesOnTable(int table){
        ArrayList<Businessrule> rules = new ArrayList<Businessrule>();
        try{
            Connection con = this.jdbcInstance.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select businessrule.id, TARGETTABLE.NAME as targettable ,BUSINESSRULE.name,operator.ACTION as operator, BUSINESSRULE.APPLIED, BUSINESSRULE.CONSTRAINT, BUSINESSRULE.ONINSERT, BUSINESSRULE.ONUPDATE,BUSINESSRULE.ONDELETE, BUSINESSRULE.ERROR, businessrule.businessruletype, database.databasetype from BUSINESSRULE, operator,TARGETTABLE, database where BUSINESSRULE.OPERATOR = OPERATOR.ID and BUSINESSRULE.TARGETTABLE = TARGETTABLE.ID and targettable.database = database.databasetype and businessrule.constraint = 0 and BUSINESSRULE.targettable  = ?");
            pstmt.setInt(1, table);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                boolean applied = false;
            boolean constraint = false;
            boolean insert = false;
            boolean update = false;
            boolean delete = false;
            if(rs.getInt(5) == 1){
                applied = true;
            }
            if(rs.getInt(6) == 1){
                constraint = true;
            }
            if(rs.getInt(7) == 1){
                insert = true;
            }
            if(rs.getInt(8) == 1){
                update = true;
            }
            if(rs.getInt(9) == 1){
                delete = true;
            }
            Businessrule br = new Businessrule(
               rs.getInt(1), rs.getString(3), applied, rs.getString(4), constraint, rs.getString(2), insert, update, delete, rs.getString(10),rs.getInt(11),rs.getInt(12));
            br.addBindings(this.getBindings(rs.getInt(1),con));
            rules.add(br);
           
            }
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return rules;
    }
   

    public Map<String,String> getBindings(int id, Connection con){
        Map<String,String> bindings = new HashMap<String,String>();
        try {
            
            Statement query = con.createStatement();
           
            ResultSet rs = query.executeQuery("select * from binding where businessrule = "+id);

            while (rs.next()) {
                
                bindings.put(rs.getString(3),rs.getString(4));
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bindings;
    }
    

}