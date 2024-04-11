package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.sql.*;

public class TS_SQLWhereCondStrPreUpperCase extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrPreUpperCase.class);

    public TS_SQLWhereCondStrPreUpperCase(CharSequence columnName, CharSequence val) {
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
    public TGS_UnionExcuse<Integer> fill(PreparedStatement fillStmt, int offset) {
        try {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            if (val != null) {
                fillStmt.setString(newOffset, TGS_StringUtils.concat(val.concat("%")));
            } else {
                fillStmt.setString(newOffset, TGS_StringUtils.concat("null"));
            }
            return TGS_UnionExcuse.of(newOffset);
        } catch (SQLException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }
}
