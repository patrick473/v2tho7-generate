package App.Services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import App.Model.Businessrulebs.Businessrule;
import App.Model.Databasebs.Database;
import App.Model.Databasebs.DatabaseType;
import App.Model.Templatebs.Template;
import App.persistence.target.TargetJDBCConnectionManager;
import App.persistence.target.TriggerDAO;
import App.persistence.tool.BusinessruleDAO;
import App.persistence.tool.DatabaseDAO;
import App.persistence.tool.TableDAO;
import App.persistence.tool.TemplateDAO;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;

/**
 * GenerateService
 */
public class GenerateService {
    private BusinessruleDAO bdao = new BusinessruleDAO();
    private DatabaseDAO ddao = new DatabaseDAO();
    private TemplateDAO tdao = new TemplateDAO();
    private TriggerDAO trigdao = new TriggerDAO();
    private SimpleTemplateEngine engine = new SimpleTemplateEngine();
    public String generateIntoDatabase(int database){
        

        Database targetDatabase = ddao.getDatabase(database);
     
        
        for(Map.Entry<String,Integer> table : targetDatabase.tables().entrySet() ){
            this.generateRulesOnTable(targetDatabase, table.getKey(), table.getValue());
            
        }
        
        return "big F";

    }
   
    
    public boolean generateRulesOnTable(Database database, String name, int id){
        
        String triggerName =  name+"trigger";
        
        String triggerTemplate = database.dialect().triggertemplate();
        Map<String,String> generalTriggerbindings = new HashMap<String,String>();
        generalTriggerbindings.put("name", triggerName);
        generalTriggerbindings.put("tablename", name);
        try{
        Writable triggerCode = this.engine.createTemplate(triggerTemplate).make(generalTriggerbindings);
        ArrayList<Businessrule> rules = this.bdao.getRulesOnTable(id);
        ArrayList<String> triggerCodeList = new ArrayList<String>();
        triggerCodeList.add(triggerCode.toString());
        for (Businessrule rule : rules) {
            triggerCodeList.add(this.generateRule(rule));
        }
        String triggerCodeString = String.join("\n", triggerCodeList) + "\n end;";
        this.trigdao.insertTrigger(database, triggerCodeString);

        } catch(Exception e){
            e.printStackTrace();
        }
        
     
        // generate them and add them to trigger

        // rules that are not triggers
        // generate constraints for them

        return true;
    }
    public String generateRule(Businessrule rule){
        String templatecode = "";
        Template template = tdao.getTemplate(rule.databasetype(), rule.type(), rule.constraint());
        try {
            Map<String,String> bindings = rule.bindings();
            bindings.put("operator", rule.operator());
            bindings.put("error", rule.error());
            Writable rulecode = engine.createTemplate(template.templatestring()).make(bindings);
            templatecode = rulecode.toString();
        } catch(Exception e){
            e.printStackTrace();
        }
        return templatecode;
    }
    /*public String generateRule(int id){
        String returnvalue = "";
        
        TemplateDAO tdao = new TemplateDAO();
        DatabaseDAO ddao = new DatabaseDAO();
        Businessrule br = this.bdao.getRule(id);
        DatabaseType dt = ddao.getdatabaseType(br.databasetype());
        Template template = tdao.getTemplate(br.databasetype(), br.type(), br.constraint());
        System.out.print(template.templatestring());
        
        String ruletemplate = template.templatestring();
        SimpleTemplateEngine engine = new SimpleTemplateEngine();
        try {
        Map<String,String> bindings = br.bindings();
        bindings.put("operator", br.operator());
        bindings.put("error", br.error());
        
        Writable rulecode = engine.createTemplate(ruletemplate).make(bindings);
        
        String fulltemplate = dt.triggertemplate() + rulecode.toString();

        Map<String,String> triggerbindings = new HashMap<String,String>();
        triggerbindings.put("name", br.name());
        triggerbindings.put("tablename", br.table());

        Writable fullCode = engine.createTemplate(fulltemplate).make(triggerbindings);
        returnvalue = fullCode.toString()+ "\n end;";
    } catch(Exception e){
            e.printStackTrace();
        }


      
        return returnvalue;
    }*/
}