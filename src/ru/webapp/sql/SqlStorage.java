package ru.webapp.sql;


import ru.webapp.model.Resume;
import ru.webapp.storage.Storage;

import java.sql.DriverManager;
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
        sqlHelper.execute("DELETE FROM resume", (statement, ps) -> ps.execute(statement));
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", (statement, ps) -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            return ps.execute(statement);
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", (statement, ps) -> {
            ps.setString(2, resume.getFullName());
            ps.setString(1, resume.getUuid());
            return ps.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
//        PreparedStatement ps = sqlHelper.createStatement("SELECT * FROM resume r WHERE r.uuid = ?");
//        try {
//            ps.setString(1, uuid);
//            ResultSet resultSet = ps.executeQuery();
//            if (!resultSet.next()) {
//                throw new NotExistStorageException(uuid);
//            }
//            return new Resume(uuid, resultSet.getString("full_name"));
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", (statement, ps) -> {
            ps.setString(1, uuid);
            return ps.execute();
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        sqlHelper.execute("SELECT * FROM resume", (statement, ps) -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String uuid = resultSet.getString(1);
                String fullName = resultSet.getString(2);
                resumes.add(new Resume(uuid, fullName));
            }
            return ps.execute();
        });
        resumes.sort(Comparator.comparing(Resume::getFullName));
        return resumes;
    }

    @Override
    public int size() {
        return getAllSorted().size();
    }
}
