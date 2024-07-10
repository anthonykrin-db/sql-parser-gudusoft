package parser;

import java.util.Objects;

public class ColumnToken {
    private String catalog;
    private String schema;
    private String table;
    private String column;
    private String expr;

    public String getCatalog() {
        return catalog;
    }

    public String getSchema() {
        return schema;
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }

    public String getExpr() {
        return expr;
    }

    public static ColumnToken withTable(String catalog, String schema, String table) {
        ColumnToken ce=new ColumnToken();
        ce.catalog = catalog;
        ce.schema = schema;
        ce.table = table;
        return ce;
    }

    public static ColumnToken withColumn(String catalog, String schema, String table, String column) {
        ColumnToken ce=new ColumnToken();
        ce.catalog = catalog;
        ce.schema = schema;
        ce.table = table;
        ce.column = column;
        return ce;
    }

    public static ColumnToken withExpr(String catalog, String schema, String table, String column, String expr) {
        ColumnToken ce=new ColumnToken();
        ce.catalog = catalog;
        ce.schema = schema;
        ce.table = table;
        ce.column = column;
        ce.expr = expr;
        return ce;
    }


    @Override
    public String toString(){
        if (this.column!=null) {
            return getEscapedString(this.catalog)+ "." + getEscapedString(this.schema)+ "." + getEscapedString(this.table) + "." + getEscapedString(this.column) ;
        } else {
            return getEscapedString(this.catalog)+ "." + getEscapedString(this.schema)+ "." + getEscapedString(this.table);
        }
    }

    private String getEscapedString(String token){
        if (token.indexOf(" ")>-1){
            return "`" + token+ "`.`";
        } else {
            return token;
        }
    }

    // Constructor and other methods...

    @Override
    public int hashCode() {
        return Objects.hash(catalog,schema, table, column, expr);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColumnToken other = (ColumnToken) obj;
        return Objects.equals(catalog, other.catalog) &&
                Objects.equals(schema, other.schema) &&
                Objects.equals(table, other.table) &&
                Objects.equals(column, other.column) &&
                Objects.equals(expr, other.expr);
    }

}
