package App.Model.Businessrulebs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class Businessrule {

    private int _id;
    private int _type;
    private String _name;
    private boolean _applied;
    private String _operator;
    private Map<String, String> _bindings;
    private boolean _constraint;
    private String _table;
    private boolean _insert;
    private boolean _update;
    private boolean _delete;
    private String _error;
    private int _databasetype;

  
    public Businessrule(){};

    public Businessrule(int id, String name, boolean applied, String operator,
    boolean constraint,String table, boolean insert, boolean update, boolean delete, String error, int type, int databasetype) {
        this._id = id;
        this._name = name;
        this._applied = applied;
        this._operator = operator;
        this._type = type;
        this._constraint = constraint;
        this._table = table;
        this._insert = insert;
        this._update = update;
        this._delete = delete;
        this._error = error;
        this._databasetype = databasetype;
        this._bindings = new HashMap<String,String>();
        ArrayList<String> happenon = new ArrayList<String>();
        if(insert){happenon.add(" 'INS'");}
        if(update){happenon.add("'UPD'");}
        if(delete){happenon.add("'DEL'");}
        
        this._bindings.put("happenon", String.join(", ", happenon));
      
}

    public void addBindings( Map<String, String> bindings){
        for (Map.Entry<String,String> binding : bindings.entrySet()) {
            this._bindings.put(binding.getKey(),binding.getValue());
        }
    }

    public int id() {
        return this._id;
    }

    public String name() {
        return this._name;
    }

    public boolean applied() {
        return this._applied;
    }

    public String operator() {
        return this._operator;
    }

    public Map<String, String> bindings() {
        return this._bindings;
    }

 
    public boolean constraint(){
        return this._constraint;
    }

    public String table(){
        return this._table;
    }

    public boolean insert(){
        return this._insert;
    }
    public boolean update(){
        return this._update;
    }
    public boolean delete(){
        return this._delete;
    }
    public String error(){
        return this._error;
    }
    public int type(){
        return this._type;
    }
    public int databasetype(){
        return this._databasetype;
    }
}