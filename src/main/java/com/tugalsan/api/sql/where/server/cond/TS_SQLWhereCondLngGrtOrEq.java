package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;

import java.sql.*;

public class TS_SQLWhereCondLngGrtOrEq extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondLngGrtOrEq.class);

    public TS_SQLWhereCondLngGrtOrEq(CharSequence columnName, long val) {
        super(columnName);
        this.val = val;
    }
    final public long val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat(columnName, " >= ?");
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
