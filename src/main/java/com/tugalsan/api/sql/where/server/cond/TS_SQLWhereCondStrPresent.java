package com.tugalsan.api.sql.where.server.cond;

import module com.tugalsan.api.log;
import module com.tugalsan.api.sql.sanitize;
import module com.tugalsan.api.string;
import module java.sql;

public class TS_SQLWhereCondStrPresent extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrPresent.class);

    public TS_SQLWhereCondStrPresent(CharSequence columnName) {
        super(columnName.toString());
    }

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat(columnName, " <> ''");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return offset;
    }
}
