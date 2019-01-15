package ru.webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

//    public void createStatement(String statement) {
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement ps = connection.prepareStatement(statement)) {
//            execute(statement, ps::execute);
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
//    }

    public void execute(String statement, SqlExecutor executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(statement)) {
            executor.execute(statement, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
