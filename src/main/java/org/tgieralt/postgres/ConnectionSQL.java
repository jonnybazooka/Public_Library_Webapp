package org.tgieralt.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ConnectionSQL {

    public static DataSource getDatasource() throws SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
}
