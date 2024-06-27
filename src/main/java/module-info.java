module com.tugalsan.api.sql.where {
    requires java.sql;
    requires com.tugalsan.api.function;
    requires com.tugalsan.api.unsafe;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.sql.sanitize;
    exports com.tugalsan.api.sql.where.server;
    exports com.tugalsan.api.sql.where.server.cond;
}
