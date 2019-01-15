package App.persistence.tool;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import App.Model.Businessrulebs.Businessrule;
import App.Model.Templatebs.Template;

/**
 * BusinessruleDAO
 */
public class TemplateDAO {

    private JDBCSingleton jdbcInstance;
  
    public TemplateDAO() {
        this.jdbcInstance = JDBCSingleton.getInstance();
     

    }

    public Template getTemplate(int databasetype, int ruletype, boolean isConstraint){
        Template template = new Template();
        try {
        Connection con = this.jdbcInstance.getConnection();
        Statement query = con.createStatement();
        int isconstraint = 0;
        if(isConstraint){
            isconstraint = 1;
        }
        ResultSet rs = query.executeQuery("select * from template where databasetype = "+databasetype+" and ruletype ="+ ruletype+" and isconstraint ="+isconstraint);
        if (rs.next()) {
            
            template = new Template(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),isConstraint);
            
        }
        con.close();
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    return template;
    }

  
    

}