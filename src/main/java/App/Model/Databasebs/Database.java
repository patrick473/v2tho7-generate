package App.Model.Databasebs;

import java.util.ArrayList;
import java.util.Map;



public class Database {

    private int _id;
    private String _name;
    private String _username;
    private String _password;
    private String _host;
    private String _schema;
    private String _port;
    private DatabaseType _dialect;
    private Map<String,Integer> _tables;

    public Database(){}
    public Database(int id,String name, String username, String password, String host, String schema, String port,
            DatabaseType dialect, Map<String,Integer> tables) {
        this._id = id;
        this._name = name;
        this._username = username;
        this._password = password;
        this._host = host;
        this._schema = schema;
        this._port = port;
        this._dialect = dialect;
        this._tables = tables;
    }

    public int id(){
        return this._id;
    }

    public void setID(int id){
         this._id = id;
    }

    public String name() {
        return this._name;
    }

    public String username() {
        return this._username;
    }

    public String password() {
        return this._password;
    }

    public String host() {
        return this._host;
    }

    public String schema() {
        return this._schema;
    }

    public String port() {
        return this._port;
    }

    public DatabaseType dialect() {
        return this._dialect;
    }

    public Map<String,Integer> tables() {
        return this._tables;
    }
}