package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.sql.*;

public class TS_SQLWhereCondStrEmpty extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrEmpty.class);

    public TS_SQLWhereCondStrEmpty(CharSequence columnName) {
        super(columnName.toString());
    }

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.concat(columnName, " = ''");
    }

    @Override
    public TGS_UnionExcuse<Integer> fill(PreparedStatement fillStmt, int offset) {
        return TGS_UnionExcuse.of(offset);
    }
}
