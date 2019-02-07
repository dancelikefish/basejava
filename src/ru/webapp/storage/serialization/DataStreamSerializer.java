package ru.webapp.storage.serialization;

import ru.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void serialize(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), contactTypeStringEntry -> {
                dos.writeUTF(String.valueOf(contactTypeStringEntry.getKey()));
                dos.writeUTF(contactTypeStringEntry.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(sectionType.name());
                        dos.writeUTF(((SimpleTextSection) section).getTextSection());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(sectionType.name());
                        writeCollection(dos, ((ListSection) section).getListSection(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(sectionType.name());
                        writeCollection(dos, ((OrganizationSection) section).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            if (organization.getHomePage().getUrl() == null) {
                                dos.writeUTF("");
                            } else
                                dos.writeUTF(organization.getHomePage().getUrl());
                            writeCollection(dos, organization.getPositions(), position -> {
                                dos.writeUTF(position.getTitle());
                                if (position.getDescription() == null) {
                                    dos.writeUTF("");
                                } else
                                    dos.writeUTF(position.getDescription());
                                writeLocalDate(position.getStartDate(), dos);
                                writeLocalDate(position.getFinishDate(), dos);
                            });
                        });
                        break;
                }
            });
        }
    }

    @FunctionalInterface
    private interface Writer<T> {
        void writeElement(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T obj : collection) {
            writer.writeElement(obj);
        }
    }

    private void writeLocalDate(LocalDate date, DataOutputStream dos) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    @Override
    public Resume deserialize(InputStream inputStream) throws IOException {
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(SectionType.valueOf(dis.readUTF()), new SimpleTextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(SectionType.valueOf(dis.readUTF()), new ListSection(readList(dis, dis::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.setSection(SectionType.valueOf(dis.readUTF()), readOrganizationSection(dis));
                        break;
                }
            });
            return resume;
        }
    }

    @FunctionalInterface
    private interface Function {
        void readElement() throws IOException;
    }

    @FunctionalInterface
    private interface Reader<T> {
        T readElement() throws IOException;
    }

    private <T> void readCollection(DataInputStream dis, Function function) throws IOException {
        int collectionSize = dis.readInt();
        for (int i = 0; i < collectionSize; i++) {
            function.readElement();
        }
    }

    private <T> List<T> readList(DataInputStream dis, Reader<T> reader) throws IOException {
        int listSize = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(reader.readElement());
        }
        return list;
    }

    private OrganizationSection readOrganizationSection(DataInputStream dis) throws IOException {
        int orgSize = dis.readInt();
        List<Organization> org = new ArrayList<>();
        List<Organization.Position> orgPos = new ArrayList<>();
        for (int i = 0; i < orgSize; i++) {
            String name = dis.readUTF();
            String url = dis.readUTF();

            int orgPositionSize = dis.readInt();
            for (int j = 0; j < orgPositionSize; j++) {
                orgPos.add(new Organization.Position(dis.readUTF(), dis.readUTF()
                        , readLocalDate(dis), readLocalDate(dis)));
            }
            org.add(new Organization(name, url, orgPos));
        }
        return new OrganizationSection(org);
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}
