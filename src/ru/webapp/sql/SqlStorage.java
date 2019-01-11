package ru.webapp.sql;

import ru.webapp.exception.NotExistStorageException;
import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;
import ru.webapp.storage.Storage;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume resume) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
            ps.setString(1,resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume r WHERE r.uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
