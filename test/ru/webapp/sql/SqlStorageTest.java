package ru.webapp.sql;

import ru.webapp.Config;
import ru.webapp.storage.AbstractStorageTest;
import ru.webapp.storage.SqlStorage;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword()));
    }
}