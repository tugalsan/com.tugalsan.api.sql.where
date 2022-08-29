package com.tugalsan.api.sql.where.server.cond;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import static com.tugalsan.api.sql.where.server.cond.TS_SQLWhereCondStrPreLowerCase.d;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.unsafe.client.*;
import java.sql.*;

public class TS_SQLWhereCondStrPre extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrPre.class);

    public TS_SQLWhereCondStrPre(CharSequence columnName, CharSequence val) {
        super(columnName);
        this.val = val == null ? null : val.toString();
    }
    final public String val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.concat(columnName, " LIKE ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
       return TGS_UnSafe.compile(() -> {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            if (val != null) {
                fillStmt.setString(newOffset, TGS_StringUtils.concat(val.concat("%")));
            } else {
                fillStmt.setString(newOffset, TGS_StringUtils.concat("null"));
            }
            return newOffset;
        });
    }
}
