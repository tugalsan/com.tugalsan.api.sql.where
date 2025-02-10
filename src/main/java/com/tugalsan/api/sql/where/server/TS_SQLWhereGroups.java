package com.tugalsan.api.sql.where.server;


import com.tugalsan.api.function.client.maythrow.uncheckedexceptions.TGS_FuncMTUCE_In1;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.tuple.client.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

public class TS_SQLWhereGroups {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereGroups.class);

    public TS_SQLWhereGroups(boolean operatorIsAnd) {
        this.operatorIsAnd = operatorIsAnd;
    }
    final private boolean operatorIsAnd;

    private final List<TS_SQLWhereGroups> groups = TGS_ListUtils.of();
    private final List<TS_SQLWhereConditions> conditions = TGS_ListUtils.of();

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        d.ci("isEmpty", "conditions.size", conditions.size(), "groups.size", groups.size());
        if (!conditions.isEmpty()) {
            d.ci("isEmpty", "!conditions.isEmpty()", "return false");
            return false;
        }
        if (groups.isEmpty()) {
            d.ci("isEmpty", "groups.isEmpty()", "return false");
            return true;
        }
        var result = !groups.stream().filter(g -> g.isPresent()).findAny().isPresent();
        d.ci("isEmpty", "!groups.stream().filter(g -> g.isPresent()).findAny().isPresent()", "return", !groups.stream().filter(g -> g.isPresent()).findAny().isPresent());
        return result;
    }

    public TS_SQLWhereGroups groupsAnd(TGS_FuncMTUCE_In1<TS_SQLWhereGroups> groups) {
        var g = new TS_SQLWhereGroups(true);
        TS_SQLWhereGroups.this.groups.add(g);
        groups.run(g);
        return g;
    }

    public TS_SQLWhereGroups groupsOr(TGS_FuncMTUCE_In1<TS_SQLWhereGroups> groups) {
        var g = new TS_SQLWhereGroups(false);
        TS_SQLWhereGroups.this.groups.add(g);
        groups.run(g);
        return g;
    }

    public TS_SQLWhereConditions conditionsAnd(TGS_FuncMTUCE_In1<TS_SQLWhereConditions> conditions) {
        var c = new TS_SQLWhereConditions(true);
        TS_SQLWhereGroups.this.conditions.add(c);
        conditions.run(c);
        return c;
    }

    public TS_SQLWhereConditions conditionsOr(TGS_FuncMTUCE_In1<TS_SQLWhereConditions> conditions) {
        var c = new TS_SQLWhereConditions(false);
        TS_SQLWhereGroups.this.conditions.add(c);
        conditions.run(c);
        return c;
    }

    private String toStringConditions() {
        var sbConditions = new StringBuilder("(");
        IntStream.range(0, conditions.size()).forEachOrdered(i -> {
            if (i != 0) {
                addOperator(sbConditions);
            }
            sbConditions.append(conditions.get(i).toString());
        });
        sbConditions.append(")");
        var sbConditionsToString = sbConditions.toString();
        if (sbConditionsToString.replace("(", "").replace(")", "").isEmpty()) {
            sbConditionsToString = "";
        }
        d.ci("toStringConditions", sbConditionsToString);
        return sbConditionsToString;
    }

    private String toStringGroups() {
        var sbGroups = new StringBuilder("(");
        IntStream.range(0, groups.size()).forEachOrdered(i -> {
            if (i != 0) {
                addOperator(sbGroups);
            }
            sbGroups.append(groups.get(i).toString());
        });
        sbGroups.append(")");
        var sbGroupsToString = sbGroups.toString();
        if (sbGroupsToString.replace("(", "").replace(")", "").isEmpty()) {
            sbGroupsToString = "";
        }
        d.ci("toStringGroups", sbGroupsToString);
        return sbGroupsToString;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            var result = "";
            d.ci("toString", "isEmpty()", "-> return", result);
            return result;
        }

        var sbConditionsToString = toStringConditions();
        var sbGroupsToString = toStringGroups();
        var sbConditionsEmpty = sbConditionsToString.isEmpty();
        var sbGroupsEmpty = sbGroupsToString.isEmpty();

        //IF BOTH EMPTY
        if (sbConditionsEmpty && sbGroupsEmpty) {
            d.ci("toString", "bothEmpty", "return ''");
            return "";
        }

        //IF ONE OF EMPTY
        if (sbConditionsEmpty) {
            d.ci("toString", "sbConditionsEmpty", "return sbGroupsToString");
            return sbGroupsToString;
        }
        if (sbGroupsEmpty) {
            d.ci("toString", "sbGroupsEmpty", "return sbConditionsToString");
            return sbConditionsToString;
        }

        //COMBINE
        var result = String.join(getOperator(), sbConditionsToString, sbGroupsToString);
        d.ci("toString", "combine", "return", result);
        return result;
    }

    public int fill(PreparedStatement stmt, int offset) {
        d.ci("fill", "processed");
        TGS_Tuple1<Integer> pack = new TGS_Tuple1(offset);
        conditions.stream().forEachOrdered(c -> pack.value0 = c.fill(stmt, pack.value0));
        groups.stream().forEachOrdered(g -> pack.value0 = g.fill(stmt, pack.value0));
        return pack.value0;
    }

    private void addOperator(StringBuilder sb) {
        sb.append(getOperator());
    }

    private String getOperator() {
        return operatorIsAnd ? " AND " : " OR ";
    }
}
