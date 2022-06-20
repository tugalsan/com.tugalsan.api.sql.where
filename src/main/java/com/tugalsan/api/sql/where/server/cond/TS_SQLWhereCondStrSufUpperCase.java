package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;
import java.sql.*;

public class TS_SQLWhereCondStrSufUpperCase extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrSufUpperCase.class.getSimpleName());

    public TS_SQLWhereCondStrSufUpperCase(CharSequence columnName, CharSequence val) {
        super(columnName);
        this.val = val == null ? null : val.toString();
    }
    final public String val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.concat("UCASE(", columnName, ") LIKE ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        try {
            d.ci("fill", "processed", offset, val);
            if (val != null) {
                fillStmt.setString(++offset, TGS_StringUtils.concat( "%".concat(val)));
            } else {
                fillStmt.setString(++offset, TGS_StringUtils.concat("null"));
            }
            return offset;
        } catch (SQLException ex) {
            throw new RuntimeException(TS_SQLWhereCondLngSmlOrEq.class.getSimpleName() + ".fill", ex);
        }
    }
}
