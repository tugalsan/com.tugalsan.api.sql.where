module com.tugalsan.api.sql.where {
    requires java.sql;
    requires com.tugalsan.api.union;
    requires com.tugalsan.api.runnable;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.sql.sanitize;
    exports com.tugalsan.api.sql.where.server;
    exports com.tugalsan.api.sql.where.server.cond;
}
