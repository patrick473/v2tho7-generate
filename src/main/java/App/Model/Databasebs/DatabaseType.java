package App.Model.Databasebs;

/**
 * DatabaseType
 */
public class DatabaseType {

    private String _jdbcTypeCode;
    private String _triggerTemplate;
    private String _constraintTemplate;

    public DatabaseType(){}
    public DatabaseType(String jdbcTypeCode, String triggerTemplate, String constraintTemplate) {
        this._jdbcTypeCode = jdbcTypeCode;
        this._triggerTemplate = triggerTemplate;
        this._constraintTemplate = constraintTemplate;
    }

    public String jdbctypecode() {
        return this._jdbcTypeCode;
    }

    public String triggertemplate() {
        return this._triggerTemplate;
    }
    public String constraintTemplate() {
        return this._constraintTemplate;
    }

}