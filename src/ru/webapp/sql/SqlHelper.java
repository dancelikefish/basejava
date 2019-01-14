package ru.webapp.sql;

import ru.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public PreparedStatement createStatement(String statement) {
        try { Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(statement);
            execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    public void execute() {

    }


}
