package ru.webapp.storage;


import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

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
        sqlHelper.transactionalExecute(connection -> {
                    try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContacts(resume, connection);
                    return null;
                }
        );
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume r SET full_name = ? WHERE r.uuid = ?")) {
                ps.setString(2, resume.getFullName());
                ps.setString(1, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(resume);
            insertContacts(resume, connection);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                "SELECT * FROM resume r\n"
                + "LEFT JOIN contact c\n"
                + "    ON r.uuid = c.resume_uuid\n"
                + " WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, resultSet.getString("full_name"));
            do {
                String value = resultSet.getString("value");
                ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
                resume.addContact(contactType, value);
            } while (resultSet.next());

            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r\n" +
                "LEFT JOIN contact c\n" +
                "ON r.uuid = c.resume_uuid\n" +
                "ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> notDuplicatedResumes = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString(1);
                String fullName = rs.getString(2);
                if (notDuplicatedResumes.get(uuid) == null) {
                    notDuplicatedResumes.put(uuid, new Resume(uuid, fullName));
                }
                String value = rs.getString("value");
                if (value != null) {
                    ContactType contactType = ContactType.valueOf(rs.getString("type"));
                    notDuplicatedResumes.get(uuid).addContact(contactType, value);
                }
            }
            return new ArrayList<>(notDuplicatedResumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) AS total FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("total") : 0;
        });
    }

    private void insertContacts(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Resume resume) {
        sqlHelper.execute("DELETE FROM contact WHERE resume_uuid = ?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }
}
