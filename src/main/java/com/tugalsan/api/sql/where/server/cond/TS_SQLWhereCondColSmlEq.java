package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;
import java.sql.*;

public class TS_SQLWhereCondColSmlEq extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondColSmlEq.class);

    public TS_SQLWhereCondColSmlEq(CharSequence columnName, CharSequence columnName2) {
        super(columnName.toString());
        this.columnName2 = columnName2.toString();
    }
    final public String columnName2;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        TS_SQLSanitizeUtils.sanitize(columnName2);
        return TGS_StringUtils.cmn().concat(columnName, " <= ", columnName2);
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return offset;
    }
}
