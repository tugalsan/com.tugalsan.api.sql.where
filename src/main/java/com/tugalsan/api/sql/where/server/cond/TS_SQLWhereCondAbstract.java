package com.tugalsan.api.sql.where.server.cond;

import java.sql.*;
import com.tugalsan.api.sql.sanitize.server.*;

abstract public class TS_SQLWhereCondAbstract {

    public TS_SQLWhereCondAbstract(CharSequence columnName) {
        this.columnName = columnName.toString();
        TS_SQLSanitizeUtils.sanitize(columnName);
    }
    final public String columnName;

    abstract public int fill(PreparedStatement fillStmt, int offset);
}
