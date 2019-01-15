package ru.webapp.sql;


import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;
import ru.webapp.storage.Storage;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(2, resume.getFullName());
            ps.setString(1, resume.getUuid());
            ps.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        final Resume[] resume = new Resume[1];
        sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            resume[0] = new Resume(uuid, resultSet.getString("full_name"));
        });
        return resume[0];
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        sqlHelper.execute("SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString(1);
                String fullName = rs.getString(2);
                resumes.add(new Resume(uuid, fullName));
            }
        });
        resumes.sort(Comparator.comparing(Resume::getFullName));
        return resumes;
    }

    @Override
    public int size() {
        final int[] size = {0};
        sqlHelper.execute("SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                size[0]++;
            }
        });
        return size[0];
    }
}
