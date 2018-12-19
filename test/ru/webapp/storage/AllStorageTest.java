package ru.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectStreamPathSerializerTest.class,
        ObjectStreamFileSerializerTest.class,
        XmlStreamPathSerializerTest.class,
        JsonStreamPathSerializerTest.class
})

public class AllStorageTest {
}