package ru.webapp.util;

import org.junit.Assert;
import org.junit.Test;
import ru.webapp.model.Resume;
import ru.webapp.model.Section;
import ru.webapp.model.SimpleTextSection;

import static ru.webapp.storage.AbstractStorageTest.*;

public class JsonParserTest {

    @Test
    public void testResume() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(R1, resume);
    }

    @Test
    public void testSection() {
        Section section1 = new SimpleTextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}