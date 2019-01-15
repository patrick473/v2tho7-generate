package App.Services;

import java.util.HashMap;
import java.util.Map;

import App.Model.Businessrulebs.Businessrule;
import App.Model.Databasebs.DatabaseType;
import App.Model.Templatebs.Template;
import App.persistence.tool.BusinessruleDAO;
import App.persistence.tool.DatabaseDAO;
import App.persistence.tool.TemplateDAO;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;

/**
 * GenerateService
 */
public class GenerateService {

    public String generateRule(int id){
        String returnvalue = "";
        BusinessruleDAO bdao = new BusinessruleDAO();
        TemplateDAO tdao = new TemplateDAO();
        DatabaseDAO ddao = new DatabaseDAO();
        Businessrule br = bdao.getRule(id);
        DatabaseType dt = ddao.getdatabaseType(br.databasetype());
        Template template = tdao.getTemplate(br.databasetype(), br.type(), br.constraint());
        
        
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


        // insert rule info into template
        // find array of applied rules to table
        // add rule to array
        // insert trigger
        return returnvalue;
    }
}