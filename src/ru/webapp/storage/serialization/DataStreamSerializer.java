package ru.webapp.storage.serialization;

import ru.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void serialize(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
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
                        dos.writeInt(((ListSection) section).getListSection().size());
                        writeListSections(((ListSection) section).getListSection(), dos);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(sectionType.name());
                        dos.writeInt(((OrganizationSection) section).getOrganizations().size());
                        writeOrganizationSections(((OrganizationSection) section).getOrganizations(), dos);
                        break;
                }
            }
        }
    }

    private void writeListSections(List<String> list, DataOutputStream dos) throws IOException {
        for (String s : list) {
            dos.writeUTF(s);
        }
    }

    private void writeOrganizationSections(List<Organization> list, DataOutputStream dos) throws IOException {
        for (Organization s : list) {
            dos.writeUTF(s.getHomePage().getName());
            dos.writeUTF(s.getHomePage().getUrl());
            dos.writeInt(s.getPositions().size());
            for (Organization.Position op : s.getPositions()) {
                dos.writeUTF(op.getTitle());
                dos.writeUTF(op.getDescription());
                writeLocalDate(op.getStartDate(), dos);
                writeLocalDate(op.getFinishDate(), dos);
            }
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
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(SectionType.valueOf(dis.readUTF()), new SimpleTextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(SectionType.valueOf(dis.readUTF()), readListSection(dis));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.addSection(SectionType.valueOf(dis.readUTF()), readOrganizationSection(dis));
                        break;
                }
            }
            return resume;
        }
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        int listSize = dis.readInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(dis.readUTF());
        }
        return new ListSection(list);
    }

    private OrganizationSection readOrganizationSection(DataInputStream dis) throws IOException {
        int orgSize = dis.readInt();
        List<Organization> org = new ArrayList<>();
        List<Organization.Position> orgPos = new ArrayList<>();
        for (int i = 0; i < orgSize; i++) {
            String name = dis.readUTF();
            String url = dis.readUTF();

            int orgPositionSize = dis.readInt();
            for (int j = 0; j < orgPositionSize; j++ ) {
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
