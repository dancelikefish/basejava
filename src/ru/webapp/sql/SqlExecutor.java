package ru.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutor {
    Object execute(String statement, PreparedStatement ps) throws SQLException;
}
