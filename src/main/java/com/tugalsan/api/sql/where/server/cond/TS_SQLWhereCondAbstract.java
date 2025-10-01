package com.tugalsan.api.sql.where.server.cond;

import module com.tugalsan.api.sql.sanitize;
import module java.sql;

abstract public class TS_SQLWhereCondAbstract {

    public TS_SQLWhereCondAbstract(CharSequence columnName) {
        this.columnName = columnName.toString();
        TS_SQLSanitizeUtils.sanitize(columnName);
    }
    final public String columnName;

    abstract public int fill(PreparedStatement fillStmt, int offset);
}
