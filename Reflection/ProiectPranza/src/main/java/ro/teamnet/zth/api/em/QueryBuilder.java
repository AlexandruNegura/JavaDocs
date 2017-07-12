package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandru.Negura on 7/12/2017.
 */
public class QueryBuilder {
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    public Object getTableName() {
        return tableName;
    }

    public List<ColumnInfo> getQueryColumns() {
        return queryColumns;
    }

    public void setQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns = queryColumns;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public String getValueForQuery(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Date) {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
        } else {
            return value.toString();
        }
    }

    public QueryBuilder addCondition(Condition condition){
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName){
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType = queryType;
        return this;
    }
}
