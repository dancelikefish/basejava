package ru.webapp.sql;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;
import ru.webapp.storage.Storage;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        PreparedStatement ps = sqlHelper.createStatement("DELETE FROM resume");
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume resume) {
        PreparedStatement ps = sqlHelper.createStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)");
        try {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        PreparedStatement ps = sqlHelper.createStatement("UPDATE resume SET full_name = ? WHERE uuid = ?");
        try {
            ps.setString(2, resume.getFullName());
            ps.setString(1, resume.getUuid());
            ps.execute();
        } catch (
                SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public Resume get(String uuid) {
        PreparedStatement ps = sqlHelper.createStatement("SELECT * FROM resume r WHERE r.uuid = ?");
        try {
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
        PreparedStatement ps = sqlHelper.createStatement("DELETE FROM resume WHERE uuid = ?");
        try {
            ps.setString(1, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        PreparedStatement ps = sqlHelper.createStatement("SELECT * FROM resume");
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String uuid = resultSet.getString(1);
                String fullName = resultSet.getString(2);
                resumes.add(new Resume(uuid, fullName));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        resumes.sort(Comparator.comparing(Resume::getFullName));
        return resumes;
    }

    @Override
    public int size() {
        return getAllSorted().size();
    }
}
