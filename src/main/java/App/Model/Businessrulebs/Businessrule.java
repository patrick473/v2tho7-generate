package App.Model.Businessrulebs;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class Businessrule {

    private int _id;
    private String _name;
    private boolean _applied;
    private int _operator;
    private Map<String, String> _bindings;
    private int _type;
    private boolean _constraint;
    private int _table;
    private boolean _insert;
    private boolean _update;
    private boolean _delete;
    private String _error;

  
    public Businessrule(int id, String name, boolean applied, int operator, Map<String, String> bindings,
    int type,boolean constraint,int table, boolean insert, boolean update, boolean delete, String error) {
        this._id = id;
        this._name = name;
        this._applied = applied;
        this._operator = operator;
        this._bindings = bindings;
        this._type = type;
        this._constraint = constraint;
        this._table = table;
        this._insert = insert;
        this._update = update;
        this._delete = delete;
        this._error = error;
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

    public int operator() {
        return this._operator;
    }

    public Map<String, String> bindings() {
        return this._bindings;
    }

    public int type() {
        return this._type;
    }
    public boolean constraint(){
        return this._constraint;
    }

    public int table(){
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
}