package App.persistence.tool;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public Businessrule getRule(int id){
        Businessrule br = new Businessrule();
        try {
        Connection con = this.jdbcInstance.getConnection();
        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery("select TARGETTABLE.NAME as targettable ,BUSINESSRULE.name,operator.ACTION as operator, BUSINESSRULE.APPLIED, BUSINESSRULE.CONSTRAINT, BUSINESSRULE.ONINSERT, BUSINESSRULE.ONUPDATE,BUSINESSRULE.ONDELETE, BUSINESSRULE.ERROR, businessrule.businessruletype, database.databasetype from BUSINESSRULE, operator,TARGETTABLE, database where BUSINESSRULE.OPERATOR = OPERATOR.ID and BUSINESSRULE.TARGETTABLE = TARGETTABLE.ID and targettable.database = database.databasetype and BUSINESSRULE.ID  =" + id);
        if (rs.next()) {
            boolean applied = false;
            boolean constraint = false;
            boolean insert = false;
            boolean update = false;
            boolean delete = false;
            if(rs.getInt(4) == 1){
                applied = true;
            }
            if(rs.getInt(5) == 1){
                constraint = true;
            }
            if(rs.getInt(6) == 1){
                insert = true;
            }
            if(rs.getInt(7) == 1){
                update = true;
            }
            if(rs.getInt(8) == 1){
                delete = true;
            }
            br = new Businessrule(
               id, rs.getString(2), applied, rs.getString(3), constraint, rs.getString(1), insert, update, delete, rs.getString(9),rs.getInt(10),rs.getInt(11));
            br.addBindings(this.getBindings(id));
            con.close();
        }
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    return br;
    }

    public Map<String,String> getBindings(int id){
        Map<String,String> bindings = new HashMap<String,String>();
        try {
            Connection con = this.jdbcInstance.getConnection();
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