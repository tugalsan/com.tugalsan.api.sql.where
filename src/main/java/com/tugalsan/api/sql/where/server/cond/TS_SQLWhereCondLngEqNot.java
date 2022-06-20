package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;
import java.sql.*;

public class TS_SQLWhereCondLngEqNot extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondLngEqNot.class.getSimpleName());

    public TS_SQLWhereCondLngEqNot(CharSequence columnName, long val) {
        super(columnName);
        this.val = val;
    }
    final public long val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.concat(columnName, " <> ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        try {
            d.ci("fill", "processed", offset, val);
            fillStmt.setLong(++offset, val);
            return offset;
        } catch (SQLException ex) {
            throw new RuntimeException(TS_SQLWhereCondLngEqNot.class.getSimpleName() + ".fill", ex);
        }
    }
}
