package com.tugalsan.api.sql.where.server;

import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.sql.*;

public class TS_SQLWhere {

    public final static TS_Log d = TS_Log.of(TS_SQLWhere.class);

    private TS_SQLWhereGroups group = null;

    public TS_SQLWhereGroups groupsAnd(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        group = new TS_SQLWhereGroups(true);
        return group.groupsAnd(groups);
    }

    public TS_SQLWhereGroups groupsOr(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        group = new TS_SQLWhereGroups(false);
        return group.groupsOr(groups);
    }

    public TS_SQLWhereConditions conditionsAnd(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        var wrap = new Object() {
            TS_SQLWhereConditions conditions = null;
        };
        groupsAnd(groups -> wrap.conditions = groups.conditionsAnd(conditions));
        return wrap.conditions;
    }

    public TS_SQLWhereConditions conditionsOr(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        var wrap = new Object() {
            TS_SQLWhereConditions conditions = null;
        };
        groupsOr(groups -> wrap.conditions = groups.conditionsOr(conditions));
        return wrap.conditions;
    }

    @Override
    public String toString() {
        if (group == null) {
            return "";
        }
        var groupStr = group.toString();
        if (groupStr.replace("(", "").replace(")", "").isEmpty()) {
            return "";
        }
        return "WHERE " + groupStr;
    }

    public TGS_UnionExcuse<Integer> fill(PreparedStatement stmt, int offset) {
        d.ci("fill", "processed");
        return group.fill(stmt, offset);
    }
}
