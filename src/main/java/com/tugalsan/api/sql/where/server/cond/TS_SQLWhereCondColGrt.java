package com.tugalsan.api.sql.where.server.cond;

import module com.tugalsan.api.log;
import module com.tugalsan.api.sql.sanitize;
import module com.tugalsan.api.string;
import module java.sql;

public class TS_SQLWhereCondColGrt extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondColGrt.class);

    public TS_SQLWhereCondColGrt(CharSequence columnName, CharSequence columnName2) {
        super(columnName.toString());
        this.columnName2 = columnName2.toString();
    }
    final public String columnName2;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        TS_SQLSanitizeUtils.sanitize(columnName2);
        return TGS_StringUtils.cmn().concat(columnName, " > ", columnName2);
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return offset;
    }
}
