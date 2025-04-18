package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;

import java.sql.*;

public class TS_SQLWhereCondLngBtwEncl extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondLngBtwEncl.class);

    public TS_SQLWhereCondLngBtwEncl(CharSequence columnName, long min, long max) {
        super(columnName);
        this.min = min;
        this.max = max;
    }

    final public long min;
    final public long max;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat(columnName, " BETWEEN ? AND ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return TGS_FuncMTCUtils.call(() -> {
            d.ci("fill", "processed", offset, min, max);
            var newOffset = offset + 1;
            fillStmt.setLong(newOffset, min);
            newOffset = newOffset + 1;
            fillStmt.setLong(newOffset, max);
            return newOffset;
        });
    }
}
