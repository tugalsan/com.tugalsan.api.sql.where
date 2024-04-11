package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.sql.*;

public class TS_SQLWhereCondBlobLenSml extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondBlobLenSml.class);

    public TS_SQLWhereCondBlobLenSml(CharSequence columnName, long val) {
        super(columnName);
        this.val = val;
    }
    final public long val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.concat("LENGTH(", columnName, ") < ?");
    }

    @Override
    public TGS_UnionExcuse<Integer> fill(PreparedStatement fillStmt, int offset) {
        try {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            fillStmt.setLong(newOffset, val);
            return TGS_UnionExcuse.of(newOffset);
        } catch (SQLException ex) {
            return TGS_UnionExcuse.ofExcuse(ex);
        }
    }
}
