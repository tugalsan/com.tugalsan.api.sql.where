package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import static com.tugalsan.api.sql.where.server.cond.TS_SQLWhereCondLngEq.d;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.unsafe.client.*;
import java.sql.*;

public class TS_SQLWhereCondBlobLenSmlOrEq extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondBlobLenSmlOrEq.class);

    public TS_SQLWhereCondBlobLenSmlOrEq(CharSequence columnName, long val) {
        super(columnName);
        this.val = val;
    }
    final public long val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.concat("LENGTH(", columnName, ") <= ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return TGS_UnSafe.compile(() -> {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            fillStmt.setLong(newOffset, val);
            return newOffset;
        });
    }
}
