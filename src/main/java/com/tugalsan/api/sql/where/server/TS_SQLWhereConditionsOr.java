package com.tugalsan.api.sql.where.server;

public class TS_SQLWhereConditionsOr extends TS_SQLWhereConditions {

    public TS_SQLWhereConditionsOr(boolean operatorIsAnd) {
        super(operatorIsAnd);
    }

    public TS_SQLWhereConditionsOr strLike_needsOR(CharSequence columnName, CharSequence val, CharSequence delim) {
        var _val = val.toString();
        var _delim = delim.toString();
        strEq(columnName, val);
        strCon(columnName, _delim + _val + _delim);
        strPre(columnName, _val + _delim);
        strSuf(columnName, _delim + _val);
        return this;
    }
}
