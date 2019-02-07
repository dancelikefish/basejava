package ru.webapp.util;

import ru.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume gKislin = new Resume("Grigoriy Kislin");
        ResumeTestData.fillWholeResume(gKislin);
    }

    public static void fillWholeResume(Resume resume) {
        resume.setContacts(ResumeTestData.fillContactSection());

        resume.setSection(SectionType.PERSONAL, new SimpleTextSection(ResumeTestData.fillPersonalSection()));
        resume.setSection(SectionType.OBJECTIVE, new SimpleTextSection(ResumeTestData.fillPositionSection()));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillAchievementSection()));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fillQualificationSection()));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection()));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection()));
    }

    public static Map<ContactType, String> fillContactSection() {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.CELLPHONENUMBER, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "gkislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        return contacts;
    }

    public static String fillPositionSection() {
        return "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
    }

    public static String fillPersonalSection() {
        return "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";
    }

    public static List<String> fillAchievementSection() {
        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                " Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP." +
                " Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, " +
                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish)." +
                " Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и" +
                " мониторинга системы по JMX (Jython/ Django).");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        return achievements;
    }

    public static List<String> fillQualificationSection() {
        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI," +
                " JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualifications.add("Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        return qualifications;
    }

    public static List<Organization> fillExperienceSection() {
        List<Organization> occupationPlaces = new ArrayList<>();
        Organization.Position javaOnlineProjects = new Organization.Position("Автор проекта.", "Создание, организация и " +
                "проведение Java онлайн проектов и стажировок", LocalDate.of(2013, 10, 1), LocalDate.now());

        Organization.Position wrike = new Organization.Position("Старший разработчик (backend)", "Проектирование и разработка " +
                "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                "авторизация по OAuth1, OAuth2, JWT SSO.", LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1));
        Organization.Position ritCenter = new Organization.Position("Java архитектор", "Организация процесса разработки системы ERP " +
                "для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA " +
                "via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, " +
                "doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, " +
                "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python", LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1));
        Organization.Position luxoft = new Organization.Position("Ведущий программист", "Участие в проекте Deutsche Bank" +
                " CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, " +
                "мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.", LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1));
        Organization.Position yota = new Organization.Position("Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"" +
                "Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка." +
                " Разработка online JMX клиента (Python/ Jython, Django, ExtJS)", LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));
        Organization.Position enkata = new Organization.Position("Разрабочик ПО", "Разработка информационной модели, проектирование интерфейсов," +
                " реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).", LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1));
        Organization.Position alcatel = new Organization.Position("Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение " +
                "ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).", LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1));

        List<Organization.Position> jop = new ArrayList<>();
        jop.add(javaOnlineProjects);
        List<Organization.Position> wr = new ArrayList<>();
        wr.add(wrike);
        List<Organization.Position> rit = new ArrayList<>();
        rit.add(ritCenter);
        List<Organization.Position> lux = new ArrayList<>();
        lux.add(luxoft);
        List<Organization.Position> yot = new ArrayList<>();
        yot.add(yota);
        List<Organization.Position> enk = new ArrayList<>();
        enk.add(enkata);
        List<Organization.Position> alc = new ArrayList<>();
        alc.add(alcatel);

        Organization j = new Organization("Java Online Project", "javaops.ru", jop);
        Organization w = new Organization("Wrike", "https://www.wrike.com/", wr);
        Organization r = new Organization("RIT Center", "", rit);
        Organization l = new Organization("Luxoft", "luxoft.ru", lux);
        Organization y = new Organization("Yota", "https://www.yota.ru/", yot);
        Organization e = new Organization("Enkata", "https://www.enkata.com/", enk);
        Organization a = new Organization("Alcatel", "alcatel.ru", alc);
        occupationPlaces.add(j);
        occupationPlaces.add(w);
        occupationPlaces.add(r);
        occupationPlaces.add(l);
        occupationPlaces.add(y);
        occupationPlaces.add(e);
        occupationPlaces.add(a);

        return occupationPlaces;
    }

    public static List<Organization> fillEducationSection() {
        List<Organization> educationPlaces = new ArrayList<>();
        Organization.Position coursera = new Organization.Position("\"Functional Programming Principles in Scala\" by Martin Odersky", "",
                LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1));
        Organization.Position luxofEdu = new Organization.Position("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "",
                LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1));
        Organization.Position siemensAg = new Organization.Position("3 месяца обучения мобильным IN сетям (Берлин)", "",
                LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1));
        Organization.Position alcatelEdu = new Organization.Position("6 месяцев обучения цифровым телефонным сетям (Москва)", "",
                LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));
        Organization.Position spbNii = new Organization.Position("Аспирантура (программист С, С++)", "", LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1));
        Organization.Position spbNii2 = new Organization.Position("Инженер (программист Fortran, C)", "", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1));
        Organization.Position mfti = new Organization.Position("Заочная физико-техническая школа при МФТИ", "", LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1));

        List<Organization.Position> cour = new ArrayList<>();
        cour.add(coursera);
        List<Organization.Position> luxEd = new ArrayList<>();
        luxEd.add(luxofEdu);
        List<Organization.Position> siem = new ArrayList<>();
        siem.add(siemensAg);
        List<Organization.Position> alcat = new ArrayList<>();
        alcat.add(alcatelEdu);
        List<Organization.Position> spb = new ArrayList<>();
        spb.add(spbNii);
        spb.add(spbNii2);
        List<Organization.Position> mft = new ArrayList<>();
        mft.add(mfti);

        Organization c = new Organization("Coursera", "coursera.com", cour);
        Organization lu = new Organization("Luxoft Education Center", "luxoft.com", luxEd);
        Organization s = new Organization("Siemens", "siemens.com", siem);
        Organization alcate = new Organization("Alcatel", "alcatel.com", alcat);
        Organization spbN = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/ru/", spb);
        Organization mf = new Organization("МФТИ", "http://www.school.mipt.ru/", mft);

        educationPlaces.add(c);
        educationPlaces.add(lu);
        educationPlaces.add(s);
        educationPlaces.add(alcate);
        educationPlaces.add(spbN);
        educationPlaces.add(mf);

        return educationPlaces;
    }
}
