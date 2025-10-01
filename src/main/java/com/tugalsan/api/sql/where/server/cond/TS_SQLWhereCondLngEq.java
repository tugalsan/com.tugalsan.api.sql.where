package com.tugalsan.api.sql.where.server.cond;

import module com.tugalsan.api.function;
import module com.tugalsan.api.log;
import module com.tugalsan.api.sql.sanitize;
import module com.tugalsan.api.string;
import module java.sql;

public class TS_SQLWhereCondLngEq extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondLngEq.class);

    public TS_SQLWhereCondLngEq(CharSequence columnName, long val) {
        super(columnName);
        this.val = val;
    }
    final public long val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat(columnName, " = ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return TGS_FuncMTCUtils.call(() -> {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            fillStmt.setLong(newOffset, val);
            return newOffset;
        });
    }
}
