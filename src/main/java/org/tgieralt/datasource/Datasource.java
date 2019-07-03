package org.tgieralt.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Datasource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource hikariDataSource;

    static {
        config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(10);
        hikariDataSource = new HikariDataSource(config);
    }

    private Datasource() {}

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
