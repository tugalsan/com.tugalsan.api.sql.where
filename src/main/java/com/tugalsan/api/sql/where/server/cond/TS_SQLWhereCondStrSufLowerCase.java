package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;

import java.sql.*;

public class TS_SQLWhereCondStrSufLowerCase extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrSufLowerCase.class);

    public TS_SQLWhereCondStrSufLowerCase(CharSequence columnName, CharSequence val) {
        super(columnName);
        this.val = val == null ? null : val.toString();
    }
    final public String val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat("LCASE(", columnName, ") LIKE ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return TGS_FuncMTCUtils.call(() -> {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            if (val != null) {
                fillStmt.setString(newOffset, TGS_StringUtils.cmn().concat("%".concat(val)));
            } else {
                fillStmt.setString(newOffset, TGS_StringUtils.cmn().concat("null"));
            }
            return newOffset;
        });
    }
}
